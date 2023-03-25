package com.example.sagapp.welcome.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.observe
import com.example.sagapp.databinding.FragmentOnBoardingBinding
import com.example.sagapp.welcome.ui.adapters.OnBoardAdapter
import com.example.sagapp.welcome.ui.viewmodel.OnBoardingAction
import com.example.sagapp.welcome.ui.viewmodel.OnBoardingViewModel
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override val mViewModel: OnBoardingViewModel by viewModels()
    @Inject
    lateinit var onBoardAdapter : OnBoardAdapter
    override fun onFragmentReady() {


        mViewModel.getOnBoarding()
        subscribeToObservers()
        viewPager2Scrolling()
        initIndicator()
    }

    fun viewPager2Scrolling() {
        binding.viewpagerOnboard.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }




            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
    fun initIndicator() {
        binding.indicator.setSliderWidth(40f)
        binding.indicator.setSliderHeight(10f)
        binding.indicator.setSlideMode(IndicatorSlideMode.WORM)
        binding.indicator.setIndicatorStyle(IndicatorStyle.ROUND_RECT)
        binding.indicator.setPageSize(3)
        binding.indicator.notifyDataChanged()
    }

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: OnBoardingAction) {
        when (action) {
            is OnBoardingAction.OnBoarding -> {
                Timber.e("${action.list}")
                Log.e("aya","${action.list}")
                onBoardAdapter.onBoardList =action.list
                binding.viewpagerOnboard.adapter= onBoardAdapter
            }
        }

    }

}