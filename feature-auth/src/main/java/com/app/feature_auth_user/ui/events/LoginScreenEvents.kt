package com.app.feature_auth_user.ui.events

sealed interface LoginScreenEvents {
    data class OnPhoneNumberChanged(val phoneNumber: String) : LoginScreenEvents
    data class OnCountrySelected(val code: String, val name: String) : LoginScreenEvents
    object OnShowCountryPickerClicked : LoginScreenEvents
    object OnDismissCountryPicker : LoginScreenEvents
    object OnSendVerificationCodeClicked : LoginScreenEvents
    object OnGoogleLoginClicked : LoginScreenEvents
    object OnAppleLoginClicked : LoginScreenEvents
    object OnCreateAccountClicked : LoginScreenEvents
}
