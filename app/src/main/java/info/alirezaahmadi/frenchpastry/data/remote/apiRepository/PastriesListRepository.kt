package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

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
        callbackRequest: CallbackRequest<ListPastriesModel>
    ) {

        RetrofitService.pastriesListApiService.getPastries().enqueue(

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

    @GET("main")
    fun getPastries(): Call<ListPastriesModel>

}