package com.example.sagapp.authentication.ui.fragments

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.observe
import com.example.sagapp.authentication.domain.model.LoginParams
import com.example.sagapp.authentication.ui.viewmodel.LoginAction
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>() {
    override fun onFragmentReady() {
        binding.loginFragmentConfirmBtn.setOnClickListener {
            mViewModel.login( LoginParams(binding.loginFragmentEmailInputText.text.toString(),binding.loginFragmentPasswordInputText.text.toString()))

        }
        subscribeToObservers()
    }

    override val mViewModel: LoginViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: LoginAction) {
        when (action) {
            is LoginAction.FailureMessage -> {
                Log.e("hhh", "fail", )
            }
            LoginAction.Loading -> {
                Log.e("hhh", "loading", )
            }
            is LoginAction.Success -> {
                Log.e("hhh", "success", )
            }
        }
    }


}