package com.app.feature_chat.navigation.chatGraph

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.feature_chat.ui.screens.ChatScreen

fun EntryProviderScope<NavKey>.chatGraph() {
    entry<AppScreens.ChatScreen> { screen ->
        ChatScreen(
            userId = screen.userId,
            userName = screen.userName,
            backStack = LocalBackStackNav.current
        )
    }
}
