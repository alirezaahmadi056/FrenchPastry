package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryDetailModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityDetailPastryBinding

class ViewDetailPastryActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding =
        ActivityDetailPastryBinding.inflate(LayoutInflater.from(context))

    fun setData(detail: PastryDetailModel) {

        binding.txtTitle.text = detail.title
        binding.txtDesc.text = detail.content
        binding.txtRate.text = detail.rate.toString()
        binding.ratingComment.rating = 4.5f

        if (detail.bookmark)
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
        else
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite)

        if (detail.comment_count > 0)
            binding.commentCount.visibility = View.VISIBLE
        else
            binding.commentCount.visibility = View.INVISIBLE
        binding.txtCommentCount.text = detail.comment_count.toString()

    }

    fun startGetData() {
        binding.allViews.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun endGetData() {
        binding.allViews.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}