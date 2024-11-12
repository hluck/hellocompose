package com.hluck.checknet.helper

sealed class ConnectionStatus {
    object Available : ConnectionStatus()
    object Unavailable : ConnectionStatus()
}