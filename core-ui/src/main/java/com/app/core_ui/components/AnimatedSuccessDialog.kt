package com.app.core_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.core_ui.R
import com.app.core_ui.ui.theme.HAFFER_TRIAL_BOLD
import com.app.core_ui.ui.theme.HAFFER_TRIAL_SEMI_BOLD
import com.app.core_ui.ui.theme.theme_color_1
import com.app.core_ui.ui.theme.theme_color_2
import com.app.core_ui.ui.theme.theme_color_3
import com.app.core_ui.ui.theme.theme_color_4
import com.app.core_ui.ui.theme.theme_color_6
import com.app.core_ui.ui.theme.theme_color_7
import com.app.core_ui.ui.theme.theme_color_18

@Composable
fun AnimatedSuccessDialog(
    show: Boolean,
    title: String,
    message: String,
    icon: Int = R.drawable.bean_iconimage86,
    positiveButtonText: String = "Done",
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit
) {
    if (show) {
        val infiniteTransition = rememberInfiniteTransition(label = "success_dialog_anim")
        val meshProgress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = androidx.compose.animation.core.LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "mesh_progress"
        )

        val pulseScale by infiniteTransition.animateFloat(
            initialValue = 0.95f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = androidx.compose.animation.core.FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_scale"
        )

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = true),
            onDismissRequest = onDismiss
        ) {
            AnimatedVisibility(
                visible = show,
                enter = fadeIn(animationSpec = tween(300)) + scaleIn(
                    initialScale = 0.75f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = fadeOut(animationSpec = tween(200)) + scaleOut(
                    targetScale = 0.75f,
                    animationSpec = tween(200)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Animated Mesh Gradient Ambient Glow behind Dialog
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                    ) {
                        val w = size.width
                        val h = size.height

                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    theme_color_3.copy(alpha = 0.5f),
                                    theme_color_1.copy(alpha = 0.2f),
                                    Color.Transparent
                                ),
                                center = Offset(
                                    w * (0.3f + 0.3f * meshProgress),
                                    h * 0.35f
                                ),
                                radius = w * 0.55f
                            ),
                            radius = w * 0.55f,
                            center = Offset(
                                w * (0.3f + 0.3f * meshProgress),
                                h * 0.35f
                            )
                        )

                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    theme_color_4.copy(alpha = 0.4f),
                                    theme_color_2.copy(alpha = 0.15f),
                                    Color.Transparent
                                ),
                                center = Offset(
                                    w * (0.7f - 0.3f * meshProgress),
                                    h * 0.65f
                                ),
                                radius = w * 0.5f
                            ),
                            radius = w * 0.5f,
                            center = Offset(
                                w * (0.7f - 0.3f * meshProgress),
                                h * 0.65f
                            )
                        )
                    }

                    // Main Glassmorphism Card Container
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 28.dp,
                                shape = RoundedCornerShape(28.dp),
                                ambientColor = theme_color_2.copy(alpha = 0.4f),
                                spotColor = theme_color_1.copy(alpha = 0.5f)
                            )
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        theme_color_7,
                                        theme_color_6,
                                        theme_color_18
                                    )
                                ),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .border(
                                width = 1.5.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.95f),
                                        theme_color_1.copy(alpha = 0.7f),
                                        theme_color_2.copy(alpha = 0.9f)
                                    )
                                ),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .padding(28.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Pulsing Icon Badge
                            Box(
                                modifier = Modifier
                                    .size(76.dp)
                                    .scale(pulseScale)
                                    .shadow(elevation = 12.dp, shape = CircleShape, spotColor = theme_color_2)
                                    .background(
                                        brush = Brush.radialGradient(
                                            colors = listOf(
                                                theme_color_3,
                                                theme_color_1,
                                                theme_color_2
                                            )
                                        ),
                                        shape = CircleShape
                                    )
                                    .border(2.dp, Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.foundation.Image(
                                    painter = painterResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(38.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                GradientText(
                                    text = title,
                                    fontSize = 22.sp,
                                    fontFamily = HAFFER_TRIAL_BOLD
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = message,
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                color = Color(0xFF555555),
                                fontFamily = HAFFER_TRIAL_SEMI_BOLD,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(26.dp))

                            GradientButton(
                                onClick = onPositiveClick,
                                text = positiveButtonText,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}
