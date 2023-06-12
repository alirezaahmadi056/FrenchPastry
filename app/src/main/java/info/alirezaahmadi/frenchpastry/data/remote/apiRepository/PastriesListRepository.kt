package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

class PastryListApiRepository private constructor() {

    companion object {

        private var apiRepository: PastryListApiRepository? = null

        val instance: PastryListApiRepository
            get() {
                if (apiRepository == null) apiRepository = PastryListApiRepository()
                return apiRepository!!
            }

    }

    fun getMainContent(
        callbackRequest: CallbackRequest<ListPastriesModel>,
        id: Int
    ) {

        RetrofitService.pastriesListApiService.getPastries(id, true).enqueue(

            object : Callback<ListPastriesModel> {

                override fun onResponse(
                    call: Call<ListPastriesModel>,
                    response: Response<ListPastriesModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as ListPastriesModel
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

                override fun onFailure(call: Call<ListPastriesModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun getContentByType(
        callbackRequest: CallbackRequest<AllPastriesModel>,
        type: String
    ) {

        RetrofitService.pastriesListApiService.getPastriesByType(type).enqueue(

            object : Callback<AllPastriesModel> {

                override fun onResponse(
                    call: Call<AllPastriesModel>,
                    response: Response<AllPastriesModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as AllPastriesModel
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

                override fun onFailure(call: Call<AllPastriesModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun getFavoriteContent(
        apiKey: String,
        pubKey: String,
        id: String,
        callbackRequest: CallbackRequest<AllPastriesModel>
    ) {

        RetrofitService.pastriesListApiService.getFavoritePastries(
            apiKey,
            id,
            pubKey,
            true
        ).enqueue(

            object : Callback<AllPastriesModel> {

                override fun onResponse(
                    call: Call<AllPastriesModel>,
                    response: Response<AllPastriesModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as AllPastriesModel
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

                override fun onFailure(call: Call<AllPastriesModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface PastryListApiService {

    @GET("cat/{id}")
    fun getPastries(
        @Path(value = "id", encoded = false) ID: Int,
        @Query("has_pastries") hasPastries: Boolean
    ): Call<ListPastriesModel>

    @GET("pastries")
    fun getPastriesByType(
        @Query("orderBy") type: String
    ): Call<AllPastriesModel>

    @GET("pastries")
    fun getFavoritePastries(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Query("favorite") favorite: Boolean
    ): Call<AllPastriesModel>

}