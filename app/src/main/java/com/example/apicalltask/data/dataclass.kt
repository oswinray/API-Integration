package com.example.apicalltask.data

data class dataclass(
    val error: Boolean,
    val message: String,
    val response: Response,
    val status: String,
    val statusCode: Int
)