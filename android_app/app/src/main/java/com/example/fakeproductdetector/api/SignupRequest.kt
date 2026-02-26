package com.example.fakeproductdetector.api

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)