package com.example.sagapp.alarm.domain

import com.example.data.alarm.data.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}