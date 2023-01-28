package info.alirezaahmadi.frenchpastry.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import info.alirezaahmadi.frenchpastry.data.local.preferences.LoginInfo
import info.alirezaahmadi.frenchpastry.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity() {

    private lateinit var loginState: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatus()

        loginState = getSharedPreferences(LoginInfo.LOGIN_PREFERENCES, MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (
                    loginState.getBoolean(LoginInfo.LOGIN_STATE_KEY, false)
                )
                    startActivity(
                        Intent(this@FullscreenActivity, MainActivity::class.java)
                    )
                else
                    startActivity(
                        Intent(this@FullscreenActivity, MainActivity::class.java)
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