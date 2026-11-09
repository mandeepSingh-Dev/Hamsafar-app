package com.app.core_ui.local_providers

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope

val LocalSnackBarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("No SnackBar_host_state provided!")
}
val LocalBackStackNav = staticCompositionLocalOf<NavBackStack<NavKey>> {
    error("No local back stack for navigation provided!")
}


@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope =
    staticCompositionLocalOf<SharedTransitionScope> {
        error("SharedTransitionScope is not provided")
    }

val LocalGuestMode = staticCompositionLocalOf {
    false
}

val LocalGuestModeLambda = staticCompositionLocalOf<((Boolean) -> Unit)?> {
    error("No Guest mode lambda provided")
}