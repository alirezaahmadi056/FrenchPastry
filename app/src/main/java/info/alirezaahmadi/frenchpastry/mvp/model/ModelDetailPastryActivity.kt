package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelDetailPastryActivity(private val id: Int) {

    fun getCats(callbackRequest: CallbackRequest<PastryMainModel>) {
        PastryApiRepository.instance.getPastryDetail(callbackRequest, id)
    }

}