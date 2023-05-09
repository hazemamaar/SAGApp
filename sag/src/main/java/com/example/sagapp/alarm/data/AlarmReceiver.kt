package com.example.sagapp.alarm.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Alarm triggered: $message")
        Log.e("Alarm triggered", "onReceive: "+message )
    }
}