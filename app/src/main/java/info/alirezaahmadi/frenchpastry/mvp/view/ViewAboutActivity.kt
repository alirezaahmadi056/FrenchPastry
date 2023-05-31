package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.ActivityAboutBinding

class ViewAboutActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityAboutBinding.inflate(LayoutInflater.from(context))

}