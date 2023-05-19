package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ParentCategoryModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

class CatsApiRepository private constructor() {

    companion object {

        private var apiRepository: CatsApiRepository? = null
        const val PASTRY_TYPE = "pastries"
        const val CAKE_TYPE = "cake"

        val instance: CatsApiRepository
            get() {
                if (apiRepository == null) apiRepository = CatsApiRepository()
                return apiRepository!!
            }

    }

    fun getCategories(
        callbackRequest: CallbackRequest<ParentCategoryModel>,
        type: String
    ) {

        RetrofitService.catsApiService.getCats(type).enqueue(

            object : Callback<ParentCategoryModel> {

                override fun onResponse(
                    call: Call<ParentCategoryModel>,
                    response: Response<ParentCategoryModel>
                ) {
                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as ParentCategoryModel
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<ParentCategoryModel>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }

            }

        )

    }

}

interface CatsApiService {

    @GET("cats")
    fun getCats(@Query("pastry_type") type: String): Call<ParentCategoryModel>

}