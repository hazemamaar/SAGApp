package com.example.features.whatsapp

import android.content.Context
import android.content.Intent
import android.net.Uri

class SendMessage {
    suspend fun shareText(context: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "https://api.whatsapp.com/send?phone=" + "201117274756" +
                        "&text=" + "Hello"
            )
        )
        context.startActivity(intent)
    }


}