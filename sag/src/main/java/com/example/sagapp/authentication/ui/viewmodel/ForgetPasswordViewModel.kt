package com.example.sagapp.authentication.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.core.extentions.showLog
import com.example.core.response.Resource
import com.example.sagapp.authentication.data.local.entities.ChangePasswordModels.ChangePasswordDto
import com.example.sagapp.authentication.domain.model.LoginParams
import com.example.sagapp.authentication.domain.usecase.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class ForgetPasswordAction : Action {
    object Loading : ForgetPasswordAction()
    data class FailureMessage(val message:String):ForgetPasswordAction()

    data class Success(val changeModel: ChangePasswordDto):ForgetPasswordAction()
}
@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val forgetPasswordUseCase: ChangePasswordUseCase) :BaseViewModel<ForgetPasswordAction>()  {

    fun forgetPassword(params: String){
        forgetPasswordUseCase(viewModelScope,params){
            when(it){
                is Resource.Failure -> {
                    produce(ForgetPasswordAction.FailureMessage(it.message.toString()))
                }
                is Resource.Progress -> {
                    it.loading.toString().showLog("loading")
                    if(it.loading)
                        produce(ForgetPasswordAction.Loading)
                }
                is Resource.Success -> {
                    it.data.toString().showLog("success")
                    produce(ForgetPasswordAction.Success(it.data))
                }
            }
0
        }
    }
}