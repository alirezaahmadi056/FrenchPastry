package info.alirezaahmadi.frenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.CustomTextViewBinding

class CustomTextView(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding =
        CustomTextViewBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

        initialize(attrs)

    }

    private fun initialize(attrs: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)

        val text = typeArray.getString(R.styleable.CustomTextView_customText)
        val size = typeArray.getFloat(R.styleable.CustomTextView_customSize, 0f)
        val icon = typeArray.getBoolean(R.styleable.CustomTextView_iconState, false)

        binding.txtMain.text = text

        if (size != 0f)
            binding.txtMain.textSize = size

        if (icon)
            binding.imgIcon.visibility = View.VISIBLE

        typeArray.recycle()

    }

    fun getTxt() = binding.txtMain

    fun getIcon() = binding.imgIcon

}