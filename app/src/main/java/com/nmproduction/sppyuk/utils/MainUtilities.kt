package com.nmproduction.sppyuk.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.nmproduction.sppyuk.R
import java.text.NumberFormat
import java.util.*

object MainUtilities {
    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun localFormatNumber(amount: Int): String {
        return NumberFormat.getNumberInstance(Locale.GERMANY).format(amount)
    }

    fun intToDp(gap: Int, resources: Resources): Int {
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                gap.toFloat(),
                resources.displayMetrics
            )
        )
    }
}