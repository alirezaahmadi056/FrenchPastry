package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestSendPhone
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class LoginApiRepository private constructor() {

    companion object {

        private var apiRepository: LoginApiRepository? = null

        val instance: LoginApiRepository
            get() {
                if (apiRepository == null) apiRepository = LoginApiRepository()
                return apiRepository!!
            }

    }

    fun sendPhoneAuth(phone: String) {

        RetrofitService.apiService.sendPhone(phone).enqueue(

            object : Callback<RequestSendPhone> {

                override fun onResponse(
                    call: Call<RequestSendPhone>,
                    response: Response<RequestSendPhone>
                ) {

                }

                override fun onFailure(call: Call<RequestSendPhone>, t: Throwable) {

                }

            }

        )

    }

}

interface LoginApiService {

    @POST("auth/phone/login")
    fun sendPhone(
        @Query("phone") phone: String
    ): Call<RequestSendPhone>

}