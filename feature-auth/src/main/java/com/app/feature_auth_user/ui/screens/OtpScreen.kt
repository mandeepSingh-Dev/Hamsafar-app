package com.app.feature_auth_user.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.feature_auth_user.ui.events.OtpEvent
import com.app.feature_auth_user.ui.viewmodels.OtpViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.CommonOutlineButton
import com.app.core_ui.components.GradientButton
import com.app.core_ui.components.TimerWithResend
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.zodiaq.ui.theme.Shape_15

@Composable
fun OtpScreen(
    phoneNumber: String,
    countryCode: String,
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val otpLength = 6
    
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonAuthHeader(
                    title = "Hamsafar",
                    leftIcon = R.drawable.back,
                    rightIcon = R.drawable.baseline_info_24,
                    backStack = backStack
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "01 · GETTING STARTED",
                        color = Color(0xFF6B7280),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Check your messages",
                        color = Color(0xFF2C1635),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 36.sp
                    )
                    Text(
                        text = "Enter the 6-digit code sent to $countryCode $phoneNumber",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Verification code",
                        color = Color(0xFF2C1635),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    BasicTextField(
                        value = uiState?.otpValue ?: "",
                        onValueChange = {
                            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                                viewModel.onEvent(OtpEvent.OnOtpChanged(it))
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        decorationBox = {
                            Box {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val currentOtp = uiState?.otpValue ?: ""
                                    repeat(otpLength) { index ->
                                        val char = when {
                                            index >= currentOtp.length -> ""
                                            else -> currentOtp[index].toString()
                                        }
                                        
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(55.dp)
                                                .background(Color.White, Shape_15)
                                                .border(
                                                    width = 1.dp,
                                                    color = if (uiState?.isError == true) Color(0xFFEF4444) else Color(0xFF2C1635),
                                                    shape = Shape_15
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = char,
                                                fontSize = 20.sp,
                                                color = Color(0xFF2C1635),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    )
                    
                    TimerWithResend(
                        onResendClick = {
                            viewModel.onEvent(OtpEvent.OnResendClicked)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Keep your code private Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_15)
                        .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Keep your code private",
                            color = Color(0xFF2C1635),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Hamsafar will never ask you to share an OTP with a member or support agent.",
                            color = Color(0xFF374151),
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadowCustom(shape = CircleShape, color = Color(0xFF4A154B).copy(0.3f), radius = 10.dp)
                ) {
                    GradientButton(
                        onClick = { viewModel.onEvent(OtpEvent.OnVerifyClicked) },
                        text = "Verify and continue",
                        showEndIcon = true,
                        endIcon = R.drawable.right_arrow,
                        bgColor = Color(0xFF4A154B),
                        borderClr = Color(0xFF4A154B),
                        fontColor = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                CommonOutlineButton(
                    onClick = { backStack.removeLastOrNull() },
                    text = "Change phone number",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Privacy first. Always your choice.",
                    color = Color(0xFF9CA3AF),
                    fontSize = 12.sp
                )
            }
        }
    }
}
