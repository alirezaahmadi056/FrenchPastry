package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryListApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelFavoriteActivity {

    companion object {
        private const val TYPE = ""
    }

    fun getPastries(callbackRequest: CallbackRequest<AllPastriesModel>) {

        PastryListApiRepository.instance.getContentByType(callbackRequest, TYPE)

    }

}