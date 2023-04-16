package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.local.dataFake.PastryCatsFakeData
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryCatApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryCategoryModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelPastryCatsFragment {

    val fakeData = PastryCatsFakeData.data

    fun getCats(callbackRequest: CallbackRequest<PastryCategoryModel>) {
        PastryCatApiRepository.instance.getCategories(callbackRequest)
    }

}