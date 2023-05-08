package com.example.sagapp.authentication.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import com.example.core.response.Resource
import com.example.data.authentcation.entities.LoginDto
import com.example.data.authentcation.entities.LoginParams
import com.example.features.authentication.domain.intractors.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class LoginAction : Action {
   object Loading : LoginAction()
    data class FailureMessage(val message:String):LoginAction()

    data class Success(val loginInfo: LoginDto):LoginAction()
}
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel<LoginAction>() {

    fun login(params: LoginParams){
        loginUseCase(viewModelScope,params){
            when(it){
                is Resource.Failure -> {
                    produce(LoginAction.FailureMessage(it.message.toString()))
                }
                is Resource.Progress -> {
                    if(it.loading)
                          produce(LoginAction.Loading)
                }
                is Resource.Success -> {
                    it.data.showLog("llllllllllll")
                    produce(LoginAction.Success(it.data))
                }
            }

        }
    }
}