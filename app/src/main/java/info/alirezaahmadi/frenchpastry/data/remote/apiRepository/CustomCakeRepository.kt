package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import java.io.File

class CakeApiRepository private constructor() {

    companion object {

        private var apiRepository: CakeApiRepository? = null

        val instance: CakeApiRepository
            get() {
                if (apiRepository == null) apiRepository = CakeApiRepository()
                return apiRepository!!
            }

    }

    fun sendCake(
        callbackRequest: CallbackRequest<DefaultModel>,
        apiKey: String,
        id: String,
        pubKey: String,
        files: List<File>,
        desc: RequestBody,
        weight: RequestBody,
        floor: RequestBody
    ) {

        //val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        //val imagePart: MultipartBody.Part =
        //    MultipartBody.Part.createFormData("image", file.name, requestFile)

        val imageParts: List<MultipartBody.Part> = files.map { imageFile ->
            val imageRequestBody: RequestBody =
                RequestBody.create(MediaType.parse("image/*"), imageFile)
            MultipartBody.Part.createFormData("files[]", imageFile.name, imageRequestBody)
        }

        RetrofitService.cakeApiService.sendCakes(
            apiKey,
            id,
            pubKey,
            imageParts,
            desc,
            weight,
            floor
        ).enqueue(

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

interface CakeApiService {

    @Multipart
    @POST("cake")
    fun sendCakes(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Part file: List<MultipartBody.Part>,
        @Part("description") description: RequestBody,
        @Part("weight") weight: RequestBody,
        @Part("floor") floor: RequestBody
    ): Call<DefaultModel>

}