package com.example.sagapp.authentication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.core.response.Resource
import com.example.sagapp.authentication.data.local.entities.LoginParams
import com.example.sagapp.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class LoginAction :Action{

}
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel<LoginAction>() {

    fun login(params: LoginParams){
        loginUseCase.invoke(viewModelScope,params){
            when(it){
                is Resource.Failure -> {
                    Log.e("sag", "login: "+ it.message )
                }
                is Resource.Progress -> {
                    Log.e("sag", "login: "+ it.loading.toString() )
                }
                is Resource.Success -> {
                    Log.e("sag", "login: "+ it.data.data )
                }
            }

        }
    }
}