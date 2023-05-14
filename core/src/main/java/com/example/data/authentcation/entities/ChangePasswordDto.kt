package com.example.data.authentcation.entities

data class ChangePasswordDto(
    val access_token: String,
    val city: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val img: String,
    val is_admin: Int,
    val name: String,
    val oauth_token: Any,
    val phone: String,
    val relationship: String,
    val updated_at: String
)