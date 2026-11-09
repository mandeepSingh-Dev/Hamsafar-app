package com.app.feature_splash.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.feature_splash.ui.LanguageSelectionScreen
import com.app.feature_splash.ui.SplashScreen


fun EntryProviderScope<NavKey>.splashGraph() {

    entry<AppScreens.SplashScreen> {
        SplashScreen()
    }
    entry<AppScreens.LanguageSelectionScreen> {
        LanguageSelectionScreen()
    }
}
