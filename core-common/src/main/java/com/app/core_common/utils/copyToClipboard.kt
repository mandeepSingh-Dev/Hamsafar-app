package com.app.core_common.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.app.core_common.extensions.showToast

fun String.copyToClipboard(
    context: Context,
) {
    val clipboard = context.getSystemService(
        Context.CLIPBOARD_SERVICE
    ) as ClipboardManager

    clipboard.setPrimaryClip(
        ClipData.newPlainText("Copied text", this)
    )


    context.showToast("Copied to clipboard", length = Toast.LENGTH_SHORT)
}