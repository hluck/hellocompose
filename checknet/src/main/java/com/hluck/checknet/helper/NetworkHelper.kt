package com.hluck.checknet.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

val Context.currentConnectivityStatus: ConnectionStatus
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityStatus(connectivityManager)
    }

private fun getCurrentConnectivityStatus(
    connectivityManager: ConnectivityManager
): ConnectionStatus {
    val connected = connectivityManager.allNetworks.any { network ->
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
    return if (connected) ConnectionStatus.Available else ConnectionStatus.Unavailable
}


fun Context.observerConnectivityAsFlow() = callbackFlow<ConnectionStatus> {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = NetworkCallback { status -> trySend(status) }
    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest,callback)
    val currentState = getCurrentConnectivityStatus(connectivityManager)
    trySend(currentState)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}


fun NetworkCallback(
    callback:(ConnectionStatus) -> Unit
) :ConnectivityManager.NetworkCallback{
    return object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: android.net.Network) {
//            super.onAvailable(network)
            callback(ConnectionStatus.Available)
        }

        override fun onLost(network: android.net.Network) {
//            super.onLost(network)
            callback(ConnectionStatus.Unavailable)
        }
    }
}