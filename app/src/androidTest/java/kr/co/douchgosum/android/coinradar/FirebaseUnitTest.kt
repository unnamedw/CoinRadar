package kr.co.douchgosum.android.coinradar

import android.util.Log
import android.widget.Toast
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.junit.Test

class FirebaseUnitTest {
    @Test
    fun main() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
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
                } else {
                    Log.d("MyTag", "fetch failed")
                }

            }
        val string = remoteConfig.getString("lottery_data")
        Log.d("MyTag", string)

        val jsonArray = JSONArray(string)
        val winningLotteries = List(jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(it)
            WinningLottery(
                jsonObject.getInt("년도"),
                jsonObject.getInt("회차"),
                jsonObject.getString("추첨일"),
                listOf(
                    Prize(1, jsonObject.getString("당첨금액1"), jsonObject.getString("당첨자수1")),
                    Prize(2, jsonObject.getString("당첨금액2"), jsonObject.getString("당첨자수2")),
                    Prize(3, jsonObject.getString("당첨금액3"), jsonObject.getString("당첨자수3")),
                    Prize(4, jsonObject.getString("당첨금액4"), jsonObject.getString("당첨자수4")),
                    Prize(5, jsonObject.getString("당첨금액5"), jsonObject.getString("당첨자수5"))
                ),
                listOf(
                    jsonObject.getInt("1"),
                    jsonObject.getInt("2"),
                    jsonObject.getInt("3"),
                    jsonObject.getInt("4"),
                    jsonObject.getInt("5"),
                    jsonObject.getInt("6")
                ),
                jsonObject.getInt("보너스")) }
        winningLotteries.forEach {
            Log.d("MyTag", it.prizes[2].toString())
        }
        Thread.sleep(1000)
    }
}

data class WinningLottery(
    val year: Int,
    val rounding: Int,
    val drawDate: String,
    val prizes: List<Prize>,
    val winningNumber: List<Int>,
    val bonusNumber: Int
)
data class Prize(
    val rank: Int,
    val prizeMoney: String,
    val numberOfWinners: String
)