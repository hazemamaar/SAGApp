package com.example.sagapp.welcome.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.sagapp.welcome.domain.preferences.OnBoardPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class SplashAction : Action {
    data class OnBoardingFinish(val finish:Boolean):SplashAction()
    object TimeDone : SplashAction()

}

@HiltViewModel
class SplashViewModel @Inject constructor(private val pref: OnBoardPref) : BaseViewModel<SplashAction>() {

    fun splashFinished() {

    }
    fun onBoardingReadToFinish(){

        viewModelScope.launch {
            withContext(Dispatchers.Main){
                delay(3000)
            }
            pref.onBoardReadToFinish().onEach {
                produce(SplashAction.OnBoardingFinish(it))
            }.launchIn(viewModelScope)
        }

    }
}