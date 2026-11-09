package com.app.kahoji

import android.app.Application
import android.util.Log
import com.app.core_common.utils.SocketHostEventsHandler
import com.app.core_common.utils.deepLinkHandler
import com.app.core_common.utils.fromJson
import com.app.core_navigation.NavigationProvider
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        checkRuntime()
    }


    fun checkRuntime() {
        CoroutineScope(Dispatchers.IO).launch {
            Runtime.getRuntime().run {
                while (true) {
                    Log.d("'fbknfkbnkf", "Max :  " +  maxMemory().toString())
                    Log.d("'fbknfkbnkf", "Free :  " + freeMemory().toString())
                    Log.d("'fbknfkbnkf","Total :  " +  totalMemory().toString())
                    delay(1000.milliseconds)
                }
            }
        }
    }
}