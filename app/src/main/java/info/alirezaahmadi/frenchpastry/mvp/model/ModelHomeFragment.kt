package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.MainApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelHomeFragment {

    fun getUrl() = arrayListOf(
        "https://www.daneshjooyar.com/wp-content/uploads/2023/02/terand-1536x864.jpg",
        "https://www.daneshjooyar.com/wp-content/uploads/2023/02/terand-1536x864.jpg",
        "https://www.daneshjooyar.com/wp-content/uploads/2023/02/terand-1536x864.jpg",
        "https://www.daneshjooyar.com/wp-content/uploads/2023/02/terand-1536x864.jpg"
    )

    fun getContent(callbackRequest: CallbackRequest<RequestMain>) {

        MainApiRepository.instance.getMainContent(callbackRequest)

    }

}