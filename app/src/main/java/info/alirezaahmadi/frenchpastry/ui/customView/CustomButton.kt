package info.alirezaahmadi.frenchpastry.ui.customView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.CustomButtonBinding

class CustomButton(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding =
        CustomButtonBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

        initialize(attrs)

    }

    private fun initialize(attrs: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)

        val text = typeArray.getString(R.styleable.CustomButton_buttonText)
        val white = typeArray.getBoolean(R.styleable.CustomButton_buttonWhite, false)

        binding.button.text = text

        if (white) {
            binding.button.setBackgroundResource(R.drawable.back_button_white)
            binding.button.setTextColor(Color.parseColor("#101219"))
        }

        typeArray.recycle()

    }

    fun getView() = binding.button

    fun enableProgress() {
        binding.progressIndicator.visibility = VISIBLE
        binding.button.visibility = INVISIBLE
    }

    fun disableProgress() {
        binding.progressIndicator.visibility = INVISIBLE
        binding.button.visibility = VISIBLE
    }

}