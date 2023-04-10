package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewDetailPastryActivity

class PresenterDetailPastryActivity(
    private val view: ViewDetailPastryActivity,
    private val model: ModelDetailPastryActivity
):BaseLifeCycle {

    override fun onCreate() {

    }

}