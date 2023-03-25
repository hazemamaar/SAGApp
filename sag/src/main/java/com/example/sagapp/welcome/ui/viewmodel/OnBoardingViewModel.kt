package com.example.sagapp.welcome.ui.viewmodel



import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.sagapp.welcome.data.OnBoardingData
import com.example.sagapp.welcome.data.model.OnBoardingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class OnBoardingAction : Action {
    data class OnBoarding(val list: List<OnBoardingModel>) : OnBoardingAction()
}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val onBoardingData: OnBoardingData) : BaseViewModel<OnBoardingAction>() {


    fun getOnBoarding(){
        produce(OnBoardingAction.OnBoarding(onBoardingData.welcomeData()))
    }
}