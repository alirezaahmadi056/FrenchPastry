package info.alirezaahmadi.frenchpastry.data.remote.mainService

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val url = "https://frenchpastry.ir/api/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS) // TIMEOUT برای برقراری اتصال به سرور
        .readTimeout(20, TimeUnit.SECONDS) // TIMEOUT برای دریافت داده از سرور
        .writeTimeout(30, TimeUnit.SECONDS) // TIMEOUT برای ارسال داده به سرور
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: LoginApiService = retrofit.create(LoginApiService::class.java)
    val mainApiService: MainApiService = retrofit.create(MainApiService::class.java)
    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
    val catsApiService: CatsApiService = retrofit.create(CatsApiService::class.java)
    val pastriesListApiService: PastryListApiService =
        retrofit.create(PastryListApiService::class.java)

}