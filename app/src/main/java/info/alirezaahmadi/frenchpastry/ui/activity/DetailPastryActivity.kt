package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewDetailPastryActivity

class DetailPastryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewDetailPastryActivity(this)
        setContentView(view.binding.root)

        val presenter = PresenterDetailPastryActivity(view, ModelDetailPastryActivity())
        presenter.onCreate()


    }

}