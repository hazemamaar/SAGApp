package com.example.sagapp.commands.data.remote

import android.util.Log
import com.example.data.alarm.data.AlarmItem
import com.example.data.alarm.domain.AlarmScheduler
import com.example.sagapp.alarm.ui.viewmodel.AlarmActions
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class FireBaseCommand:Action{
    data class ReadCommand(val message:String) :FireBaseCommand()
    object Cancel:FireBaseCommand()
    object  Schedule:FireBaseCommand()
}
@HiltViewModel
class CommandsViewModel @Inject constructor(private val alarmScheduler: AlarmScheduler) :BaseViewModel<FireBaseCommand>(){


    fun readMessagesFromFirebase() {
        val messagesRef = FirebaseDatabase.getInstance().getReference("messages")

        messagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = snapshot.children.mapNotNull { child ->
                    Log.e("child", "onDataChange: "+child )
                    child.children.mapNotNull {child ->
                        Log.e("child2", "onDataChange: "+child )
                        if (child.value != null) {
                            produce(FireBaseCommand.ReadCommand(child.value.toString()))
                        }
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                produce(FireBaseCommand.ReadCommand(error.message))
            }
        })
    }

    fun scheduleAlarm(alarmItem: AlarmItem){
        alarmItem.let (alarmScheduler::schedule)
        produce(FireBaseCommand.Schedule)
    }
    fun cancelAlarm(alarmItem: AlarmItem){
        alarmItem.let (alarmScheduler::cancel)
        produce(FireBaseCommand.Cancel)
    }
}