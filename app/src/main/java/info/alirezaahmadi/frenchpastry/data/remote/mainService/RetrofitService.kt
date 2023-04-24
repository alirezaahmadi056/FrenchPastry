package info.alirezaahmadi.frenchpastry.data.remote.mainService

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val url = "https://frenchpastry.ir/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: LoginApiService = retrofit.create(LoginApiService::class.java)
    val mainApiService: MainApiService = retrofit.create(MainApiService::class.java)
    val catsApiService: CatsApiService = retrofit.create(CatsApiService::class.java)
    val pastriesListApiService: PastryListApiService =
        retrofit.create(PastryListApiService::class.java)

}