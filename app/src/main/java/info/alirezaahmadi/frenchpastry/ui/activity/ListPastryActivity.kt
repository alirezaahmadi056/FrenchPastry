package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListPastryActivity

class ListPastryActivity : AppCompatActivity(), ActivityUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewListPastryActivity(this, this)
        setContentView(view.binding.root)

        val id = intent.getIntExtra("ID", 0)

        val presenter = PresenterListPastryActivity(
            view,
            ModelListPastryActivity(id),
            this
        )
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}