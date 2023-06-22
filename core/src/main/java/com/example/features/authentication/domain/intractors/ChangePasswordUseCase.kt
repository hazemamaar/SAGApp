package com.example.features.authentication.domain.intractors

import com.example.core.response.BaseResponse
import com.example.core.usecase.RemoteUseCase
import com.example.data.remote.entities.ChangePasswordDto
import com.example.features.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val authenticationRepoImpl: AuthenticationRepo) : RemoteUseCase<BaseResponse<ChangePasswordDto>, String>() {
    override fun executeRemoteDS(body: String?): Flow<BaseResponse<ChangePasswordDto>> = flow{
        emit( authenticationRepoImpl.changePassword(body!!))
    }

}