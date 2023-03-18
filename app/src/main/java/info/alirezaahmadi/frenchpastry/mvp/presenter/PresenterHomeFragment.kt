package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelHomeFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewHomeFragment

class PresenterHomeFragment(
    private val view: ViewHomeFragment,
    private val model: ModelHomeFragment
) : BaseLifeCycle {

    override fun onCreate() {
        createSlider()
    }

    private fun createSlider() {
        view.initSlider(model.getUrl())
        model.getContent(
            object : CallbackRequest<RequestMain> {

                override fun onSuccess(code: Int, data: RequestMain) {

                }

                override fun onNotSuccess(code: Int, error: String, message: String) {

                }

                override fun onError(error: String) {

                }

            }
        )
    }

}