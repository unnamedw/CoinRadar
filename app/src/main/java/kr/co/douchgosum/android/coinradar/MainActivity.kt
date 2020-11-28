package kr.co.douchgosum.android.coinradar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.douchgosum.android.coinradar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CustomAppTheme)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        bottomNav = binding.bottomNav

        //Init bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(bottomNav.menu)
        bottomNav.setupWithNavController(navController)

        //네이게이션 메뉴 아이템 커스텀
        val bottomNavPosition = 0 //0으로 고정
        val menuItemPosition = 2
        val menuView = bottomNav.getChildAt(bottomNavPosition) as BottomNavigationMenuView
        val itemViewAtRadarViewPosition = menuView.getChildAt(menuItemPosition) as BottomNavigationItemView

        //RadarView 커스텀
//        val newMenuItemView = RadarView(this)
//        newMenuItemView.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT)
        val newMenuItemView = LayoutInflater.from(this).inflate(R.layout.bottom_menu_radaritem, bottomNav, false)
        itemViewAtRadarViewPosition.addView(newMenuItemView)
//        bottomNav.setOnNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.bottom_menu_item3 -> {
//                    showBottomSheet()
//                    false
//                }
//                else -> NavigationUI.onNavDestinationSelected(it, navController)
//            }
//        }

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



//    private fun showBottomSheet() {
//        val bottomSheetDialog = com.google.android.material.bottomsheet.BottomSheetDialog(this@MainActivity)
//        bottomSheetDialog.setContentView(R.layout.bottom_content)
//        bottomSheetDialog.show()
//    }


}