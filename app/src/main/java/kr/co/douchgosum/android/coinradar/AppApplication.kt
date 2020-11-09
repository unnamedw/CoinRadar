package kr.co.douchgosum.android.coinradar

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.ads.MobileAds
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kr.co.douchgosum.android.coinradar.di.apiModule
import kr.co.douchgosum.android.coinradar.di.dbModule
import kr.co.douchgosum.android.coinradar.di.uiModule
import kr.co.douchgosum.android.coinradar.utils.ConnectionStateMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //AdMob Init
        MobileAds.initialize(this)

        //DI Init
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                apiModule,
                dbModule,
                uiModule
            ))
        }

        //Network state monitoring
        val monitor = ConnectionStateMonitor(this).apply { enable() }

    }



}