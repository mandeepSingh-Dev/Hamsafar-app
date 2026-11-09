package com.app.feature_auth_user.ui.screens.profile_setup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.core_ui.components.clickableDebAnim
import com.zodiaq.ui.theme.Shape_15

@Composable
fun ProfileOptionPill(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) Color(0xFF4A154B) else Color(0xFFE5E7EB)
    val bgColor = if (isSelected) Color(0xFF4A154B).copy(alpha = 0.05f) else Color.White
    val textColor = if (isSelected) Color(0xFF4A154B) else Color(0xFF374151)
    
    Row(
        modifier = modifier
            .background(bgColor, Shape_15)
            .border(1.dp, borderColor, Shape_15)
            .clip(Shape_15)
            .clickableDebAnim { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.White, androidx.compose.foundation.shape.CircleShape)
                    .border(1.dp, Color(0xFF4A154B), androidx.compose.foundation.shape.CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(10.dp).background(Color(0xFF4A154B), androidx.compose.foundation.shape.CircleShape))
            }
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color(0xFF9CA3AF), androidx.compose.foundation.shape.CircleShape)
            )
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProfileInfoCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, Shape_15)
            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = title,
                color = Color(0xFF2C1635),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                color = Color(0xFF374151),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}
