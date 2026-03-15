package service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LocationService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("LocationService", "Service Running")

        // Future location tracking code will go here

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}