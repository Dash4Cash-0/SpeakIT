package com.example.speakit.network

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

data class TranscriptionResponse(val text: String)

interface TranscriptionApi {
    @Multipart
    @POST("transcribe")
    suspend fun uploadRecording(
        @Part file: MultipartBody.Part
    ): TranscriptionResponse
}
