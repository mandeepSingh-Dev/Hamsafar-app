package com.app.core_auth.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ForgotPasswordResponse(
    val count: Int?,
    val `data`: Data?,
    val message: String?,
    val status: Int?
) {
    @Keep
    data class Data(
        val token: String?
    )
}