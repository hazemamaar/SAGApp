package com.example.features.authentication.data.repo

import com.example.core.response.BaseResponse
import com.example.data.authentcation.entities.LoginDto
import com.example.data.authentcation.entities.LoginParams
import com.example.data.authentcation.remote.service.AuthenticationServices
import javax.inject.Inject

class AuthenticationRepo @Inject constructor(private val authService: AuthenticationServices) {

    suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto> {
       return authService.login(loginParams)
    }
}