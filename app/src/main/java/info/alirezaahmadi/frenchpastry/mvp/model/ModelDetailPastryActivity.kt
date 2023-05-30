package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestFavorite
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelDetailPastryActivity(private val id: Int) {

    companion object {

        const val ACTION_FAVORITE = "favorite"
        const val ACTION_UN_FAVORITE = "unfavorite"

    }

    fun getDetailPastry(callbackRequest: CallbackRequest<PastryMainModel>) {
        PastryApiRepository.instance.getPastryDetail(callbackRequest, id)
    }

    fun setPastryFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String
    ) {
        PastryApiRepository.instance.setPastryFavorite(
            callbackRequest,
            apiKey,
            uId,
            pubKey,
            action,
            id
        )
    }

}