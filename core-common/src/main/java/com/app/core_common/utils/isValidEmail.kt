package com.app.core_common.utils

fun String?.isValidEmail(): Boolean {
    return if (this.isNullOrEmpty()) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS
            .matcher(trim())
            .matches()
    }
}