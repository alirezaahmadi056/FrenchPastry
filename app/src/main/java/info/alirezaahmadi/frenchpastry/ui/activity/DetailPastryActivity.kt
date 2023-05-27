package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import info.alirezaahmadi.frenchpastry.adapter.viewPager.CustomSliderPagerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewDetailPastryActivity

class DetailPastryActivity : AppCompatActivity(), ActivityUtils {

    companion object {
        const val ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewDetailPastryActivity(this, this)
        setContentView(view.binding.root)

        val id = intent.getIntExtra(ID, 0)

        val presenter =
            PresenterDetailPastryActivity(
                view,
                ModelDetailPastryActivity(id),
                this
            )
        presenter.onCreate()


    }

    override fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<String>) {

        viewPager.adapter =
            CustomSliderPagerAdapter(supportFragmentManager, lifecycle, data)

    }

    override fun finished() {
        finish()
    }

}