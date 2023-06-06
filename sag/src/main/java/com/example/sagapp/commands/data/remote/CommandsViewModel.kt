package com.example.sagapp.commands.data.remote

import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
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
        val messagesRef = FirebaseDatabase.getInstance().getReference("commands")

        messagesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { child ->
                    child.children.forEach { child ->

                        if (child.key.equals("cancel") && child.key != null) {
                            child.children.mapNotNull { cancelAlarm ->
                                cancelAlarm(
                                    AlarmItem(
                                        time = LocalDateTime.now()
                                            .plusSeconds(cancelAlarm.value.toString().toLong()),
                                        cancelAlarm.key.toString()
                                    )
                                )
                            }
                        } else {
                            if (child.value != null) {
                                child.children.mapNotNull { schedule ->
                                    scheduleAlarm(
                                        AlarmItem(
                                            time = LocalDateTime.now()
                                                .plusSeconds(schedule.value.toString().toLong()),
                                            schedule.key.toString()
                                        )
                                    )
                                }

                            }
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                produce(FireBaseCommand.ReadCommand(error.message))
            }
        })
    }

    fun scheduleAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::schedule)
        alarmItem
            .time.toString().showLog("hazemamaar")
        produce(FireBaseCommand.Schedule)
    }

    fun cancelAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::cancel)
        produce(FireBaseCommand.Cancel)
    }
}