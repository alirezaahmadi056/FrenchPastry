package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryCategoryModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

class PastryCatApiRepository private constructor() {

    companion object {

        private var apiRepository: PastryCatApiRepository? = null

        val instance: PastryCatApiRepository
            get() {
                if (apiRepository == null) apiRepository = PastryCatApiRepository()
                return apiRepository!!
            }

    }

    fun getCategories(callbackRequest: CallbackRequest<PastryCategoryModel>) {

        RetrofitService.catsApiService.getCats().enqueue(

            object : Callback<PastryCategoryModel> {

                override fun onResponse(
                    call: Call<PastryCategoryModel>,
                    response: Response<PastryCategoryModel>
                ) {
                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as PastryCategoryModel
                        )
                    else {
                        val data = response.body() as PastryCategoryModel
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString(),
                            data.message
                        )
                    }
                }

                override fun onFailure(call: Call<PastryCategoryModel>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }

            }

        )

    }

}

interface PastryCatApiService {

    @GET("cats")
    fun getCats(): Call<PastryCategoryModel>

}