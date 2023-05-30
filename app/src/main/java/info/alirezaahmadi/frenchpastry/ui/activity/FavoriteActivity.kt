package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewFavoriteActivity

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewFavoriteActivity(this)
        setContentView(view.binding.root)

        val presenter = PresenterFavoriteActivity(view, ModelFavoriteActivity())
        presenter.onCreate()

    }

}