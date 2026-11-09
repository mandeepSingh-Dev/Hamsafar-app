package com.app.core_ui.components

import android.util.Log
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.core_ui.ui.theme.HAFFER_TRIAL_SEMI_BOLD
import com.app.core_ui.ui.theme.theme_color_1
import com.app.core_ui.ui.theme.theme_color_12
import com.app.core_ui.ui.theme.theme_color_2
import com.app.core_ui.ui.theme.theme_color_3
import com.app.core_ui.ui.theme.theme_color_4
import com.app.core_ui.ui.theme.theme_color_6
import com.app.core_ui.ui.theme.theme_color_7
import com.app.core_ui.ui.theme.theme_color_18
import com.zodiaq.ui.theme.Shape_20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCustomDialog(
    shouldShow: Boolean,
    datePickerState: DatePickerState,
    title: String?,
    onDismiss: () -> Unit = {},
    onDone: (Long?) -> Unit = {},
    onCancel: () -> Unit = {},
) {
    if (shouldShow) {
        val infiniteTransition = rememberInfiniteTransition(label = "mesh_gradient_transition")
        val meshProgress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = androidx.compose.animation.core.LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "mesh_progress"
        )

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = onDismiss
        ) {
            AnimatedVisibility(
                visible = shouldShow,
                enter = fadeIn(animationSpec = tween(300)) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = fadeOut(animationSpec = tween(200)) + scaleOut(
                    targetScale = 0.8f,
                    animationSpec = tween(200)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Animated Mesh Gradient Ambient Glow behind the Dialog
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    ) {
                        val w = size.width
                        val h = size.height

                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    theme_color_3.copy(alpha = 0.45f),
                                    theme_color_1.copy(alpha = 0.18f),
                                    Color.Transparent
                                ),
                                center = Offset(
                                    w * (0.25f + 0.35f * meshProgress),
                                    h * 0.3f
                                ),
                                radius = w * 0.6f
                            ),
                            radius = w * 0.6f,
                            center = Offset(
                                w * (0.25f + 0.35f * meshProgress),
                                h * 0.3f
                            )
                        )

                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    theme_color_4.copy(alpha = 0.35f),
                                    theme_color_2.copy(alpha = 0.12f),
                                    Color.Transparent
                                ),
                                center = Offset(
                                    w * (0.75f - 0.35f * meshProgress),
                                    h * 0.7f
                                ),
                                radius = w * 0.55f
                            ),
                            radius = w * 0.55f,
                            center = Offset(
                                w * (0.75f - 0.35f * meshProgress),
                                h * 0.7f
                            )
                        )
                    }

                    // Main Dialog Container with Glassmorphism / Mesh gradient surface
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 24.dp,
                                shape = Shape_20,
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
                                shape = Shape_20
                            )
                            .border(
                                width = 1.5.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.9f),
                                        theme_color_1.copy(alpha = 0.6f),
                                        theme_color_2.copy(alpha = 0.8f)
                                    )
                                ),
                                shape = Shape_20
                            )
                            .padding(vertical = 16.dp, horizontal = 12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false,
                                colors = DatePickerDefaults.colors().copy(
                                    containerColor = theme_color_7,
                                    dayContentColor = Color.Black,
                                    selectedDayContainerColor = theme_color_2,
                                    selectedDayContentColor = Color.White,
                                    todayContentColor = theme_color_12,
                                    todayDateBorderColor = theme_color_1,
                                    yearContentColor = Color.Black,
                                    selectedYearContainerColor = theme_color_2,
                                    selectedYearContentColor = Color.White,
                                    disabledSelectedYearContainerColor = theme_color_6,
                                    dividerColor = theme_color_1.copy(alpha = 0.3f),
                                    headlineContentColor = theme_color_12,
                                    subheadContentColor = theme_color_12
                                ),
                                title = {
                                    GradientText(
                                        text = title ?: "Select Date",
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp,
                                            vertical = 10.dp
                                        ),
                                        fontSize = 18.sp,
                                        fontFamily = HAFFER_TRIAL_SEMI_BOLD
                                    )
                                }
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 4.dp)
                            ) {
                                GradientButton(
                                    onClick = onCancel,
                                    text = "Cancel",
                                    modifier = Modifier.weight(1f),
                                    isGradient = false,
                                    isBorder = true,
                                    borderClr = theme_color_12
                                )
                                GradientButton(
                                    enabled = datePickerState.selectedDateMillis != null,
                                    onClick = {
                                        Log.d(
                                            "fknbkfnbf",
                                            datePickerState.selectedDateMillis.toString()
                                        )
                                        onDone(datePickerState.selectedDateMillis)
                                    },
                                    text = "Done",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
