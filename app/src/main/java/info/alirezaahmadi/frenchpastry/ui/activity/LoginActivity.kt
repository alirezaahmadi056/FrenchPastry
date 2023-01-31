package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding
import info.alirezaahmadi.frenchpastry.mvp.model.ModelLoginActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterLoginActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewLoginActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterLoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewLoginActivity(this)
        setContentView(view.binding.root)

        val model = ModelLoginActivity()
        presenter = PresenterLoginActivity(view, model)
        presenter.onCreate()

    }

}