package com.example.lessonandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings


class PlaneReceiver(private val on: (() ->Unit), private val off: (()->Unit)) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Settings.Global.getInt(context?.contentResolver,
           Settings.Global.AIRPLANE_MODE_ON, 0) == 0){
            on()
        }else{
            off()
        }
    }
}