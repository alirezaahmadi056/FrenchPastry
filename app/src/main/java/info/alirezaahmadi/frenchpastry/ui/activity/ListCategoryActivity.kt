package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListCategoryActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterListCategoryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListCategoryActivity

class ListCategoryActivity : AppCompatActivity(), ActivityUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewListCategoryActivity(this, this)
        setContentView(view.binding.root)

        val presenter = PresenterListCategoryActivity(view, ModelListCategoryActivity())
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}