package com.app.feature_home.navigation.homeGraph

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.feature_home.ui.screens.AccountSettingsScreen
import com.app.feature_home.ui.screens.HomeBottomContainerScreen
import com.app.feature_home.ui.screens.PartnerPreferencesScreen
import com.app.feature_home.ui.screens.ProfileDetailsScreen
import com.app.feature_home.ui.screens.SearchScreen
import com.app.feature_home.ui.screens.SubscriptionScreen

fun EntryProviderScope<NavKey>.homeGraph() {
    entry<AppScreens.HomeBottomContainerScreen> {
        HomeBottomContainerScreen()
    }
    entry<AppScreens.HomeBottomContainerHostScreen> {
        HomeBottomContainerScreen()
    }
    entry<AppScreens.ProfileDetailsScreen> {
        ProfileDetailsScreen()
    }
    entry<AppScreens.SearchScreen> {
        SearchScreen(backStack = LocalBackStackNav.current)
    }
    entry<AppScreens.PartnerPreferencesScreen> {
        PartnerPreferencesScreen(backStack = LocalBackStackNav.current)
    }
    entry<AppScreens.AccountSettingsScreen> {
        AccountSettingsScreen(backStack = LocalBackStackNav.current)
    }
    entry<AppScreens.SubscriptionScreen> {
        SubscriptionScreen(backStack = LocalBackStackNav.current)
    }
}
