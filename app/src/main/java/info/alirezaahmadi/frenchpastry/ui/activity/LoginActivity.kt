package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
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

        val model = ModelLoginActivity(this)
        presenter = PresenterLoginActivity(view, model)
        presenter.onCreate()

        onBack()

    }

    private fun onBack(){

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    finishAffinity()
                }
            })

    }

    private fun hideStatus() {

        if (VERSION.SDK_INT >= Build.VERSION_CODES.P) {

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