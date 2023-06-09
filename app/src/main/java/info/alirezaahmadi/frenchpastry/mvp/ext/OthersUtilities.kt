package info.alirezaahmadi.frenchpastry.mvp.ext

import android.content.res.Resources
import android.util.TypedValue

object OthersUtilities {

    fun changePrice(price: Int):String {

        val number = price / 10

        val numberString = number.toString()
        val reversedNumberString = numberString.reversed()
        val separatedDigits = mutableListOf<String>()

        for (i in reversedNumberString.indices step 3) {
            val endIndex =
                if (i + 3 > reversedNumberString.length)
                    reversedNumberString.length else i + 3
            separatedDigits.add(reversedNumberString.substring(i, endIndex))
        }

        return separatedDigits.joinToString(",").reversed()

    }

    fun getPixel(dip: Float, resources: Resources): Int {

        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            resources.displayMetrics
        ).toInt()

    }

}