package info.alirezaahmadi.frenchpastry.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.local.preferences.SecureSharePref
import info.alirezaahmadi.frenchpastry.data.local.preferences.SharedPrefKey
import info.alirezaahmadi.frenchpastry.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity(), ActivityUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatus()

        val loginState = SecureSharePref.getSharedPref(this)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (loginState.getBoolean(SharedPrefKey.LOGIN_STATE_KEY, false))
                    startActivity(
                        Intent(this@FullscreenActivity, MainActivity::class.java)
                    )
                else
                    startActivity(
                        Intent(this@FullscreenActivity, LoginActivity::class.java)
                    )
                finish()
            }, 1300
        )

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