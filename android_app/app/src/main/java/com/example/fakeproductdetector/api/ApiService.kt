package com.example.fakeproductdetector.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Body
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<MessageResponse>

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): Response<PredictionResponse>
}