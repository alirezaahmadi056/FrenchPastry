package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat.PastryCategoryModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelPastryCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewPastryCatsFragment

class PresenterPastryCatsFragment(
    private val view: ViewPastryCatsFragment,
    val model: ModelPastryCatsFragment
) : BaseLifeCycle {

    override fun onCreate() {
        getCats()
    }

    private fun getCats() {

        model.getCats(

            object : CallbackRequest<PastryCategoryModel> {

                override fun onSuccess(code: Int, data: PastryCategoryModel) {
                    view.setDataRecycler(data.categories)
                }

                override fun onNotSuccess(code: Int, error: String, message: String) {
                    view.toast(message, false)
                }

                override fun onError(error: String) {
                    view.toast("", true)
                }

            }

        )

    }

}