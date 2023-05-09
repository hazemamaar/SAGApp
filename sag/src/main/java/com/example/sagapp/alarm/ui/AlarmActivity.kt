package com.example.sagapp.alarm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.sagapp.R

import java.util.Locale
import java.util.Objects

class AlarmActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
//        val scheduler = AndroidAlarmScheduler(this)
        val btn = findViewById<Button>(R.id.btn_alarm)
//       val alarmItem= AlarmItem(LocalDateTime.now().plusSeconds(60.toLong()),"wake up")
//        Log.e("alarmItem", "onCreate: "+alarmItem.toString() )
//
//        btn.setOnClickListener {
//            alarmItem.let (scheduler::schedule)
//        }
        // initializing variables of list view with their ids.
//        outputTV = findViewById(R.id.idTVOutput)
//        micIV = findViewById(R.id.idIVMic)

        // on below line we are adding on click
        // listener for mic image view.
        btn.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this@AlarmActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.
                Toast
                    .makeText(
                        this@AlarmActivity, Objects.requireNonNull(res)[0],
                        Toast.LENGTH_SHORT
                    )
                    .show()

            }
        }
    }
}