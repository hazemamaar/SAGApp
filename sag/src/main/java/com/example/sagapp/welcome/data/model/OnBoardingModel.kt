package com.example.sagapp.welcome.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnBoardingModel (
    @StringRes
    val welcomeWord:Int,
    @DrawableRes
    val welcomeImage:Int,
    @StringRes
    val welcomeText:Int)