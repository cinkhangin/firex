package com.naulian.firex

import androidx.fragment.app.Fragment
import com.naulian.anhance.logError
import com.naulian.anhance.showToast

fun Fragment.handleError(exception: Throwable) {
    showToast(exception.localizedMessage ?: "")
}

fun handleError(exception: Throwable) {
    logError(exception.localizedMessage ?: "")
}