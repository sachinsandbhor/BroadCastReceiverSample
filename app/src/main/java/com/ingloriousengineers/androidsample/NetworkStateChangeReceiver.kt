package com.ingloriousengineers.androidsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.LocalBroadcastManager

class NetworkStateChangeReceiver : BroadcastReceiver() {

    companion object {
        const val NETWORK_AVAILABLE_ACTION: String = "com.ingloriousengineers.androidsample.NetworkState"
        const val IS_NETWORK_AVAILABLE: String = "isNetworkAvailable"
    }


    override fun onReceive(context: Context, intent: Intent) {
        val networkIntent = Intent(NETWORK_AVAILABLE_ACTION)
        networkIntent.putExtra(IS_NETWORK_AVAILABLE, isNetworkAvailable(context))
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkIntent)
    }

    private fun isNetworkAvailable(context : Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager == null) {
            return false
        }
        val info = connectivityManager.activeNetworkInfo
        if(info != null) {
            return info.isConnected
        }
        return false
    }
}
