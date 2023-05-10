package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

class UserApiRepository private constructor() {

    companion object {

        private var apiRepository: UserApiRepository? = null

        val instance: UserApiRepository
            get() {
                if (apiRepository == null) apiRepository = UserApiRepository()
                return apiRepository!!
            }

    }

    fun getUserInfo(
        apiKey: String,
        id: String,
        pubKey: String,
        callbackRequest: CallbackRequest<UserInfoData>
    ) {

        RetrofitService.userApiService.getUserInfo(apiKey, id, pubKey).enqueue(

            object : Callback<UserInfoData> {

                override fun onResponse(call: Call<UserInfoData>, response: Response<UserInfoData>) {

                    if (response.isSuccessful) {
                        val data = response.body() as UserInfoData
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString()
                        )

                }

                override fun onFailure(call: Call<UserInfoData>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface UserApiService {

    @GET("auth/heartbeat")
    fun getUserInfo(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String
    ): Call<UserInfoData>

}