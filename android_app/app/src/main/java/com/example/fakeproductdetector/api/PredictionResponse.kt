package com.example.fakeproductdetector.api

data class PredictionResponse(
    val prediction: String,
    val confidence: Double
)