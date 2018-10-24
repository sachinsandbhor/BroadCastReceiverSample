package com.ingloriousengineers.androidsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity: AppCompatActivity() {

    lateinit var networkStateChangeReceiver: NetworkStateChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStateChangeReceiver = NetworkStateChangeReceiver()
        registerReceiver(networkStateChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        val intentFilter = IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isNetworkAvailable = intent?.getBooleanExtra(NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE, false)
                val networkStatus = if (isNetworkAvailable!!) "connected" else "disconnected"
                Toast.makeText(context, networkStatus, Toast.LENGTH_LONG).show()
            }
        }, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkStateChangeReceiver)
    }
}