package com.example.sagapp.authentication.domain.usecase

import com.example.core.base.usecase.BaseRemoteUseCase
import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.LoginDto
import com.example.sagapp.authentication.data.local.entities.LoginParams
import com.example.sagapp.authentication.data.repo.AuthenticationRepo
import com.example.sagapp.authentication.domain.model.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepo: AuthenticationRepo) : BaseRemoteUseCase<BaseResponse<LoginDto>,BaseResponse<Login>,LoginParams>() {
    override fun mapper(req: BaseResponse<LoginDto>): BaseResponse<Login> {
        TODO("Not yet implemented")
    }

    override fun executeRemote(params: LoginParams?): Flow<BaseResponse<LoginDto>>  {
        return flow { params?.let {  authenticationRepo.login(it)}  }
    }

}