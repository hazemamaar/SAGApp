package com.example.sagapp.authentication.domain.usecase

import com.example.core.base.usecase.BaseRemoteUseCase
import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.LoginDto
import com.example.sagapp.authentication.domain.model.LoginParams
import com.example.sagapp.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepo: AuthenticationRepo) : BaseRemoteUseCase<BaseResponse<LoginDto>,LoginDto, LoginParams>() {
    override fun mapper(req: BaseResponse<LoginDto>): LoginDto {
//        LoginMapper.mapResponse(req).toString().showLog("hazzzzzom")
        return req.data!!

    }

    override fun executeRemote(params: LoginParams?): Flow<BaseResponse<LoginDto>>  = flow{
        emit(authenticationRepo.login(params!!))

    }


}