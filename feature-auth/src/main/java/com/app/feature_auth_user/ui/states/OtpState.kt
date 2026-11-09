package com.app.feature_auth_user.ui.states

import com.app.core_common.base.UIState

data class OtpState(
    val otpValue: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
) : UIState