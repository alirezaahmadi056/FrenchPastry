package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ParentCategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class PastryApiRepository private constructor() {

    companion object {

        private var apiRepository: PastryApiRepository? = null
        const val PASTRY_TYPE = "pastries"
        const val CAKE_TYPE = "cake"

        val instance: PastryApiRepository
            get() {
                if (apiRepository == null) apiRepository = PastryApiRepository()
                return apiRepository!!
            }

    }

}

interface PastryApiService {

    @GET("cats")
    fun getCats(@Query("pastry_type") type: String): Call<ParentCategoryModel>

}