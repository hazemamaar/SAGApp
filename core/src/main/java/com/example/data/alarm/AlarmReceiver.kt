package com.example.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import com.example.core.R
import timber.log.Timber

class AlarmReceiver: BroadcastReceiver() {
    lateinit var mPlayer: MediaPlayer
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        Log.e("hazemaa", "onReceive: time to wake up"+message )
        mPlayer =MediaPlayer.create(context, R.raw.alarm)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        try {
            mPlayer.prepare()
        }catch (e:java.lang.Exception) {

            Timber.tag("hazemaa").e(e.localizedMessage?.toString())
        }
        mPlayer.start()
    }
}