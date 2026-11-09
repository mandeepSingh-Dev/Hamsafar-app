package com.app.feature_auth_user.ui.events

sealed interface OtpEvent {
    data class OnOtpChanged(val otp: String) : OtpEvent
    object OnVerifyClicked : OtpEvent
    object OnResendClicked : OtpEvent
}