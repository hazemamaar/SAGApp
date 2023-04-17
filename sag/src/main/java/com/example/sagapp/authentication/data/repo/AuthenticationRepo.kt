package com.example.sagapp.authentication.data.repo

import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.ChangePasswordModels.ChangePasswordDto
import com.example.sagapp.authentication.data.local.entities.loginModels.LoginDto
import com.example.sagapp.authentication.domain.model.LoginParams

interface AuthenticationRepo {
    suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto>
    suspend fun changePassword(email: String) : BaseResponse<ChangePasswordDto>
}