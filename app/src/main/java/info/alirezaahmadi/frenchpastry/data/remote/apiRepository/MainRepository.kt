package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class MainApiRepository private constructor() {

    companion object {

        private var apiRepository: MainApiRepository? = null

        val instance: MainApiRepository
            get() {
                if (apiRepository == null) apiRepository = MainApiRepository()
                return apiRepository!!
            }

    }

    fun getMainContent(
        callbackRequest: CallbackRequest<RequestMain>
    ) {

        RetrofitService.mainApiService.getContent().enqueue(

            object : Callback<RequestMain> {

                override fun onResponse(call: Call<RequestMain>, response: Response<RequestMain>) {

                    val data = response.body() as RequestMain

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

                override fun onFailure(call: Call<RequestMain>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface MainApiService {

    @GET("main")
    fun getContent(): Call<RequestMain>

}