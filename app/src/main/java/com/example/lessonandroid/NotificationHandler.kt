package com.example.lessonandroid

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotification(
        headerText: String?,
        messageText: String?,
        longText: String = "",
    ): Notification {
        createChanel()
        val notificationBuilder = NotificationCompat.Builder(context, MY_CHANEL_ID)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(headerText)
            .setContentText(messageText)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

        return notificationBuilder.build()
    }

    private fun createChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                MY_CHANEL_ID,
                context.getString(R.string.my_chanel_name),
                NotificationManager.IMPORTANCE_HIGH,
            ).apply { description = "Fine location" }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        private const val MY_CHANEL_ID = "MY_CHANEL_ID"
    }
}