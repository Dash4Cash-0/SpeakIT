package com.example.speakit.network

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

data class TextResponse(val text: String)
data class TranslateRequest(
    val transcribedText: String,
    val sourceLanguage: String,
    val targetLanguage: String
    )


interface ApiService {
    @Multipart
    @POST("transcribe")
    suspend fun uploadRecording(
        @Part file: MultipartBody.Part
    ): TextResponse

    @POST("translate")
    suspend fun translateText(
        @Body request: TranslateRequest

    ): TextResponse
}
