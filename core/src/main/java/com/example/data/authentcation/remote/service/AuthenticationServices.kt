package com.example.data.authentcation.remote.service

import com.example.core.response.BaseResponse
import com.example.data.authentcation.entities.ChangePasswordDto
import com.example.data.authentcation.entities.LoginDto
import com.example.data.authentcation.entities.LoginParams
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationServices {
    companion object {
        private const val LOGIN = "handle-login"
        private const val CHANGEPASSWORD="changePassword"

    }

    @POST(LOGIN)
    suspend fun login(@Body loginParams: LoginParams): BaseResponse<LoginDto>
    @POST(CHANGEPASSWORD)
    suspend fun changePassword(@Body email: String) : BaseResponse<ChangePasswordDto>

}