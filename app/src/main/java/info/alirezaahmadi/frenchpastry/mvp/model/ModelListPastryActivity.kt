package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.PastryListApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.ui.activity.ListPastryActivity

class ModelListPastryActivity(
    private val id: Int,
    private val type: String
) {

    fun getPastries(
        callbackRequest: CallbackRequest<ListPastriesModel>,
        callbackRequest2: CallbackRequest<AllPastriesModel>
    ) {

        if (id != 0)
            PastryListApiRepository.instance.getMainContent(callbackRequest, id)
        else
            PastryListApiRepository.instance.getContentByType(callbackRequest2, type)

    }

    fun getTitle() = when (type) {
        ListPastryActivity.NEW -> "تازه ترین ها"
        ListPastryActivity.RATE -> "محبوب ترین ها"
        ListPastryActivity.SPECIAL_OFFER -> "پیشنهاد ویژه"
        else -> ""
    }

}