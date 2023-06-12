package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryListApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelFavoriteActivity {

    fun getPastries(
        apiKey: String,
        pubKey: String,
        id: String,
        callbackRequest: CallbackRequest<AllPastriesModel>
    ) {

        PastryListApiRepository.instance.getFavoriteContent(
            apiKey,
            pubKey,
            id,
            callbackRequest
        )

    }

}