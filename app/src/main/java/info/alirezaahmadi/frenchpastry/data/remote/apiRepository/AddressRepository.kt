package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.data.remote.ext.ErrorUtils
import info.alirezaahmadi.frenchpastry.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

class AddressApiRepository private constructor() {

    companion object {

        private var apiRepository: AddressApiRepository? = null

        val instance: AddressApiRepository
            get() {
                if (apiRepository == null) apiRepository = AddressApiRepository()
                return apiRepository!!
            }

    }

    fun getAddresses(
        apiKey: String,
        id: String,
        pubKey: String,
        callbackRequest: CallbackRequest<Address>
    ) {

        RetrofitService.addressApiService.getAddress(apiKey, id, pubKey).enqueue(

            object : Callback<Address> {

                override fun onResponse(call: Call<Address>, response: Response<Address>) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as Address
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<Address>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }

            }

        )

    }

    fun deleteAddresses(
        apiKey: String,
        id: String,
        pubKey: String,
        addressID: Int,
        callbackRequest: CallbackRequest<Address>
    ) {

        RetrofitService.addressApiService.deleteAddress(
            id,
            pubKey,
            apiKey,
            addressID
        ).enqueue(

            object : Callback<Address> {

                override fun onResponse(
                    call: Call<Address>,
                    response: Response<Address>
                ) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as Address
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<Address>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }

            }

        )

    }

    fun editAddresses(
        apiKey: String,
        id: String,
        pubKey: String,
        address: String,
        phone: String,
        receiver: String,
        addressID: String,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.addressApiService.editAddress(
            id,
            pubKey,
            apiKey,
            address,
            receiver,
            phone,
            addressID
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

    fun addAddresses(
        apiKey: String,
        id: String,
        pubKey: String,
        address: String,
        phone: String,
        receiver: String,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.addressApiService.addAddress(
            id,
            pubKey,
            apiKey,
            address,
            receiver,
            phone
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

interface AddressApiService {

    @GET("user/address")
    fun getAddress(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String
    ): Call<Address>

    @DELETE("user/address/{id}")
    fun deleteAddress(
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Header("app-api-key") apiKey: String,
        @Path(value = "id", encoded = false) ID: Int
    ): Call<Address>

    @FormUrlEncoded
    @POST("user/address/{id}")
    fun editAddress(
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Header("app-api-key") apiKey: String,
        @Field("address") address: String,
        @Field("receiver") receiver: String,
        @Field("phone") phone: String,
        @Path(value = "id", encoded = false) ID: String
    ): Call<DefaultModel>

    @FormUrlEncoded
    @POST("user/address")
    fun addAddress(
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Header("app-api-key") apiKey: String,
        @Field("address") address: String,
        @Field("receiver") receiver: String,
        @Field("phone") phone: String
    ): Call<DefaultModel>

}