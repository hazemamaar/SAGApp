package com.example.sagapp.commands.data.remote

import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class FireBaseCommand : Action {
    data class ReadCommand(val message: String) : FireBaseCommand()
    object Cancel : FireBaseCommand()
    object Schedule : FireBaseCommand()
}

@HiltViewModel
class CommandsViewModel @Inject constructor(private val alarmScheduler: AlarmScheduler) :
    BaseViewModel<FireBaseCommand>() {


    fun readMessagesFromFirebase() {

    }
}
