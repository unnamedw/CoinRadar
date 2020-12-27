package kr.co.douchgosum.android.coinradar

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kr.co.douchgosum.android.coinradar.data.db.AppDatabase
import kr.co.douchgosum.android.coinradar.data.db.TickerSymbol
import kr.co.douchgosum.android.coinradar.data.db.TickerThumbnail
import kr.co.douchgosum.android.coinradar.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //AdMob init
        MobileAds.initialize(this)

        //Firebase Config init
        initRemoteConfig()

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
        
        /**
         * Firebase RemoteConfig 에서 심볼 별 풀네임과 썸네일 이미지를 받아와 Local 에 저장하는 부분이다.
         * 기능별로 함수 분리가 필요하다. (아직 분리하지 않음)
         * */
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d("MyTag", "Config params updated: $updated")
                Log.d("MyTag", "fetch succeed")
                val symbols = remoteConfig.getString("coin_symbols_ko")
                val thumbnails = remoteConfig.getString("coin_thumbnails")
                Log.d("MyTag", "총 글자수: symbols->${symbols.length}, thumbnails->${thumbnails.length}")
                Log.d("MyTag", symbols)

                val symbolListType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
                val symbolListAdapter = get(Moshi::class.java).adapter<Map<String, String>>(symbolListType)
                val symbolList = symbolListAdapter.fromJson(symbols)?.map {
                    TickerSymbol(it.key, it.value)
                } ?: emptyList()

                val thumbnailListType = Types.newParameterizedType(List::class.java, TickerThumbnail::class.java)
                val thumbnailListAdapter = get(Moshi::class.java).adapter<List<TickerThumbnail>>(thumbnailListType)
                Log.d("MyTag", "Json: ${thumbnailListAdapter.fromJson(thumbnails)}")
                val thumbnailList = thumbnailListAdapter.fromJson(thumbnails)?.map {
//                    if (it.symbol.equals("btc", true)) println("thumbTest ${true}")
                    TickerThumbnail(it.symbol, it.image)
                } ?: emptyList()


                val db = get(AppDatabase::class.java)
                GlobalScope.launch {
                    db.tickerSymbolDao().insertAll(symbolList)
                    db.tickerThumnailDao().insertAll(thumbnailList)
                }
                Log.d("MyTag", "Json: ${symbolList}")
                symbolList.filter {
                    it.symbol.equals("xrp", true)
                }.map {
                    Log.d("MyTag", "필터링: $it")
                }
            } else {
                Log.d("MyTag", "fetch failed")
            }
        }
    }

}