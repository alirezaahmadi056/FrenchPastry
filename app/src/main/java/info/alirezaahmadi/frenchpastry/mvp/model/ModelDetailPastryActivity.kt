package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestFavorite
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelDetailPastryActivity(private val id: Int) {

    companion object {

        const val ACTION_FAVORITE = "favorite"
        const val ACTION_UN_FAVORITE = "unfavorite"

    }

    fun getDetailPastry(
        callbackRequest: CallbackRequest<PastryMainModel>,
        uId: String,
        pubKey: String,
        apiKey: String
    ) {
        PastryApiRepository.instance.getPastryDetail(
            callbackRequest, id, apiKey, uId, pubKey
        )
    }

    fun setPastryFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String
    ) {
        PastryApiRepository.instance.setPastryFavorite(
            callbackRequest, apiKey, uId, pubKey, action, id
        )
    }

    fun setPastryComment(
        apiKey: String,
        uId: String,
        pubKey: String,
        post_id: Int,
        content: String,
        rate: Float,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {
        PastryApiRepository.instance.setPastryComments(
            apiKey, uId, pubKey, post_id, content, rate, callbackRequest
        )
    }

}