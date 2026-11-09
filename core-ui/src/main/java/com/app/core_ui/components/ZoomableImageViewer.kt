package com.app.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.core_ui.R

@Composable
fun ZoomableImageViewer(
    imageUrl: Any?,
    title: String = "Photo Viewer",
    onDismiss: () -> Unit,
    onRemove: () -> Unit,
    onMakeMainPhoto: (() -> Unit)? = null
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 5f)
                    if (scale > 1f) {
                        offsetX += pan.x
                        offsetY += pan.y
                    } else {
                        offsetX = 0f
                        offsetY = 0f
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Zoomable Image
        AsyncImageWithLoader(
            image = imageUrl,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
            contentScale = ContentScale.Fit
        )

        // Top bar with close cross icon, title, and remove / make main options
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Close / Cross icon
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .size(42.dp)
                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_close_24),
                    contentDescription = "Close",
                    tint = Color.White
                )
            }

            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (onMakeMainPhoto != null) {
                    IconButton(
                        onClick = {
                            onMakeMainPhoto()
                            onDismiss()
                        },
                        modifier = Modifier
                            .size(42.dp)
                            .background(Color(0xFF4A154B), CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_star_24),
                            contentDescription = "Make Main Photo",
                            tint = Color.White
                        )
                    }
                }

                // Remove / Delete icon
                IconButton(
                    onClick = {
                        onRemove()
                        onDismiss()
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color.Red.copy(alpha = 0.8f), CircleShape)
                    ) {
                    Icon(
                        painter = painterResource(R.drawable.round_delete_24),
                        contentDescription = "Remove Photo",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
