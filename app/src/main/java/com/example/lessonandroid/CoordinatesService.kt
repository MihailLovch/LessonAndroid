package com.example.lessonandroid

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.util.concurrent.Executor

class CoordinatesService() : Service() {

    private val id = 12
    private var notificationHandler: NotificationHandler? = null
    private var coordinates: String = ""
    private var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        notificationHandler = NotificationHandler(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        coordinates = "latitude: ${location?.latitude} ; longitude: ${location?.longitude}"
        startForeground(id, notificationHandler?.createNotification("Coordinates", coordinates))
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                coordinates = "latitude: ${location?.latitude} ; longitude: ${location?.longitude}"
                startForeground(id, notificationHandler?.createNotification("Coordinates", coordinates))
                handler.postDelayed(this, 5000)
            }
        }
            , 5000
        )

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

}