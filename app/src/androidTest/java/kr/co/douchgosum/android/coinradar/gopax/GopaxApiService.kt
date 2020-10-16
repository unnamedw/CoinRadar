package kr.co.douchgosum.android.coinradar.gopax

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.gopax.co.kr"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GopaxApiService {
    // 자산목록 조회
    @GET("/assets")
    suspend fun getAssets():
            String
//            List<GopaxAsset>


    // 거래쌍 조회
    @GET("/trading-pairs")
    fun getTradingPairs():
            List<GopaxPair>

}

object GopaxApi {
    val retrofitService : GopaxApiService by lazy {
        retrofit.create(
            GopaxApiService::class.java) }
}

