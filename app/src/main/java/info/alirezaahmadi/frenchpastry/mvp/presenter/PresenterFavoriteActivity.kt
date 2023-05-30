package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewFavoriteActivity

class PresenterFavoriteActivity(
    private val view: ViewFavoriteActivity,
    private val model: ModelFavoriteActivity
) : BaseLifeCycle {

    override fun onCreate() {

    }

}