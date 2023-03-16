package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.databinding.ActivityMainBinding
import info.alirezaahmadi.frenchpastry.mvp.model.ModelMainActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterMainActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)

        val model = ModelMainActivity()
        presenter = PresenterMainActivity(view, model)
        presenter.onCreate()

    }

}