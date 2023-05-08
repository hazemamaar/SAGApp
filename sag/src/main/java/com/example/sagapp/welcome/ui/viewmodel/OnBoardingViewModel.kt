package com.example.sagapp.welcome.ui.viewmodel



import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.welcome.data.OnBoardingData
import com.example.sagapp.welcome.data.model.OnBoardingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class OnBoardingAction : Action {
    data class OnBoarding(val list: List<OnBoardingModel>) : OnBoardingAction()

}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val onBoardData: OnBoardingData) : BaseViewModel<OnBoardingAction>() {


    fun getOnBoarding(){
        produce(OnBoardingAction.OnBoarding(onBoardData.welcomeData()))
    }
}