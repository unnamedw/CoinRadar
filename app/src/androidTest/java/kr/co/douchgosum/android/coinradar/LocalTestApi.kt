package kr.co.douchgosum.android.coinradar

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kr.co.douchgosum.android.coinradar.upbit.OrderBook
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val BASE_URL = "http://10.0.2.2:8080/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object LocalTestApi {
    val retrofitService: TestApiService by lazy {
        retrofit.create(TestApiService::class.java)
    }
}

//interface TestApiService {
//    @FormUrlEncoded
//    @POST("/")
//    suspend fun postToLocal(
//        @Field("name", encoded = true) name: String,
//        @Field("age", encoded = true) age: String
//    ): String
//
//}

interface TestApiService {

    @POST("/")
    suspend fun postToLocal(
        @Query("markets") markets: List<String>
    ): String
}

data class Person(
    val name: String,
    val age: String
)

