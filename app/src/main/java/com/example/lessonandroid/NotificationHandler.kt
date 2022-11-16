package com.example.lessonandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(private val context: Context) {

    fun createNotification(headerText: String?, messageText: String?,intent:PendingIntent, longText: String = "") {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(MY_CHANEL_ID) == null) {
                val notificationChannel = NotificationChannel(
                    MY_CHANEL_ID,
                    context.getString(R.string.my_chanel_name),
                    NotificationManager.IMPORTANCE_HIGH
                ).apply { description = "" }
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
        val notificationBuilder = NotificationCompat.Builder(context, MY_CHANEL_ID)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(headerText)
            .setContentText(messageText)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(intent)


        if (longText.isNotEmpty()) {
            notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(longText))
        }
        notificationManager.notify(101, notificationBuilder.build())
    }

    companion object {
        private const val MY_CHANEL_ID = "MY_CHANEL_ID"
    }
}
