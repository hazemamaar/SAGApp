package com.example.sagapp.welcome.ui.viewmodel



import androidx.lifecycle.viewModelScope
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.sagapp.welcome.data.OnBoardingData
import com.example.sagapp.welcome.data.model.OnBoardingModel
import com.example.sagapp.welcome.domain.preferences.OnBoardPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OnBoardingAction : Action {
    data class OnBoardingData(val list: List<OnBoardingModel>) : OnBoardingAction()

}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardData: OnBoardingData,private val pref: OnBoardPref) : BaseViewModel<OnBoardingAction>() {


    fun getOnBoarding(){
        produce(OnBoardingAction.OnBoardingData(onBoardData.welcomeData()))
    }
    fun onBoardingWriteToFinish(){
        viewModelScope.launch {
            pref.onBoardWriteToFinish()
        }
    }

}