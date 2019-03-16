package com.apptimizm.mgs.networking

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import com.apptimizm.mgs.presentation.utils.Constants

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Connection(
    private val mConnectivityManager: ConnectivityManager,
    private val mListener: OnConnectionEstablishedListener
) : AsyncTask<Void, Void, Boolean>() {

    interface OnConnectionEstablishedListener {

        fun onConnectionEstablished(isReachable: Boolean)
    }

    override fun doInBackground(vararg voids: Void): Boolean? {
        val netInfo = mConnectivityManager.activeNetworkInfo
        var isReachable = false

        if (netInfo != null && netInfo.isConnected) {
            // Some sort of connection is open, check if server is reachable
            try {
                val url = URL(Constants.SIMPLE_BASE_URL)
                val urlc = url.openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Android Application")
                urlc.setRequestProperty("Connection", "close")
                urlc.connectTimeout = 10 * 2000
                urlc.connect()
                isReachable = urlc.responseCode == 200
            } catch (e: IOException) {
                //        Timber.e(e.getMessage());
            }

        }
        return isReachable
    }

    override fun onPostExecute(isReachable: Boolean?) {
        super.onPostExecute(isReachable)
        mListener.onConnectionEstablished(isReachable!!)
    }
}



