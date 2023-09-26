package com.example.apicalltask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (isNetworkAvailable(context)) {
            // Network is available, retry network operations here
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        context?.let {
            val connectivityManager =
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val networkInfo = connectivityManager.getNetworkCapabilities(networkCapabilities)
                return networkInfo?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        ?: false || networkInfo?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        ?: false
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo?.isConnected == true
            }
        }
        return false
    }
}
