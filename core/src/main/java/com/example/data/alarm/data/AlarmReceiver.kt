package com.example.data.alarm.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.e("hazemaa", "onReceive: time to wake up" )
    }
}