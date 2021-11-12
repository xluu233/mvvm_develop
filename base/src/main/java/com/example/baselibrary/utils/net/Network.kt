package com.example.baselibrary.utils.net

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import com.example.baselibrary.utils.activity.application


inline val connectivityManager: ConnectivityManager? get() = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

@get:RequiresPermission(ACCESS_NETWORK_STATE)
val isNetworkAvailable: Boolean
  get() = connectivityManager?.run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getNetworkCapabilities(activeNetwork)?.run {
        hasCapability(NET_CAPABILITY_INTERNET) && hasCapability(NET_CAPABILITY_VALIDATED)
      }
    } else {
      @Suppress("DEPRECATION")
      activeNetworkInfo?.isConnectedOrConnecting
    }
  } ?: false

@get:RequiresPermission(ACCESS_NETWORK_STATE)
val isWifiConnected: Boolean
  get() = connectivityManager?.run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getNetworkCapabilities(activeNetwork)?.hasTransport(TRANSPORT_WIFI)
    } else {
      @Suppress("DEPRECATION")
      activeNetworkInfo?.run { isConnected && type == ConnectivityManager.TYPE_WIFI }
    }
  } ?: false

@get:RequiresPermission(ACCESS_NETWORK_STATE)
val isMobileData: Boolean
  get() = connectivityManager?.run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getNetworkCapabilities(activeNetwork)?.hasTransport(TRANSPORT_CELLULAR)
    } else {
      @Suppress("DEPRECATION")
      activeNetworkInfo?.run { isAvailable && type == ConnectivityManager.TYPE_MOBILE }
    }
  } ?: false

@get:RequiresPermission(ACCESS_WIFI_STATE)
inline val isWifiEnabled: Boolean
  get() = (application.getSystemService(Context.WIFI_SERVICE) as WifiManager?)?.isWifiEnabled == true



class NetworkAvailableLiveData @RequiresPermission(ACCESS_NETWORK_STATE) constructor() : LiveData<Boolean>() {

  @RequiresPermission(ACCESS_NETWORK_STATE)
  override fun onActive() {
    super.onActive()
    when {
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
      else ->
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }
  }

  override fun onInactive() {
    super.onInactive()
    connectivityManager?.unregisterNetworkCallback(networkCallback)
  }

  override fun setValue(value: Boolean) {
    if (this.value != value) {
      super.setValue(value)
    }
  }

  private val networkRequest by lazy {
    NetworkRequest.Builder()
      .addTransportType(TRANSPORT_CELLULAR)
      .addTransportType(TRANSPORT_ETHERNET)
      .addTransportType(TRANSPORT_WIFI)
      .build()
  }

  private val networkCallback = object : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        postValue(true)
      }
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        networkCapabilities.run {
          postValue(hasCapability(NET_CAPABILITY_INTERNET) && hasCapability(NET_CAPABILITY_VALIDATED))
        }
      }
    }

    override fun onLost(network: Network) {
      postValue(false)
    }
  }
}
