package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.MainApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelHomeFragment {

    fun getContent(callbackRequest: CallbackRequest<RequestMain>) {

        MainApiRepository.instance.getMainContent(callbackRequest)

    }

}