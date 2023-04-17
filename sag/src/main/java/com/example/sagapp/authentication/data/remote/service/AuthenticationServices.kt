package com.example.sagapp.authentication.data.remote.service

import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.ChangePasswordModels.ChangePasswordDto
import com.example.sagapp.authentication.data.local.entities.loginModels.LoginDto
import com.example.sagapp.authentication.domain.model.LoginParams
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationServices {
    companion object {
        private const val LOGIN = "handle-login"
        private const val CHANGEPASSWORD="changePassword"
    }
    @POST(LOGIN)
    suspend fun login(@Body loginParams: LoginParams) : BaseResponse<LoginDto>
    @POST(CHANGEPASSWORD)
    suspend fun changePassword(@Body email: String) : BaseResponse<ChangePasswordDto>

}