package com.app.feature_auth_user.ui.states

import com.app.core_common.base.UIState

data class LoginScreenUIState(
    val countryCode: String = "+91",
    val countryName: String = "India",
    val phoneNumber: String = "",
    val showCountryPicker: Boolean = false,
    val isLoading: Boolean = false
) : UIState
