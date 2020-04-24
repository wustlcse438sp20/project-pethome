package com.example.pethome.Data

data class Token(
    val token_type: String,
    val expires_in: Int,
    val access_token: String
)