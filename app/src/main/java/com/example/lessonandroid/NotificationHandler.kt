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
    ): Notification {
        createChanel()
        val notificationBuilder = NotificationCompat.Builder(context, MY_CHANEL_ID)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(headerText)
            .setContentText(messageText)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setCategory(NotificationCompat.CATEGORY_LOCATION_SHARING)

        return notificationBuilder.build()
    }

    fun updateNotification(
        headerText: String?,
        messageText: String?
    ) {
        val notification = createNotification(headerText,messageText)
        notificationManager.notify(ID,notification)
    }

    private fun createChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                MY_CHANEL_ID,
                context.getString(R.string.my_chanel_name),
                NotificationManager.IMPORTANCE_HIGH,
            ).apply { name = "Fine location" }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        private const val MY_CHANEL_ID = "MY_CHANEL_ID"
        const val ID = 12
    }
}