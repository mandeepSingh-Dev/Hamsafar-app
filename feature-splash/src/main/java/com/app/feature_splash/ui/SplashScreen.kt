package com.app.feature_splash.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_common.utils.AttachLifecycleObserver
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.core_ui.ui.theme.theme_color_1
import com.app.core_ui.ui.theme.theme_color_2
import com.app.core_ui.ui.theme.theme_color_3
import com.app.core_ui.ui.theme.theme_color_9
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview
fun SplashScreen(
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    AttachLifecycleObserver { event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                splashViewModel.startTimer()
            }
            else -> Unit
        }
    }

    ObserveUIEvents(splashViewModel.uiEvent) { event ->
        when (event) {
            is CommonUIEvents.NavigateTo -> {
                backStack.removeAll(backStack)
                backStack.add(event.navKey)
            }
        }
    }

    val logoScale = remember { Animatable(0.7f) }
    val logoAlpha = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }
    val textOffsetY = remember { Animatable(30f) }

    val infiniteTransition = rememberInfiniteTransition(label = "SplashMeshGlowAnimation")

    val floatingOffset by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Floating"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Pulse"
    )

    val meshRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "MeshRotation"
    )

    LaunchedEffect(Unit) {
        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(900, easing = FastOutSlowInEasing)
            )
        }
        launch {
            logoAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(700)
            )
        }
        delay(300)
        launch {
            textAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(700)
            )
        }
        launch {
            textOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(700, easing = FastOutSlowInEasing)
            )
        }
    }

    BoxCommon(
        modifier = Modifier.fillMaxSize(),
        showBackgroundImage = false,
        isAppearanceLightStatusBars = false,
        isAppearanceLightNavigationBars = false,
        containerBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF2C1635),
                Color(0xFF4A154B),
                Color(0xFF230D2C)
            )
        )
    ) {
        // Infinite Mesh Gradient / Glowing Animated Background behind Icon
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(280.dp)
                .graphicsLayer {
                    rotationZ = meshRotation
                    scaleX = pulseScale * 1.2f
                    scaleY = pulseScale * 1.2f
                }
                .blur(40.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            theme_color_1.copy(alpha = 0.5f),
                            theme_color_2.copy(alpha = 0.3f),
                            Color(0xFF8142E9).copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icon Container with Scale and Floating animation
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .graphicsLayer {
                        scaleX = logoScale.value * pulseScale
                        scaleY = logoScale.value * pulseScale
                        translationY = floatingOffset
                        alpha = logoAlpha.value
                    }
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                theme_color_9.copy(alpha = 0.25f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.heart_handshake),
                    contentDescription = "Hamsafar Logo",
                    modifier = Modifier.size(90.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Cool Matrimony Texts
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = textAlpha.value
                        translationY = textOffsetY.value + (floatingOffset * 0.3f)
                    }
            ) {
                Text(
                    text = "Hamsafar",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Where Two Hearts Meet",
                    color = theme_color_3,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Matrimony for Every Community",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}
