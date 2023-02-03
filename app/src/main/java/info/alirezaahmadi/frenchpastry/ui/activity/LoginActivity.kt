package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
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

        hideStatus()

        val model = ModelLoginActivity()
        presenter = PresenterLoginActivity(view, model)
        presenter.onCreate()

    }

    private fun hideStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        } else {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }

    }

}