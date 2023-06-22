package com.example.features.authentication.data.repo

import com.example.core.response.BaseResponse
import com.example.data.remote.entities.ChangePasswordDto
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import com.example.data.remote.remote.service.RemoteServices
import javax.inject.Inject

class AuthenticationRepo @Inject constructor(private val authService: RemoteServices) {

    suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto> {
       return authService.login(loginParams)
    }

    suspend fun changePassword(email: String): BaseResponse<ChangePasswordDto> {
        return authService.changePassword(email)
    }
}