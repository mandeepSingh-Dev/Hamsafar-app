package com.app.feature_auth_user.ui.screens.profile_setup

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.CommonOutlineButton
import com.app.core_ui.components.DropDownTextField
import com.app.core_ui.components.GradientButton
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.feature_auth_user.ui.events.ProfileSetupEvent
import com.app.feature_auth_user.ui.screens.profile_setup.components.ProfileInfoCard
import com.app.feature_auth_user.ui.viewmodels.ProfileSetupViewModel
import com.zodiaq.ui.theme.Shape_15

@Composable
fun BiodataScreen(
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    viewModel: ProfileSetupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header & Content Scroll
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                CommonAuthHeader(
                    title = "Hamsafar",
                    leftIcon = R.drawable.back,
                    rightIcon = R.drawable.baseline_info_24,
                    backStack = backStack
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Scrollable Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "01 · GETTING STARTED",
                        color = Color(0xFF6B7280),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Bring your biodata along",
                        color = Color(0xFF2C1635),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 36.sp
                    )
                    Text(
                        text = "Upload a PDF or photo. Review what we read before saving.",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Upload Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, Shape_15)
                            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                            .padding(16.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Upload biodata",
                                    color = Color(0xFF2C1635),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "PDF, JPG or PNG · up to 10 MB",
                                    color = Color(0xFF6B7280),
                                    fontSize = 14.sp
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Choose a file",
                                    color = Color(0xFF2C1635),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.clickable { /* Handle file picking */ }
                                )
                                Text(
                                    text = "Your document is private.",
                                    color = Color(0xFF6B7280),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Editable preview",
                        color = Color(0xFF2C1635),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Editable Fields with Subtitles
                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropDownTextField(
                            label = "Name",
                            value = uiState?.biodataName ?: "",
                            hint = "Aditi Sharma",
                            list = listOf("Aditi Sharma"),
                            getText = { it },
                            onSelect = { viewModel.onEvent(ProfileSetupEvent.OnBiodataNameChanged(it)) },
                            trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
                        )
                        Text(
                            text = "High confidence · Confirmed",
                            color = Color(0xFF6B7280),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropDownTextField(
                            label = "Education",
                            value = uiState?.biodataEducation ?: "",
                            hint = "B.Des, NID Ahmedabad",
                            list = listOf("B.Des, NID Ahmedabad"),
                            getText = { it },
                            onSelect = { viewModel.onEvent(ProfileSetupEvent.OnBiodataEducationChanged(it)) },
                            trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
                        )
                        Text(
                            text = "High confidence",
                            color = Color(0xFF6B7280),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        DropDownTextField(
                            label = "Employer",
                            value = uiState?.biodataEmployer ?: "",
                            hint = "Studio North",
                            list = listOf("Studio North"),
                            getText = { it },
                            onSelect = { viewModel.onEvent(ProfileSetupEvent.OnBiodataEmployerChanged(it)) },
                            trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
                        )
                        Text(
                            text = "Please confirm before saving",
                            color = Color(0xFF6B7280),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileInfoCard(
                        title = "Nothing publishes automatically",
                        description = "Only the fields you approve will be added to your profile."
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Bottom Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadowCustom(shape = CircleShape, color = Color(0xFF4A154B).copy(0.3f), radius = 10.dp)
                ) {
                    GradientButton(
                        onClick = {
                            // final save
                        },
                        text = "Approve selected fields",
                        showEndIcon = true,
                        endIcon = R.drawable.right_arrow,
                        bgColor = Color(0xFF4A154B),
                        borderClr = Color(0xFF4A154B),
                        fontColor = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                CommonOutlineButton(
                    onClick = {
                        // go to manual entry
                    },
                    text = "Enter details manually",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Privacy first. Always your choice.",
                    color = Color(0xFF9CA3AF),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}