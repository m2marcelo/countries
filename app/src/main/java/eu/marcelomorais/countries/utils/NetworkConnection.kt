package eu.marcelomorais.countries.utils

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkConnection (private var context: Context){

    fun isConnected() : Boolean {
        var connectivity: ConnectivityManager? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        if (null != connectivity) {
            val network = connectivity.activeNetwork ?: return false
            val activeNetwork = connectivity.getNetworkCapabilities(network) ?: return false

                if(activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                }
                else return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        return false
    }
}

