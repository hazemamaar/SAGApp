package com.example.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.features.firebase.FireBaseWithAlarm
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import javax.inject.Inject

class FireBaseWithAlarmImpl @Inject constructor(private val alarmScheduler: AlarmScheduler) :
    FireBaseWithAlarm {
    private val alarmRef = FirebaseDatabase.getInstance().getReference("commands")
    override suspend fun alarmOperations() {
        alarmRef.addListenerForSingleValueEvent(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { child ->
                    child.children.forEach { alarm ->

                        if (alarm.key.equals("cancel") && alarm.key != null) {
                            alarm.children.mapNotNull { cancelAlarm ->
                                cancelAlarm(
                                    AlarmItem(
                                        time = LocalDateTime.now()
                                            .plusSeconds(cancelAlarm.value.toString().toLong()),
                                        cancelAlarm.key.toString()
                                    )
                                )
                                cancelAlarm.ref.removeValue()
                            }
                        } else {
                            if (alarm.value != null) {
                                alarm.children.mapNotNull { schedule ->
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

            }
        })
    }

    private fun scheduleAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::schedule)

    }

    private fun cancelAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::cancel)

    }
}
