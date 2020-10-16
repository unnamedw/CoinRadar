package kr.co.douchgosum.android.coinradar.apitest

import android.util.Log
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

//    @Test
fun okhttpJsonTest() {
    val baseUrl = "https://api-cloud.huobi.co.kr/v1/common/symbols"
    val urlBuilder = baseUrl.toHttpUrlOrNull()?.newBuilder()
//            ?.apply {
//            addQueryParameter("markets", "krw-btt")
//        }
    val requestUrl = urlBuilder?.build().toString()
    println("url: $requestUrl")
    val okHttpClient = OkHttpClient()
    val request = Request.Builder().url(requestUrl).build()
    val response = okHttpClient.newCall(request).execute()

    val responseBody = response.body?.string() ?: ""
    Log.d("MyTest", responseBody)

    val responseJsonObject = JSONObject(responseBody)
    val arr = responseJsonObject.getJSONArray("data")
    val set = mutableSetOf<String>()
    for (i in 0 until arr.length()) {
        val objString = arr.getString(i)
        val obj = JSONObject(objString)
        set.add(obj.getString("quote-currency"))
    }
    println(set.toList())

}