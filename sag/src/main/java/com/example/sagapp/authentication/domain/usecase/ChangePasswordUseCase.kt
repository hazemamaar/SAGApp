package com.example.sagapp.authentication.domain.usecase

import com.example.core.base.usecase.BaseRemoteUseCase
import com.example.core.response.BaseResponse
import com.example.sagapp.authentication.data.local.entities.ChangePasswordModels.ChangePasswordDto
import com.example.sagapp.authentication.data.local.entities.loginModels.LoginDto
import com.example.sagapp.authentication.data.repo.AuthenticationRepo
import com.example.sagapp.authentication.domain.model.LoginParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangeOPasswordUseCase @Inject constructor(private val authenticationRepoImpl: AuthenticationRepo) : BaseRemoteUseCase<BaseResponse<ChangePasswordDto>, ChangePasswordDto, String>() {
    override fun mapper(req: BaseResponse<ChangePasswordDto>): ChangePasswordDto {
        return req.data!!
    }

    override fun executeRemote(params: String?): Flow<BaseResponse<ChangePasswordDto>>  =flow {
        emit(authenticationRepoImpl.changePassword(params!!))
    }
}