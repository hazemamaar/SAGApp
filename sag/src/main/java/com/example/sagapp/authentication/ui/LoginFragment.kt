package com.example.sagapp.authentication.ui

import androidx.fragment.app.viewModels
import com.example.core.base.android.BaseFragment
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>() {
    override fun onFragmentReady() {
        TODO("Not yet implemented")
    }

    override val mViewModel: LoginViewModel by viewModels()



}