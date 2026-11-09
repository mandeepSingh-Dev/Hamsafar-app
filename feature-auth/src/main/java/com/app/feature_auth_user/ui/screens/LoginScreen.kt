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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.GradientButton
import com.app.core_ui.components.TextFieldOuterLabel
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.core_ui.local_providers.LocalCurrentLanguage
import com.app.core_ui.ui.theme.hamsafar_primary
import com.app.feature_auth_user.ui.events.LoginScreenEvents
import com.app.feature_auth_user.ui.viewmodels.LoginViewModel
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

data class Country(
    val name: String,
    val code: String
)

val countriesList = listOf(
    Country("India", "+91"),
    Country("United States", "+1"),
    Country("United Kingdom", "+44"),
    Country("United Arab Emirates", "+971"),
    Country("Canada", "+1"),
    Country("Australia", "+61"),
    Country("Pakistan", "+92"),
    Country("Bangladesh", "+880"),
    Country("Nepal", "+977"),
    Country("Sri Lanka", "+94"),
    Country("Saudi Arabia", "+966"),
    Country("Qatar", "+974"),
    Country("Kuwait", "+965"),
    Country("Oman", "+968"),
    Country("Bahrain", "+973"),
    Country("Singapore", "+65"),
    Country("Malaysia", "+60"),
    Country("New Zealand", "+64"),
    Country("Germany", "+49"),
    Country("France", "+33"),
    Country("Italy", "+39"),
    Country("Spain", "+34"),
    Country("Netherlands", "+31"),
    Country("Ireland", "+353"),
    Country("Switzerland", "+41"),
    Country("Sweden", "+46"),
    Country("Norway", "+47"),
    Country("Denmark", "+45"),
    Country("Poland", "+48"),
    Country("South Africa", "+27"),
    Country("Mauritius", "+230"),
    Country("Fiji", "+679"),
    Country("Thailand", "+66"),
    Country("Indonesia", "+62"),
    Country("Philippines", "+63"),
    Country("Vietnam", "+84"),
    Country("Japan", "+81"),
    Country("South Korea", "+82"),
    Country("Hong Kong", "+852"),
    Country("Bhutan", "+975"),
    Country("Maldives", "+960"),
    Country("Turkey", "+90"),
    Country("Egypt", "+20"),
    Country("Kenya", "+254"),
    Country("Nigeria", "+234"),
    Country("Brazil", "+55"),
    Country("Mexico", "+52"),
    Country("Argentina", "+54"),
    Country("Russia", "+7")
)

@Composable
fun CountryPickerDialog(
    onDismiss: () -> Unit,
    onCountrySelected: (String, String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCountries = remember(searchQuery) {
        if (searchQuery.isBlank()) countriesList
        else countriesList.filter {
            it.name.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery)
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .dropShadowCustom(shape = Shape_20, color = Color.Black.copy(0.15f), radius = 16.dp)
                .background(Color.White, Shape_20)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Select Country Code",
                    color = hamsafar_primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search country...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredCountries) { country ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCountrySelected(country.code, country.name)
                                    onDismiss()
                                }
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = country.name,
                                color = Color(0xFF111827),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = country.code,
                                color = hamsafar_primary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun LoginScreen(
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val currentLanguage = LocalCurrentLanguage.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveUIEvents(viewModel.uiEvent) { event ->
        when (event) {
            is CommonUIEvents.NavigateTo -> {
                backStack.add(event.navKey)
            }
        }
    }

    if (uiState?.showCountryPicker == true) {
        CountryPickerDialog(
            onDismiss = { viewModel.onEvent(LoginScreenEvents.OnDismissCountryPicker) },
            onCountrySelected = { code, name ->
                viewModel.onEvent(LoginScreenEvents.OnCountrySelected(code, name))
            }
        )
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
            // Header & Form Content
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonAuthHeader(
                    title = "Hamsafar ($currentLanguage)",
                    leftIcon = R.drawable.baseline_close_24,
                    rightIcon = R.drawable.baseline_info_24,
                    backStack = backStack
                )

                // Title & Subtitle ("Welcome back!")
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Welcome back!",
                        color = Color(0xFF2C1635),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Please sign in to continue to your account.",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Country Code Picker Box
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Country code",
                        color = Color(0xFF374151),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .dropShadowCustom(shape = Shape_15, color = Color.Black.copy(0.04f), radius = 6.dp)
                            .background(Color.White, Shape_15)
                            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                            .clickable { viewModel.onEvent(LoginScreenEvents.OnShowCountryPickerClicked) }
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${uiState?.countryName ?: "India"} ${uiState?.countryCode ?: "+91"}",
                                color = Color(0xFF111827),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Image(
                                painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Mobile Number Field with hint "Enter registered number"
                TextFieldOuterLabel(
                    label = "Mobile number",
                    value = uiState?.phoneNumber ?: "",
                    singleLine = true,
                    onValueChange = { viewModel.onEvent(LoginScreenEvents.OnPhoneNumberChanged(it)) },
                    hint = "Enter registered number",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isBorder = true,
                    borderColor = Color(0xFFE5E7EB),
                    bgColor = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Send Verification Code Button with dropShadowCustom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadowCustom(shape = CircleShape, color = Color(0xFF4A154B).copy(0.3f), radius = 10.dp)
                ) {
                    GradientButton(
                        onClick = { viewModel.onEvent(LoginScreenEvents.OnSendVerificationCodeClicked) },
                        text = "Send verification code",
                        showEndIcon = true,
                        endIcon = R.drawable.right_arrow,
                        bgColor = Color(0xFF4A154B),
                        borderClr = Color(0xFF4A154B),
                        fontColor = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Or continue with (Google, Apple)
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Or continue with",
                        color = Color(0xFF374151),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Google Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .dropShadowCustom(shape = Shape_15, color = Color.Black.copy(0.04f), radius = 6.dp)
                                .background(Color.White, Shape_15)
                                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                                .clickable { viewModel.onEvent(LoginScreenEvents.OnGoogleLoginClicked) }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Google",
                                    color = Color(0xFF2C1635),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Apple Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .dropShadowCustom(shape = Shape_15, color = Color.Black.copy(0.04f), radius = 6.dp)
                                .background(Color.White, Shape_15)
                                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                                .clickable { viewModel.onEvent(LoginScreenEvents.OnAppleLoginClicked) }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Apple",
                                    color = Color(0xFF2C1635),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Section: Create Account Button & Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
