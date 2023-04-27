package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelUserActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewUserActivity

class PresenterUserActivity(
    private val view: ViewUserActivity,
    private val model: ModelUserActivity
) : BaseLifeCycle {

    override fun onCreate() {

    }

}