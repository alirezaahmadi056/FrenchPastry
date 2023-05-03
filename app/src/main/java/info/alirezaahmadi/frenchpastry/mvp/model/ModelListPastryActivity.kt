package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryListApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelListPastryActivity(
    private val id: Int,
    private val type: String
) {

    fun getPastries(callbackRequest: CallbackRequest<ListPastriesModel>) {

        if (id != 0)
            PastryListApiRepository.instance.getMainContent(callbackRequest, id)
        else
            PastryListApiRepository.instance.getContentByType(callbackRequest, type)

    }

}