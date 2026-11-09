package com.app.feature_auth_user.ui.viewmodels

import com.app.core_common.UserManager
import com.app.core_common.base.BaseViewModel
import com.app.core_navigation.UserScreens
import com.app.feature_auth_user.ui.events.OtpEvent
import com.app.feature_auth_user.ui.states.OtpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val userManager: UserManager
) : BaseViewModel<OtpState>(OtpState()) {

    fun onEvent(event: OtpEvent) {
        when (event) {
            is OtpEvent.OnOtpChanged -> {
                updateState { it.copy(otpValue = event.otp, isError = false) }
            }
            is OtpEvent.OnVerifyClicked -> {
                val state = uiState.value ?: return
                if (state.otpValue.length < 6) {
                    messageEvent("Please enter a valid 6-digit OTP")
                    updateState { it.copy(isError = true) }
                    return
                }
                
                // Mock success and navigate to profile setup step 1
                navigationEvent(UserScreens.AuthScreens.ProfileScreen(dob = null, step = 1))
            }
            is OtpEvent.OnResendClicked -> {
                messageEvent("OTP Resent successfully")
            }
        }
    }
}