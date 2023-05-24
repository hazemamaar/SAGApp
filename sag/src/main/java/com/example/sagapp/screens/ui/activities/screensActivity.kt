package com.example.sagapp.screens.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sagapp.R
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.screens.ui.fragments.homeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class screensActivity : AppCompatActivity() {

    private val mViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screens)
        actionBar?.hide()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
       bottomNavigationView.setupWithNavController(navController)


    }
}
