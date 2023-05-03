package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
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

                    val data = response.body() as ListPastriesModel

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    else
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString(),
                            data.message
                        )

                }

                override fun onFailure(call: Call<ListPastriesModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun getContentByType(
        callbackRequest: CallbackRequest<ListPastriesModel>,
        type: String
    ) {

        RetrofitService.pastriesListApiService.getPastriesByType(type).enqueue(

            object : Callback<ListPastriesModel> {

                override fun onResponse(
                    call: Call<ListPastriesModel>,
                    response: Response<ListPastriesModel>
                ) {

                    val data = response.body() as ListPastriesModel

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    else
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString(),
                            data.message
                        )

                }

                override fun onFailure(call: Call<ListPastriesModel>, t: Throwable) {

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
    ): Call<ListPastriesModel>

}