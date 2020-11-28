package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.squareup.moshi.Moshi
import kr.co.douchgosum.android.coinradar.data.db.CurrencySymbolDao
import kr.co.douchgosum.android.coinradar.data.db.TickerThumbnailDao
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import org.koin.java.KoinJavaComponent.get

/**
 * 추상 클래스는 생성자를 통한 의존성 주입이 불가능하여 필드 주입을 하였음.
 * 필드 주입은 권장되지 않는다고 알고 있는데 향후 수정할 수 있으면 수정.
 *
 * */
abstract class Repository(
    private val context: Context = get(Context::class.java),
    val moshi: Moshi = get(Moshi::class.java),
    val tickerDao: TickerDao = get(TickerDao::class.java),
    val currencySymbolDao: CurrencySymbolDao = get(CurrencySymbolDao::class.java),
    val tickerThumbnailDao: TickerThumbnailDao = get(TickerThumbnailDao::class.java)
) {

    /**
     * 네트워크 상태 확인
     * */
    fun isNetworkAvailable(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //마시멜로 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        //마시멜로 미만
        else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
        return result
    }

}