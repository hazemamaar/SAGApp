package com.example.sagapp.authentication.data.repo

import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.ChangePasswordModels.ChangePasswordDto
import com.example.sagapp.authentication.data.local.entities.loginModels.LoginDto
import com.example.sagapp.authentication.domain.model.LoginParams
import com.example.sagapp.authentication.data.remote.service.AuthenticationServices
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(private val authService: AuthenticationServices) :AuthenticationRepo {


    override suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto> {
       return authService.login(loginParams)
    }

    override suspend fun changePassword(email: String): BaseResponse<ChangePasswordDto> {
        return authService.changePassword(email)
    }
}