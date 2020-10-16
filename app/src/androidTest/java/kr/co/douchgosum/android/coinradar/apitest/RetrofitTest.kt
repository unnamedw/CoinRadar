package kr.co.douchgosum.android.coinradar.apitest

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val BASE_URL = "https://jsonplaceholder.typicode.com/"
private val retrofit = Retrofit.Builder()
.addConverterFactory(ScalarsConverterFactory.create())
.baseUrl(BASE_URL)
.build()

object TestApi {
    val retrofitService: TestApiService by lazy {
        retrofit.create(TestApiService::class.java)
    }
}

interface TestApiService {

    @GET("users")
    suspend fun testRequest(
        @Query("id") id: String? = null,
        @Query("name") name: String? = null
    ): String
}