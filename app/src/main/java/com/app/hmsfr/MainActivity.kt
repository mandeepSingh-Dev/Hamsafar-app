package com.app.hmsfr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.NavDisplay
import com.app.core_common.utils.DeepLinkHandler
import com.app.core_common.utils.SocketHostEventsHandler
import com.app.core_common.utils.deepLinkHandler
import com.app.core_navigation.AppScreens
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.core_ui.local_providers.LocalCurrentLanguage
import com.app.core_ui.local_providers.LocalGuestMode
import com.app.core_ui.local_providers.LocalGuestModeLambda
import com.app.core_ui.local_providers.LocalSharedTransitionScope
import com.app.core_ui.local_providers.LocalSnackBarHostState
import com.app.core_ui.ui.theme.KahoJiTheme
import com.app.core_ui.ui.theme.theme_color_1
import com.app.core_ui.ui.theme.theme_color_2
import com.app.feature_auth_user.navigation.authGraph.authGraph
import com.app.feature_chat.navigation.chatGraph.chatGraph
import com.app.feature_home.navigation.homeGraph.homeGraph
import com.app.feature_splash.navigation.splashGraph
import com.uae.core_network.data.service.CommonService
import com.zodiaq.ui.theme.Shape_20
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var commonService: CommonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CryptoManager.generateKeysIfNeeded()

        setContent {
            val metrics = LocalWindowInfo.current

            val widthPx = metrics.containerSize.width
            val heightPx = metrics.containerSize.height
            Log.d("knbkfnbf", "${widthPx} - $heightPx")

            KahoJiTheme {
                SharedTransitionLayout {
                    val sharedScope = this

                    val backstack = rememberNavBackStack(AppScreens.HomeBottomContainerScreen)
                    val snackBarHostState = remember { SnackbarHostState() }

                    var isGuestMode by rememberSaveable { mutableStateOf(false) }
                    var currentLanguage by rememberSaveable { mutableStateOf("English") }

                    CompositionLocalProvider(
                        LocalSnackBarHostState provides snackBarHostState,
                        LocalBackStackNav provides backstack,
                        LocalSharedTransitionScope provides sharedScope,
                        LocalGuestMode provides isGuestMode,
                        LocalGuestModeLambda provides { isGuestMode = it },
                        LocalCurrentLanguage provides currentLanguage
                    ) {
                        deepLinkHandler = retain {
                            object : DeepLinkHandler {
                                override fun navigateToLoginScreen() {}
                            }
                        }

                        val receivedGift by SocketHostEventsHandler.receiveGift.collectAsStateWithLifecycle(
                            null
                        )

                        Scaffold(
                            snackbarHost = {
                                SnackbarHost(snackBarHostState) {
                                    Snackbar(
                                        shape = Shape_20,
                                        snackbarData = it,
                                        dismissActionContentColor = theme_color_1,
                                        containerColor = theme_color_1,
                                        contentColor = Color.White,
                                        actionColor = theme_color_2,
                                        actionOnNewLine = true,
                                    )
                                }
                            }, modifier = Modifier.fillMaxSize()
                        ) { innerPadding ->
                            innerPadding

                            NavDisplay(
                                backStack = backstack,
                                onBack = {
                                    if (backstack.size > 1) backstack.removeLastOrNull()
                                },
                                entryDecorators = listOf(
                                    rememberSaveableStateHolderNavEntryDecorator(),
                                    rememberViewModelStoreNavEntryDecorator()
                                ),
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(300)) togetherWith fadeOut(
                                        animationSpec = tween(300)
                                    )
                                },
                                predictivePopTransitionSpec = iosPredictivePopTransitionSpec(),
                                popTransitionSpec = {
                                    fadeIn(animationSpec = tween(300)) togetherWith fadeOut(
                                        animationSpec = tween(300)
                                    )
                                },
                                entryProvider = entryProvider {
                                    splashGraph()
                                    authGraph()
                                    homeGraph()
                                    chatGraph()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    public fun <T : Any> iosPredictivePopTransitionSpec(): AnimatedContentTransitionScope<Scene<T>>.(
        swipeEdge: Int
    ) -> ContentTransform = {
        val exitAnimation = slideOutHorizontally(
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
            targetOffsetX = { fullWidth -> fullWidth }
        ) + scaleOut(
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
            targetScale = 0.96f
        ) + fadeOut(animationSpec = tween(durationMillis = 200))

        val enterAnimation = slideInHorizontally(
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
            initialOffsetX = { fullWidth -> -fullWidth / 3 }
        ) + scaleIn(
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
            initialScale = 0.92f
        ) + fadeIn(animationSpec = tween(durationMillis = 300))

        enterAnimation togetherWith exitAnimation
    }
}
