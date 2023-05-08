package com.example.sagapp.welcome.ui.fragment

import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.R
import com.example.sagapp.databinding.FragmentSplashBinding
import com.example.sagapp.welcome.ui.viewmodel.SplashState
import com.example.sagapp.welcome.ui.viewmodel.SplashViewModel


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun onFragmentReady() {
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_splashscreen)
        binding.imageSplash.animation = slideAnimation
        mViewModel.splashFinished()
        subscribeToObservers()
    }

    override val mViewModel: SplashViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SplashState) {
        when (action) {
            SplashState.TimeDone-> {
                navigateSafe(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment(), container = R.id.frag_host)
            }
        }
    }
}