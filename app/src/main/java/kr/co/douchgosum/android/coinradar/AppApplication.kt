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
import kr.co.douchgosum.android.coinradar.di.*
import kr.co.douchgosum.android.coinradar.utils.ConnectionStateMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //AdMob init
        MobileAds.initialize(this)

        //Firebase Config init
//        initRemoteConfig()

        //Koin init
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                dbModule,
                retrofitModule,
                apiModule,
                viewModelModule
            ))
        }
    }

    private fun initRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d("MyTag", "Config params updated: $updated")
                Log.d("MyTag", "fetch succeed")
                val string = remoteConfig.getString("lottery_data")
                Log.d("MyTag", "총 글자수: ${string.length}")
            } else {
                Log.d("MyTag", "fetch failed")
            }
        }
    }

}