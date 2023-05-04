package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListPastryActivity

class ListPastryActivity : AppCompatActivity(), ActivityUtils {

    companion object {
        const val ID = "ID"
        const val TYPE = "TYPE"
        const val NEW = "NEW"
        const val SPECIAL_OFFER = "SPECIAL_OFFER"
        const val RATE = "RATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewListPastryActivity(this, this)
        setContentView(view.binding.root)

        val id = intent.getIntExtra(ID, 0)
        val type = intent.getStringExtra(TYPE) ?: ""

        val presenter = PresenterListPastryActivity(
            view,
            ModelListPastryActivity(id, type),
            this
        )
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}