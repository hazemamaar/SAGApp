package com.example.sagapp.welcome.data

import com.example.sagapp.R
import com.example.sagapp.welcome.data.model.OnBoardingModel
import javax.inject.Inject


class OnBoardingData @Inject constructor()  {
    public fun welcomeData():List<OnBoardingModel>{
        return listOf(OnBoardingModel(R.string.first_onboard_screen,R.drawable.first_onboarding,R.string.first_onboard_page),
            OnBoardingModel(R.string.second_onboard_screen,R.drawable.second_onboard,R.string.second_onboard_page),
            OnBoardingModel(R.string.third_onboard_screen,R.drawable.third_onboarding,R.string.third_onboard_page))
    }
}