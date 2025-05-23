package com.example.aiplannerapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

interface NetworkHelper {
    fun isNetworkConnected(): Boolean
}

class DefaultNetworkHelper @Inject constructor(private val context: Context) : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

}