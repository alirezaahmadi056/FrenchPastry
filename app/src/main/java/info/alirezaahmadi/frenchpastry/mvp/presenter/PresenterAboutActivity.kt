package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelAboutActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewAboutActivity

class PresenterAboutActivity(
    private val view: ViewAboutActivity,
    private val model: ModelAboutActivity
):BaseLifeCycle {

    override fun onCreate() {

    }

}