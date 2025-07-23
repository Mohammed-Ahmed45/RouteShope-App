package com.mohamed.routeshop.ui.error

import android.util.Log
import com.mohamed.data.R
import okio.IOException
import java.net.UnknownHostException

fun handleError(throwable: Throwable): Int {
    return when (throwable) {
        is UnknownHostException, is IOException -> {
            Log.e("TAG", "handleError: ${throwable.message}")

            R.string.check_your_internet_connection
        }

        else -> {
            Log.e("TAG", "handleError: ${throwable.message}")
            Log.e("TAG", "handleErrorObject: ${throwable}")

            R.string.something_went_to_wrong

        }


    }

}
