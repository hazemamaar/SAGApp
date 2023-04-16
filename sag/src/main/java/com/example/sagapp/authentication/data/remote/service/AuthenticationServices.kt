package com.example.sagapp.authentication.data.remote.service

import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.LoginDto
import com.example.sagapp.authentication.domain.model.LoginParams
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationServices {
    companion object {
        private const val LOGIN = "handle-login"

    }
    @POST(LOGIN)
    suspend fun login(@Body loginParams: LoginParams) : BaseResponse<LoginDto>
}