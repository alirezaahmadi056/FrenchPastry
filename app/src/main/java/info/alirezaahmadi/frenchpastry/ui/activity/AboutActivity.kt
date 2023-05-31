package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelAboutActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterAboutActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewAboutActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewAboutActivity(this)
        setContentView(view.binding.root)

        val presenter = PresenterAboutActivity(view, ModelAboutActivity())
        presenter.onCreate()

    }

}