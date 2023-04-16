package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryListApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelListPastryActivity {

    fun getPastries(callbackRequest: CallbackRequest<ListPastriesModel>) {

        PastryListApiRepository.instance.getMainContent(callbackRequest)

    }

}