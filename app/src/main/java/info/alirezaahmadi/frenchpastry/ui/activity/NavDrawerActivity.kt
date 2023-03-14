package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.databinding.ActivityNavDrawerBinding

class NavDrawerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}