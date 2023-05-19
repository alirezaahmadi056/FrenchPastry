package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.mvp.model.ModelUserActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterUserActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewUserActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewUserActivity(this)
        setContentView(view.binding.root)

        val presenter = PresenterUserActivity(view, ModelUserActivity(this), this)
        presenter.onCreate()

    }

}