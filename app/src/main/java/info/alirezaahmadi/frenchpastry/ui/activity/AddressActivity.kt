package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelAddressActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterAddressActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewAddressActivity

class AddressActivity : AppCompatActivity(), ActivityUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewAddressActivity(this, this)
        setContentView(view.binding.root)

        val presenter = PresenterAddressActivity(view, ModelAddressActivity(), this)
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}