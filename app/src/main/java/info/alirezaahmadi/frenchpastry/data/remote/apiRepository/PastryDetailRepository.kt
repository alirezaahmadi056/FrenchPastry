package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestFavorite
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

class PastryApiRepository private constructor() {

    companion object {

        private var apiRepository: PastryApiRepository? = null

        val instance: PastryApiRepository
            get() {
                if (apiRepository == null) apiRepository = PastryApiRepository()
                return apiRepository!!
            }

    }

    fun getPastryDetail(
        callbackRequest: CallbackRequest<PastryMainModel>,
        id: Int
    ) {

        RetrofitService.pastryApiService.getPastry(id).enqueue(

            object : Callback<PastryMainModel> {

                override fun onResponse(
                    call: Call<PastryMainModel>,
                    response: Response<PastryMainModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as PastryMainModel
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

                override fun onFailure(call: Call<PastryMainModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun setPastryFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String,
        id: Int
    ) {

        RetrofitService.pastryApiService.setPastryFavorite(id, apiKey, uId, pubKey, action).enqueue(

            object : Callback<RequestFavorite> {

                override fun onResponse(
                    call: Call<RequestFavorite>,
                    response: Response<RequestFavorite>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as RequestFavorite
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

                override fun onFailure(call: Call<RequestFavorite>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface PastryApiService {

    @GET("pastry/{id}")
    fun getPastry(
        @Path(value = "id", encoded = false) ID: Int
    ): Call<PastryMainModel>

    @FormUrlEncoded
    @POST("pastry/{id}/operations/")
    fun setPastryFavorite(
        @Path(value = "id", encoded = false) pastryId: Int,
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("action") action: String
    ): Call<RequestFavorite>

}

interface SendRequests {

    fun startSendFavorite(
        uId: String,
        pubKey: String,
        apiKey: String,
        action: String
    )

}