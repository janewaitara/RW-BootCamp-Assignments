package com.janewaitara.movieapp.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities


/**
 * We need the ConnectivityManager to check the current network info and its capabilities*/

class NetworkStatusChecker(private val connectivityManager : ConnectivityManager?) {

    /**
     * Check if there is a good connection and if there is, you execute a certain action
     * */

    inline fun performIfConnectedToInternet(action: ()-> Unit){
        if(hasInternetConnection()){
            action()
        }
    }
    inline fun performSearchIfConnectedToInternet(actionNoNetworkAvailable: () -> Unit, action: ()-> Unit){
        if(hasInternetConnection()){
            action()
        }else{
            actionNoNetworkAvailable()
        }
    }

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false //fetch capabilities to check if its connected to a mobile, wifi or a vpn
        return  capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}