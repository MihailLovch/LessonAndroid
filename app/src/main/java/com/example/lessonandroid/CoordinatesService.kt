package com.example.lessonandroid

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.concurrent.Executor

class CoordinatesService() : Service() {

    private var notificationHandler: NotificationHandler? = null
    private var coordinates: String = ""
    private var locationManager: LocationManager? = null
    private var location: Location? = null

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        notificationHandler = NotificationHandler(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        coordinates = "latitude: ${location?.latitude} ; longitude: ${location?.longitude}"

        startForeground(NotificationHandler.ID, notificationHandler?.createNotification("Coordinates", coordinates))
    }


    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            DELAY,
            MIN_DISTANCE
        ) {
            coordinates = "latitude: ${it.latitude} ; longitude: ${it.longitude}"
            notificationHandler?.updateNotification("Coordinates",coordinates)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationHandler = null
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val DELAY: Long = 5000
        const val MIN_DISTANCE = 10f
    }
}