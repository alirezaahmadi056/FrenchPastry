package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
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
        id: Int,
        apiKey: String,
        uId: String,
        pubKey: String,
    ) {

        RetrofitService.pastryApiService.getPastry(
            id,
            uId,
            pubKey,
            apiKey
        ).enqueue(

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

    fun setPastryComments(
        apiKey: String,
        uId: String,
        pubKey: String,
        post_id: Int,
        content: String,
        rate: Float,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.pastryApiService.setPastryComment(
            apiKey, uId, pubKey, post_id, content, rate
        ).enqueue(

            object : Callback<DefaultModel> {

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

interface PastryApiService {

    @GET("pastry/{id}")
    fun getPastry(
        @Path(value = "id", encoded = false) ID: Int,
        @Header("app-device-uid") uId: String,
        @Header("app-public-key") pubKey: String,
        @Header("app-api-key") apiKey: String
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

    @FormUrlEncoded
    @POST("comment/")
    fun setPastryComment(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("post_id") post_id: Int,
        @Field("content") content: String,
        @Field("rate") rate: Float
    ): Call<DefaultModel>

}

interface SendRequests {

    fun startSendFavorite(
        uId: String,
        pubKey: String,
        apiKey: String,
        action: String
    )

    fun sendComment(
        uId: String,
        pubKey: String,
        apiKey: String,
        content: String,
        rate: Float,
        postId: Int
    )

}