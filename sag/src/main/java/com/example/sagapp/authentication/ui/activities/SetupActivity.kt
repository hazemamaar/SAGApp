package com.example.sagapp.authentication.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sagapp.R
import com.example.sagapp.authentication.data.local.entities.LoginParams
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivity : AppCompatActivity() {
     private val mViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        mViewModel.login(LoginParams("Hagar@gmail.com","123456"))
    }
}