package com.app.feature_splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.GradientButton
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.core_ui.ui.theme.theme_color_3
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@Composable
@Preview
fun LanguageSelectionScreen(
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    viewModel: LanguageSelectionViewModel = hiltViewModel()
) {
    ObserveUIEvents(viewModel.uiEvent) { event ->
        when (event) {
            is CommonUIEvents.NavigateTo -> {
                backStack.add(event.navKey)
            }
        }
    }

    BoxCommon(
        modifier = Modifier.fillMaxSize(),
        showBackgroundImage = false,
        isAppearanceLightStatusBars = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section (No back button as requested!)
            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Step indicator & Title
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                    Text(
                        text = stringResource(R.string.choose_your_language),
                        color = Color(0xFF2C1635),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.language_subtitle),
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }

                // Hamsafar Logo Brand Card with dropShadowCustom (instead of standard Card elevation)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadowCustom(shape = Shape_20, color = Color(0xFF4A154B).copy(alpha = 0.12f), radius = 12.dp)
                        .background(color = Color(0xFF4A154B), shape = Shape_20)
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(Color.White.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.heart_handshake),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            text = stringResource(R.string.app_name),
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.matrimony_subtitle),
                            color = theme_color_3,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Language Grid (2 columns)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(viewModel.languages) { language ->
                        val isSelected = viewModel.selectedLanguage == language.name
                        LanguageSelectionItem(
                            language = language,
                            isSelected = isSelected,
                            onClick = { viewModel.selectLanguage(language.name, language.tag) }
                        )
                    }
                }
            }

            // Bottom Continue Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Continue Button with dropShadowCustom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadowCustom(shape = CircleShape, color = Color(0xFF4A154B).copy(alpha = 0.3f), radius = 10.dp)
                ) {
                    GradientButton(
                        onClick = { viewModel.onContinueClicked() },
                        text = stringResource(R.string.continue_btn),
                        showEndIcon = true,
                        endIcon = R.drawable.right_arrow,
                        bgColor = Color(0xFF4A154B),
                        borderClr = Color(0xFF4A154B),
                        fontColor = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    text = stringResource(R.string.privacy_first),
                    color = Color(0xFF9CA3AF),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun LanguageSelectionItem(
    language: LanguageItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFF3E8FF) else Color.White
    val borderColor = if (isSelected) Color(0xFF8142E9) else Color(0xFFE5E7EB)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .dropShadowCustom(shape = Shape_15, color = if (isSelected) Color(0xFF8142E9).copy(alpha = 0.2f) else Color.Black.copy(0.04f), radius = 6.dp)
            .clip(Shape_15)
            .background(backgroundColor, Shape_15)
            .border(width = if (isSelected) 1.5.dp else 1.dp, color = borderColor, shape = Shape_15)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = language.nativeName,
                color = if (isSelected) Color(0xFF2C1635) else Color(0xFF374151),
                fontSize = 15.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
            if (language.nativeName != language.name) {
                Text(
                    text = language.name,
                    color = Color(0xFF9CA3AF),
                    fontSize = 12.sp
                )
            }
        }

        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(Color(0xFF8142E9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = "Selected",
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
