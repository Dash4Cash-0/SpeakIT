package com.example.speakit.viewmodel

import android.app.Application
import android.media.MediaRecorder
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakit.network.RetrofitInstance
import com.example.speakit.network.SpeechRequest
import com.example.speakit.network.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording

    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> = _isUploading

    private val _transcript = MutableStateFlow<String?>(null)
    val transcript: StateFlow<String?> = _transcript

    private val _translation = MutableStateFlow<String?>(null)
    val translation: StateFlow<String?> = _translation

    private val _speechFile = MutableStateFlow<File?>(null)
    val speechFile: StateFlow<File?> = _speechFile

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var recorder: MediaRecorder? = null
    private var outputFile: File? = null

    private var tempSourceLang: String = "English"
    private var tempTargetLang: String = "Italian"

    fun startRecording() {
        val context = getApplication<Application>()
        val file = File(context.cacheDir, "recording_${System.currentTimeMillis()}.m4a")

        val newRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
            MediaRecorder()
        }

        newRecorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(file.absolutePath)
            prepare()
            start()
        }

        recorder = newRecorder
        outputFile = file
        _isRecording.value = true
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        _isRecording.value = false

        uploadRecording()

    }

    private fun uploadRecording() {
        val file = outputFile ?: return

        viewModelScope.launch {
            _isUploading.value = true
            _error.value = null
            try {
                val requestBody = file.asRequestBody("audio/mp4".toMediaTypeOrNull())
                val part = MultipartBody.Part.createFormData("file", file.name, requestBody)

                val response = RetrofitInstance.api.uploadRecording(part)
                _transcript.value = response.text
                translateText()
            } catch (e: Exception) {
                _error.value = e.message ?: "Upload failed"
            } finally {
                _isUploading.value = false
            }
        }
    }

    private fun translateText() {

        val text = _transcript.value ?: return

        viewModelScope.launch {
            _error.value = null
            try {
                val translate = TranslateRequest(text, tempSourceLang, tempTargetLang)
                val response = RetrofitInstance.api.translateText(translate)
                _translation.value = response.text
                fetchSpeech()

            }catch (e: Exception){
                _error.value = e.message ?: "Translation failed"
            }

        }

    }

    private fun fetchSpeech(){
        val text = _translation.value ?: return
        val context = getApplication<Application>()

        viewModelScope.launch {
            _error.value = null
            try {
                val speechReq = SpeechRequest(text)
                val speechResponse = RetrofitInstance.api.getSpeech(speechReq)

                val file = File(context.cacheDir, "speech_${System.currentTimeMillis()}.mp3")
                withContext(Dispatchers.IO) {
                    speechResponse.byteStream().use { input ->
                        file.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
                _speechFile.value = file

            }catch (e: Exception){
                _error.value = e.message ?: "Failed to playback audio"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        recorder?.release()
        recorder = null
    }
}