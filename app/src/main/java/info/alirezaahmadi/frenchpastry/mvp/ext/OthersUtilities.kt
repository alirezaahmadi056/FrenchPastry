package info.alirezaahmadi.frenchpastry.mvp.ext

import android.content.res.Resources
import android.util.TypedValue

object OthersUtilities {

    fun changePrice(price: Int) = price / 10

    fun getPixel(dip: Float, resources: Resources): Int {

        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            resources.displayMetrics
        ).toInt()

    }

}