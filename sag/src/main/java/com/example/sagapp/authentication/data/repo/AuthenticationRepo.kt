package com.example.sagapp.authentication.data.repo

import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.LoginDto
import com.example.sagapp.authentication.data.local.entities.LoginParams
import com.example.sagapp.authentication.data.remote.service.AuthenticationServices
import javax.inject.Inject

class AuthenticationRepo @Inject constructor(private val authService: AuthenticationServices) {

    suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto> {
       return authService.login(loginParams)
    }
}