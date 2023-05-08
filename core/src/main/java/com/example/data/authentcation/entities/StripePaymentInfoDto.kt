package com.example.data.authentcation.entities

data class StripePaymentInfoDto(
    val code: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val payment_completed: Int,
    val payment_option: String,
    val updated_at: String
)