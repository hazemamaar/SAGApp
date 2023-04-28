package com.example.sagapp.authentication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.navigateSafe
import com.example.core.extentions.observe
import com.example.sagapp.R
import com.example.sagapp.authentication.ui.viewmodel.ForgetPasswordAction
import com.example.sagapp.authentication.ui.viewmodel.ForgetPasswordViewModel
import com.example.sagapp.authentication.ui.viewmodel.LoginAction
import com.example.sagapp.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding,ForgetPasswordViewModel>() {


    override fun onFragmentReady() {
        binding.forgetPasswordFragmentSendBtn.setOnClickListener {
                 mViewModel.forgetPassword(binding.forgetPasswordFragmentEmailInputText.text.toString())
        }

    }

    override val mViewModel: ForgetPasswordViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: ForgetPasswordAction) {
        when (action) {
            is ForgetPasswordAction.FailureMessage -> {

            }
            ForgetPasswordAction.Loading -> {

            }
            is ForgetPasswordAction.Success -> {
             navigateSafe(R.id.action_forgetPasswordFragment_to_successfulLoginFragment, container = R.id.frag_host)
            }
        }
    }

}