package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import android.util.Log
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestSendPhone
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestVerifyCode
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.net.SocketTimeoutException

class LoginApiRepository private constructor() {

    companion object {

        private var apiRepository: LoginApiRepository? = null

        val instance: LoginApiRepository
            get() {
                if (apiRepository == null) apiRepository = LoginApiRepository()
                return apiRepository!!
            }

    }

    fun sendPhoneAuth(
        id: String,
        key: String,
        phone: String,
        callbackRequest: CallbackRequest<RequestSendPhone>
    ) {

        RetrofitService.apiService.sendPhone(
            id,
            key,
            phone
        ).enqueue(

            object : Callback<RequestSendPhone> {

                override fun onResponse(
                    call: Call<RequestSendPhone>,
                    response: Response<RequestSendPhone>
                ) {
                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestSendPhone
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<RequestSendPhone>, t: Throwable) {
                    if (t is SocketTimeoutException) {
                        callbackRequest.onError("تایم اوت")
                    } else {
                        callbackRequest.onError(t.message.toString())
                    }
                }

            }

        )

    }

    fun verifyCodeAuth(
        code: String,
        phone: String,
        callbackRequest: CallbackRequest<RequestVerifyCode>
    ) {

        RetrofitService.apiService.verifyCode(code, phone).enqueue(

            object : Callback<RequestVerifyCode> {

                override fun onResponse(
                    call: Call<RequestVerifyCode>,
                    response: Response<RequestVerifyCode>
                ) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestVerifyCode
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<RequestVerifyCode>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }

            }

        )

    }

    fun editUser(
        apiKey: String,
        id: String,
        pubKey: String,
        fullName: String,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.apiService.editUser(apiKey, id, pubKey, fullName).enqueue(

            object : Callback<DefaultModel> {

                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as DefaultModel
                        )
                    else {
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

interface LoginApiService {

    @FormUrlEncoded
    @POST("auth/phone/login")
    fun sendPhone(
        @Header("app-device-uid") id: String,
        @Header("app-public-key") key: String,
        @Field("phone") phone: String
    ): Call<RequestSendPhone>

    @FormUrlEncoded
    @POST("auth/phone/login/verify")
    fun verifyCode(
        @Field("code") code: String,
        @Field("phone") phone: String
    ): Call<RequestVerifyCode>

    @FormUrlEncoded
    @POST("user/profile")
    fun editUser(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("fullname") fullName: String
    ): Call<DefaultModel>

}