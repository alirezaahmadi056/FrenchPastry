package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListCategoryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListCategoryActivity

class PresenterListCategoryActivity(
    private val view: ViewListCategoryActivity,
    private val model: ModelListCategoryActivity
):BaseLifeCycle {

    override fun onCreate() {
        onBackClick()
    }

    private fun onBackClick(){
        view.onBack()
    }

}