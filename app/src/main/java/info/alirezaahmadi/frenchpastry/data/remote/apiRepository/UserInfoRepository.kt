package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

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

                override fun onResponse(
                    call: Call<UserInfoData>,
                    response: Response<UserInfoData>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as UserInfoData
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<UserInfoData>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun setUserInfo(
        apiKey: String,
        uId: String,
        pubKey: String,
        fullName: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.userApiService.setUserInfo(
            apiKey, uId, pubKey,
            fullName, email, day, month, year, sex
        ).enqueue(

            object :Callback<DefaultModel>{

                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as DefaultModel
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<DefaultModel>, t: Throwable) {

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

    @FormUrlEncoded
    @POST("user/profile")
    fun setUserInfo(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("fullname") fullName: String,
        @Field("email") email: String,
        @Field("day") day: String,
        @Field("month") month: String,
        @Field("year") year: String,
        @Field("sex") sex: Int
    ): Call<DefaultModel>

}

interface StartSetUserInfo {

    fun startSetUser(
        name: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int
    )

}