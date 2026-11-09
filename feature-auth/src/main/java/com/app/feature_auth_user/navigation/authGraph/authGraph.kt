package com.app.feature_auth_user.navigation.authGraph

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_navigation.UserScreens
import com.app.feature_auth_user.ui.screens.LoginScreen
import com.app.feature_auth_user.ui.screens.OtpScreen
import com.app.feature_auth_user.ui.screens.profile_setup.ProfileScreen


fun EntryProviderScope<NavKey>.authGraph() {
    entry<AppScreens.OnBoardingScreen> {
        LoginScreen()
    }
    entry<UserScreens.AuthScreens.LoginScreen> {
        LoginScreen()
    }
    entry<UserScreens.AuthScreens.OtpScreen> {
        OtpScreen(phoneNumber = it.phoneNumber, countryCode = it.countryCode)
    }
    entry<UserScreens.AuthScreens.ProfileScreen> {
        ProfileScreen(dob = it.dob, step = it.step)
    }
    entry<AppScreens.UserTypeSelectionScreen> {
        LoginScreen()
    }
}
