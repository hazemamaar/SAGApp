package com.example.sagapp.commands

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.data.alarm.data.AlarmItem
import com.example.sagapp.android.BaseActivity
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.commands.data.remote.FireBaseCommand
import com.example.sagapp.commands.data.remote.CommandsViewModel
import com.example.sagapp.databinding.ActivityCommuncationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class CommunicationActivity : BaseActivity<ActivityCommuncationBinding, CommandsViewModel>() {
    override val mViewModel: CommandsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.readMessagesFromFirebase()
        subscribeToObservers()

    }
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: FireBaseCommand) {
        when (action) {
            is FireBaseCommand.ReadCommand -> {
                Log.e("firebase", "handleUiState: "+action.message )
                mViewModel.scheduleAlarm(AlarmItem( time = LocalDateTime.now()
                    .plusSeconds(action.message.toLong()),"hazem"))
            }
        }
    }


}