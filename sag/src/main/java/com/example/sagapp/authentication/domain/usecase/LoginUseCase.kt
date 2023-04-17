package com.example.sagapp.authentication.domain.usecase

import com.example.core.base.usecase.BaseRemoteUseCase
import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.loginModels.LoginDto
import com.example.sagapp.authentication.data.repo.AuthenticationRepo
import com.example.sagapp.authentication.domain.model.LoginParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepoImpl: AuthenticationRepo) : BaseRemoteUseCase<BaseResponse<LoginDto>, LoginDto, LoginParams>() {
    override fun mapper(req: BaseResponse<LoginDto>): LoginDto {
        return req.data!!

    }

    override fun executeRemote(params: LoginParams?): Flow<BaseResponse<LoginDto>>  = flow{
        emit(authenticationRepoImpl.login(params!!))

    }


}