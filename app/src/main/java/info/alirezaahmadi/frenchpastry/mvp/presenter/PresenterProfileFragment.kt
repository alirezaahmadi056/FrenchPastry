package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelProfileFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewProfileFragment

class PresenterProfileFragment(
    private val view: ViewProfileFragment,
    val model: ModelProfileFragment,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        onClickHandler()
        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getCats()

    }

    override fun activeNetwork() {
        getCats()
    }

    private fun onClickHandler() {
        view.onClick()
    }

    private fun getCats() {

    }

}