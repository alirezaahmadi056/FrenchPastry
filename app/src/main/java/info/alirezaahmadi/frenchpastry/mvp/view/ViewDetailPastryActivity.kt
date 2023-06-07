package info.alirezaahmadi.frenchpastry.mvp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.adapter.recycler.CommentsRecyclerAdapter
import info.alirezaahmadi.frenchpastry.adapter.recycler.MaterialsRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.SendRequests
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryDetailModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityDetailPastryBinding
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogSellBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.OthersUtilities
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity

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

    fun setData(detail: PastryDetailModel, sendRequests: SendRequests) {

        binding.viewPagerSlider.layoutDirection = View.LAYOUT_DIRECTION_RTL
        actUtils.setViewPagerFragment(binding.viewPagerSlider, detail.gallery)

        binding.recyclerMaterials.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerMaterials.adapter = MaterialsRecyclerAdapter(detail.materials)

        binding.txtTitle.text = detail.title
        binding.txtDesc.text = detail.content
        binding.txtRate.text = detail.rate.rate.toString()

        //todo بوک مارک و علاقه مندی کار نمیکنه

        if (detail.bookmark)
            binding.imgFavorite.setImageResource(R.drawable.ic_actived_favorite)
        else
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite)

        if (detail.comment_count > 0)
            binding.commentCount.visibility = View.VISIBLE
        else
            binding.commentCount.visibility = View.INVISIBLE
        binding.txtCommentCount.text = detail.comment_count.toString()

        var bookmark = detail.bookmark

        binding.imgFavorite.setOnClickListener {

            var action = ""

            if (bookmark) {
                action = ModelDetailPastryActivity.ACTION_UN_FAVORITE
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                bookmark = false
            } else {
                action = ModelDetailPastryActivity.ACTION_FAVORITE
                binding.imgFavorite.setImageResource(R.drawable.ic_actived_favorite)
                bookmark = true
            }

            sendRequests.startSendFavorite(
                DeviceInfo.getDeviceID(context),
                DeviceInfo.getPublicKey(context),
                DeviceInfo.getApi(context),
                action
            )

        }

        binding.btnSendComment.getView().setOnClickListener {

            val text = binding.edtComment.text.toString()
            val rate = binding.ratingComment.rating

            if (text.isEmpty() || (text.length < 10)) {
                ToastUtils.toast(context, "نظر شما نمیتواند کمتر از 10 کاراکتر باشد")
                return@setOnClickListener
            }

            binding.btnSendComment.enableProgress()

            sendRequests.sendComment(
                DeviceInfo.getDeviceID(context),
                DeviceInfo.getPublicKey(context),
                DeviceInfo.getApi(context),
                text,
                rate,
                detail.ID
            )

        }

        if (detail.comments != null) {

            binding.recyclerComments.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.recyclerComments.adapter =
                CommentsRecyclerAdapter(detail.comments)

        }

        binding.txtMainPrice.text = OthersUtilities.changePrice(detail.price).toString()

        if (detail.has_discount) {

            binding.txtMainPrice.paintFlags =
                binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.txtMainPrice.setTextColor(Color.GRAY)

            binding.txtOffPrice.text = OthersUtilities.changePrice(detail.sale_price).toString()
            binding.txtOffCount.text = detail.discount_percent_110n

        } else
            binding.off.visibility = View.GONE

        binding.btnShop.getView().setOnClickListener {

            binding.btnShop.enableProgress()

            val view = CustomDialogSellBinding.inflate(LayoutInflater.from(context))
            val dialog = Dialog(context)
            dialog.setContentView(view.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            view.btnContinueSell.getView().setOnClickListener {

            }

            view.viewPlus.setOnClickListener {
                var count = view.txtCount.text.toString().toInt()
                if (count < 10)
                    count++
                view.txtCount.text = count.toString()
            }

            view.viewMin.setOnClickListener {
                var count = view.txtCount.text.toString().toInt()
                if (count > 1)
                    count--
                view.txtCount.text = count.toString()
            }

            dialog.setOnCancelListener {
                binding.btnShop.disableProgress()
            }

        }

        //todo بایستی بخش محصولات مشابه رو ایجاد کنی

    }

    fun disableButtonProgress() {
        binding.btnSendComment.disableProgress()
    }

    fun startGetData() {
        binding.allViews.visibility = View.INVISIBLE
        binding.bottomViews.visibility = View.INVISIBLE
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
        binding.bottomViews.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}