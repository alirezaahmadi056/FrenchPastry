package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelAddressActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewAddressActivity

class PresenterAddressActivity(
    private val view: ViewAddressActivity,
    private val model: ModelAddressActivity,
    private val context: Context
) : BaseLifeCycle {

    override fun onCreate() {

        view.showNavDrawer()
        view.onBack()

    }

}