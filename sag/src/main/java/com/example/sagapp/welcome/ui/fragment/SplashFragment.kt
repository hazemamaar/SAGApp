package com.example.sagapp.welcome.ui.fragment

import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.navigateSafe
import com.example.core.extentions.observe
import com.example.sagapp.R
import com.example.sagapp.databinding.FragmentSplashBinding
import com.example.sagapp.welcome.ui.viewmodel.SplashAction
import com.example.sagapp.welcome.ui.viewmodel.SplashViewModel


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun onFragmentReady() {
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_splashscreen)
        binding.imageSplash.animation = slideAnimation
        subscribeToObservers()
        mViewModel.onBoardingReadToFinish()

    }

    override val mViewModel: SplashViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SplashAction) {
        when (action) {
            is SplashAction.OnBoardingFinish -> {
                if(action.finish){
                    navigateSafe(SplashFragmentDirections.actionSplashFragmentToLoginFragment(), container = R.id.frag_host)
                }else{
                    navigateSafe(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment(), container = R.id.frag_host)
                }
            }
            else -> {}
        }
    }
}