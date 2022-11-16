package com.example.lessonandroid

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class MyAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            1001,
            Intent(context, MainActivity::class.java),
            flag
        )

        NotificationHandler(context!!)
            .createNotification(
                intent?.extras?.getString(HEADER),
                intent?.extras?.getString(SHORT_MESSAGE),
                pendingIntent,
                intent?.extras?.getString(LONG_MESSAGE) ?: "",
            )

    }

    companion object {
        const val HEADER = "HEADER"
        const val SHORT_MESSAGE = "SHORT_MESSAGE"
        const val LONG_MESSAGE = "LONG_MESSAGE"
    }
}