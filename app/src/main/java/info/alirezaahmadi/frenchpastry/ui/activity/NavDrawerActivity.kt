package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.ActivityNavDrawerBinding

class NavDrawerActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNavDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        binding.txtOrders.setOnClickListener(this)
        binding.txtAbout.setOnClickListener(this)
        binding.txtContact.setOnClickListener(this)
        binding.txtSupport.setOnClickListener(this)
        binding.txtUpgrade.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.imgCloseNav.setOnClickListener(this)

        onBack()

    }

    private fun onBack(){

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    finish()
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
                }
            })

    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.txtOrders -> {

            }

            R.id.txtSupport -> {

            }

            R.id.txtAbout -> {

            }

            R.id.txtContact -> {

            }

            R.id.txtUpgrade -> {

            }

            R.id.txtLogout -> {
                finishAffinity()
            }

            R.id.imgCloseNav -> {
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            }

        }

    }

}