package com.app.feature_auth_user.ui.viewmodels

import com.app.core_common.UserManager
import com.app.core_common.base.BaseViewModel
import com.app.feature_auth_user.ui.events.LoginScreenEvents
import com.app.feature_auth_user.ui.states.LoginScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userManager: UserManager
) : BaseViewModel<LoginScreenUIState>(LoginScreenUIState()) {

    fun onEvent(event: LoginScreenEvents) {
        when (event) {
            is LoginScreenEvents.OnPhoneNumberChanged -> {
                updateState { it.copy(phoneNumber = event.phoneNumber) }
            }
            is LoginScreenEvents.OnCountrySelected -> {
                updateState { it.copy(countryCode = event.code, countryName = event.name, showCountryPicker = false) }
            }
            is LoginScreenEvents.OnShowCountryPickerClicked -> {
                updateState { it.copy(showCountryPicker = true) }
            }
            is LoginScreenEvents.OnDismissCountryPicker -> {
                updateState { it.copy(showCountryPicker = false) }
            }
            is LoginScreenEvents.OnSendVerificationCodeClicked -> {
                val state = uiState.value
                if (state?.phoneNumber.isNullOrBlank()) {
                    messageEvent("Please enter your mobile number")
                } else {
                    navigationEvent(
                        com.app.core_navigation.UserScreens.AuthScreens.OtpScreen(
                            phoneNumber = state.phoneNumber,
                            countryCode = state.countryCode
                        )
                    )
                }
            }
            is LoginScreenEvents.OnGoogleLoginClicked -> {}
            is LoginScreenEvents.OnAppleLoginClicked -> {}
            is LoginScreenEvents.OnCreateAccountClicked -> {}
        }
    }
}
