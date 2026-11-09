package com.app.core_ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_ui.local_providers.LocalBackStackNav


@Composable
fun GuestAlertDialog(
    backstack : NavBackStack<NavKey> = LocalBackStackNav.current,
    onDismiss: () -> Unit = {},
    onLogin: () -> Unit = {},
    screen: NavKey
) {
    val gold1 = Color(0xFFDFA031)
    val gold2 = Color(0xFFC98620)
    val gold3 = Color(0xFFFAF29A)
    val gold4 = Color(0xFFFFBF61)
    val gold5 = Color(0xFFF5C563)

    val shape = RoundedCornerShape(30.dp)

    val transition = rememberInfiniteTransition(label = "guest_dialog")

    val meshProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mesh"
    )

    val glowProgress by transition.animateFloat(
        initialValue = 0.65f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    val buttonProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "button"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            contentAlignment = Alignment.Center
        ) {
            // Animated ambient mesh glow behind the card
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp)
            ) {
                val w = size.width
                val h = size.height

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            gold3.copy(alpha = 0.35f),
                            gold1.copy(alpha = 0.12f),
                            Color.Transparent
                        ),
                        center = Offset(
                            w * (0.2f + 0.45f * meshProgress),
                            h * 0.25f
                        ),
                        radius = w * 0.55f
                    ),
                    radius = w * 0.55f,
                    center = Offset(
                        w * (0.2f + 0.45f * meshProgress),
                        h * 0.25f
                    )
                )

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            gold4.copy(alpha = 0.28f),
                            gold2.copy(alpha = 0.10f),
                            Color.Transparent
                        ),
                        center = Offset(
                            w * (0.8f - 0.35f * meshProgress),
                            h * 0.75f
                        ),
                        radius = w * 0.48f
                    ),
                    radius = w * 0.48f,
                    center = Offset(
                        w * (0.8f - 0.35f * meshProgress),
                        h * 0.75f
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 32.dp,
                        shape = shape,
                        ambientColor = gold2.copy(alpha = 0.25f),
                        spotColor = gold1.copy(alpha = 0.35f)
                    )
                    .clip(shape)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFFEFA),
                                Color(0xFFFFF9EC),
                                Color(0xFFFFFDF8)
                            )
                        )
                    )
                    // Animated gold border and subtle inner highlight
                    .drawBehind {
                        drawRoundRect(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    gold3.copy(alpha = 0.85f),
                                    gold1.copy(alpha = 0.45f),
                                    gold5.copy(alpha = 0.9f),
                                    gold2.copy(alpha = 0.45f)
                                ),
                                start = Offset(
                                    size.width * buttonProgress,
                                    0f
                                ),
                                end = Offset(
                                    size.width * (1f - buttonProgress),
                                    size.height
                                )
                            ),
                            style = Stroke(width = 1.4.dp.toPx()),
                            cornerRadius = CornerRadius(30.dp.toPx())
                        )

                        // Inner top highlight
                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.8f),
                                    Color.Transparent
                                ),
                                startY = 0f,
                                endY = 42.dp.toPx()
                            ),
                            cornerRadius = CornerRadius(30.dp.toPx())
                        )
                    }
                    .padding(
                        horizontal = 25.dp,
                        vertical = 30.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Animated premium icon
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .graphicsLayer {
                            scaleX = 0.98f + glowProgress * 0.04f
                            scaleY = 0.98f + glowProgress * 0.04f
                        }
                        .shadow(
                            elevation = (10 * glowProgress).dp,
                            shape = CircleShape,
                            ambientColor = gold1.copy(alpha = 0.45f),
                            spotColor = gold2.copy(alpha = 0.35f)
                        )
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    gold3,
                                    gold4,
                                    gold1
                                )
                            )
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.9f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.65f),
                                        Color.Transparent
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null,
                            tint = Color(0xFF75450B),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Small premium label
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(gold1.copy(alpha = 0.12f))
                        .border(
                            1.dp,
                            gold2.copy(alpha = 0.22f),
                            CircleShape
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                )
                {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = gold2,
                        modifier = Modifier.size(14.dp)
                    )

                    Text(
                        text = "MEMBER ACCESS",
                        color = Color(0xFF87550F),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.4.sp
                    )
                }

                Spacer(Modifier.height(17.dp))

                Text(
                    text = "Login Required",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF302516),
                    textAlign = TextAlign.Center,
                    letterSpacing = (-0.5).sp
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "You're currently using Guest Mode. Please log in to access this feature and enjoy all its benefits.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF777064),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(28.dp))

                GradientButton(text = "Login Now",
                    onClick = {
                        backstack.removeAll(backstack)
                        backstack.add(screen)
                        onLogin()
                              },
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 20.dp)
                    )

                Spacer(Modifier.height(13.dp))

                Text(
                    text = "Maybe Later",
                    color = Color(0xFF8D7859),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onDismiss() }
                        .padding(
                            horizontal = 22.dp,
                            vertical = 10.dp
                        )
                )
            }
        }
    }
}
