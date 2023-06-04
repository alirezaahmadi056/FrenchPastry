package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewFavoriteActivity

class FavoriteActivity : AppCompatActivity(), ActivityUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewFavoriteActivity(this, this)
        setContentView(view.binding.root)

        val presenter = PresenterFavoriteActivity(view, ModelFavoriteActivity(), this)
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}