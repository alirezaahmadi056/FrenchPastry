package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.ActivityDetailPastryBinding

class ViewDetailPastryActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityDetailPastryBinding.inflate(LayoutInflater.from(context))

}