package kr.co.douchgosum.android.coinradar.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar


class ConnectionStateMonitor(
    context: Context
) : NetworkCallback() {
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun enable() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    fun disable() {
        connectivityManager.unregisterNetworkCallback(this)
    }


    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        println("네트워크 onAvailable")
    }
    override fun onLost(network: Network) {
        super.onLost(network)
        println("네트워크 onLost")
    }

}