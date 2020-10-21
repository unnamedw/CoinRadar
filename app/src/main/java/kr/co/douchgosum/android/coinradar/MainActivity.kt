package kr.co.douchgosum.android.coinradar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kr.co.douchgosum.android.coinradar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CustomAppTheme)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val bottomNav = binding.bottomNav

        //init bottom navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(bottomNav.menu)
        bottomNav
            .setupWithNavController(navController)

        //전면광고 표시
//        showAd(R.string.admob_interstitialad_id)
    }


    /**
     * Fragment 의 백스택이 남아있지 않은 경우에만 앱 종료
     * */
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    /**
     * 광고표시
     * */
    private fun showAd(adId: Int) {
        val adResource = resources.getString(adId)
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = adResource
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("MyTag", "The interstitial wasn't loaded yet.")
                }
            }
        }
    }

    /**
     * Remote config 초기화
     * */
    private fun initRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
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