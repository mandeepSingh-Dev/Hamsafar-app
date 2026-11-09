package com.app.feature_auth_user.ui.screens.profile_setup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_common.utils.CommonUIEvents
import com.app.core_common.utils.ObserveUIEvents
import com.app.core_navigation.AppScreens
import com.app.core_navigation.UserScreens
import com.app.core_ui.R
import com.app.core_ui.components.AsyncImageWithLoader
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.CommonOutlineButton
import com.app.core_ui.components.DatePickerCustomDialog
import com.app.core_ui.components.DropDownTextField
import com.app.core_ui.components.GradientButton
import com.app.core_ui.components.ImageOptionsSelector
import com.app.core_ui.components.TextFieldOuterLabel
import com.app.core_ui.components.ZoomableImageViewer
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.app.feature_auth_user.ui.events.ProfileSetupEvent
import com.app.feature_auth_user.ui.screens.profile_setup.components.ProfileInfoCard
import com.app.feature_auth_user.ui.screens.profile_setup.components.ProfileOptionPill
import com.app.feature_auth_user.ui.states.ProfileSetupState
import com.app.feature_auth_user.ui.viewmodels.ProfileSetupViewModel
import com.zodiaq.ui.theme.Shape_15
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    step: Int?,
    dob: String?,
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current,
    viewModel: ProfileSetupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentStep = step ?: 1

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

                // Scrollable Content with AnimatedContent for smooth transitions between steps
                AnimatedContent(
                    targetState = currentStep,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally(animationSpec = tween(300)) { width -> width } + fadeIn(animationSpec = tween(300)) togetherWith
                                slideOutHorizontally(animationSpec = tween(300)) { width -> -width } + fadeOut(animationSpec = tween(300))
                        } else {
                            slideInHorizontally(animationSpec = tween(300)) { width -> -width } + fadeIn(animationSpec = tween(300)) togetherWith
                                slideOutHorizontally(animationSpec = tween(300)) { width -> width } + fadeOut(animationSpec = tween(300))
                        }
                    },
                    label = "profile_step_animation",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { targetStep ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Profile Step indicator
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "PROFILE · $targetStep OF 10",
                                color = Color(0xFF6B7280),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Saved just now",
                                color = Color(0xFF9CA3AF),
                                fontSize = 12.sp
                            )
                        }

                        when (targetStep) {
                            1 -> ProfileStep1Content(uiState = uiState, onEvent = viewModel::onEvent)
                            2 -> ProfileStep2Content(uiState = uiState, onEvent = viewModel::onEvent)
                            3 -> ProfileStep3Content(uiState = uiState, onEvent = viewModel::onEvent)
                            4 -> ProfileStep4Content(uiState = uiState, onEvent = viewModel::onEvent)
                            5 -> ProfileStep5Content(uiState = uiState, onEvent = viewModel::onEvent)
                            6 -> ProfileStep6Content(uiState = uiState, onEvent = viewModel::onEvent)
                            7 -> ProfileStep7Content(uiState = uiState, onEvent = viewModel::onEvent)
                            8 -> ProfileStep8Content(uiState = uiState, onEvent = viewModel::onEvent)
                            9 -> ProfileStep9Content(uiState = uiState, onEvent = viewModel::onEvent)
                            10 -> ProfileStep10Content(uiState = uiState, onEvent = viewModel::onEvent)
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                    }
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
                            if (currentStep < 10) {
                                backStack.add(UserScreens.AuthScreens.ProfileScreen(dob = uiState?.dob, step = currentStep + 1))
                            } else {
                                backStack.add(AppScreens.HomeBottomContainerScreen)
                            }
                        },
                        text = if (currentStep == 10) "Save gallery" else "Save and continue",
                        showEndIcon = true,
                        endIcon = R.drawable.right_arrow,
                        bgColor = Color(0xFF4A154B),
                        borderClr = Color(0xFF4A154B),
                        fontColor = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (currentStep == 3 || currentStep == 4 || currentStep == 8) {
                    CommonOutlineButton(
                        onClick = {
                            backStack.add(UserScreens.AuthScreens.ProfileScreen(dob = uiState?.dob, step = currentStep + 1))
                        },
                        text = "Skip for now",
                        modifier = Modifier.fillMaxWidth()
                    )
                }

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileStep1Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Who is this profile for?",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "A meaningful journey starts with the person at its heart.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val options = listOf("Myself", "Son", "Daughter", "Brother", "Sister", "Relative", "Friend")
        options.forEach { option ->
            ProfileOptionPill(
                text = option,
                isSelected = uiState?.profileFor == option,
                onClick = { onEvent(ProfileSetupEvent.OnProfileForChanged(option)) }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    ProfileInfoCard(
        title = "The candidate stays in control",
        description = "If you're helping someone, we'll invite them to approve their profile and choose your access. Their private chats stay private."
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileStep2Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    DatePickerCustomDialog(
        shouldShow = showDatePicker,
        datePickerState = datePickerState,
        title = "Select Date of Birth",
        onDismiss = { showDatePicker = false },
        onDone = { timestamp ->
            showDatePicker = false
            timestamp?.let {
                val formatted = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date(it))
                onEvent(ProfileSetupEvent.OnDobChanged(formatted))
            }
        },
        onCancel = { showDatePicker = false }
    )

    Text(
        text = "A little about you",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Start with the basics. You can decide what to share later.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextFieldOuterLabel(
        label = "Full name",
        value = uiState?.fullName ?: "",
        hint = "Aditi Sharma",
        onValueChange = { onEvent(ProfileSetupEvent.OnFullNameChanged(it)) },
        isBorder = true,
        borderColor = Color(0xFF4A154B),
        bgColor = Color.White
    )
    Text("Use the name on your identity document.", color = Color(0xFF6B7280), fontSize = 12.sp)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Date of birth",
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color.White, Shape_15)
                .border(
                    width = 1.dp,
                    color = if (showDatePicker) Color(0xFF4A154B) else Color(0xFFE5E7EB),
                    shape = Shape_15
                )
                .clickable { showDatePicker = true }
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (uiState?.dob.isNullOrEmpty()) "18 August 1998" else uiState?.dob ?: "",
                    color = if (uiState?.dob.isNullOrEmpty()) Color(0xFFB3B3B3) else Color.Black,
                    fontSize = 14.sp
                )
                Image(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
    
    Text("Your exact birth date stays private.", color = Color(0xFF6B7280), fontSize = 12.sp)
    
    DropDownTextField(
        label = "Gender",
        value = uiState?.gender ?: "",
        hint = "Select Gender",
        list = listOf("Male", "Female"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnGenderChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileStep3Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Marital status & Physical details",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Share details about your marital status, height, and health.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Marital status",
        value = uiState?.maritalStatus ?: "",
        hint = "Never married",
        list = listOf("Never married", "Divorced", "Widowed", "Awaiting Divorce", "Annulled"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnMaritalStatusChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Height with Slider
    val currentHeight = uiState?.height ?: 170f
    val totalInches = (currentHeight / 2.54).toInt()
    val feet = totalInches / 12
    val inches = totalInches % 12
    val heightStr = "$feet ft $inches in (${currentHeight.toInt()} cm)"

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Height",
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = heightStr,
                color = Color(0xFF4A154B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Slider(
            value = currentHeight,
            onValueChange = { onEvent(ProfileSetupEvent.OnHeightChanged(it)) },
            valueRange = 140f..220f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF4A154B),
                activeTrackColor = Color(0xFF4A154B),
                inactiveTrackColor = Color(0xFFE5E7EB)
            )
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Any Disability?",
        color = Color(0xFF2C1635),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val options = listOf("No", "Yes")
        options.forEach { option ->
            ProfileOptionPill(
                text = option,
                isSelected = uiState?.hasDisability == option,
                onClick = { onEvent(ProfileSetupEvent.OnDisabilityChanged(option)) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileStep4Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Your community, your choice",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "All religions and communities are welcome. These answers are optional.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Religion · optional",
        value = uiState?.religion ?: "",
        hint = "Prefer not to say",
        list = listOf("Prefer not to say", "Hindu", "Muslim", "Christian", "Sikh", "Buddhist", "Jain", "Other"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnReligionChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "Community · optional",
        value = uiState?.community ?: "",
        hint = "No bar",
        list = listOf("No bar", "Brahmin", "Rajput", "Maratha", "Yadav", "Other"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnCommunityChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "What feels right to you?",
        color = Color(0xFF2C1635),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val options = listOf("No bar", "Prefer not to say")
        options.forEach { option ->
            ProfileOptionPill(
                text = option,
                isSelected = uiState?.communityPreference == option,
                onClick = { onEvent(ProfileSetupEvent.OnCommunityPreferenceChanged(option)) }
            )
        }
    }
}

@Composable
fun ProfileStep5Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Where do you live?",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Provide location and residency details.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Country living in",
        value = uiState?.countryOfResidence ?: "",
        hint = "Select country",
        list = listOf("India", "United States", "United Kingdom", "Canada", "Australia", "UAE"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnCountryLivingChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "State living in",
        value = uiState?.stateOfResidence ?: "",
        hint = "Select state",
        list = listOf("Karnataka", "Maharashtra", "Delhi", "Tamil Nadu", "Uttar Pradesh", "Telangana", "Kerala", "Gujarat"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnStateLivingChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "Residency status",
        value = uiState?.residencyStatus ?: "",
        hint = "Select residency status",
        list = listOf("Citizen", "Permanent Resident", "Work Permit", "Student Visa", "Temporary Visa"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnResidencyStatusChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    TextFieldOuterLabel(
        label = "Zip / PIN code",
        value = uiState?.zipCode ?: "",
        hint = "560001",
        onValueChange = { onEvent(ProfileSetupEvent.OnZipCodeChanged(it)) },
        isBorder = true,
        borderColor = Color(0xFF4A154B),
        bgColor = Color.White
    )

    DropDownTextField(
        label = "Grew up in",
        value = uiState?.grewUpIn ?: "",
        hint = "Select place",
        list = listOf("India", "United States", "United Kingdom", "Canada", "Australia", "UAE", "Other"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnGrewUpInChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )
}

@Composable
fun ProfileStep6Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Your Education",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Share your highest qualification and college.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Highest qualification",
        value = uiState?.highestQualification ?: "",
        hint = "Select qualification",
        list = listOf("B.Tech", "B.Com", "B.A", "M.Tech", "MBA", "MBBS", "Ph.D", "B.Sc", "M.Sc", "Other"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnHighestQualificationChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    TextFieldOuterLabel(
        label = "College attended",
        value = uiState?.collegeAttended ?: "",
        hint = "e.g. IIT Bombay, Delhi University",
        onValueChange = { onEvent(ProfileSetupEvent.OnCollegeAttendedChanged(it)) },
        isBorder = true,
        borderColor = Color(0xFF4A154B),
        bgColor = Color.White
    )
}

@Composable
fun ProfileStep7Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Your Career",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Tell us about your profession and income.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Working with",
        value = uiState?.workingWith ?: "",
        hint = "Select sector",
        list = listOf("Private company", "Government", "Defence", "Business / Self-Employed", "Civil Services", "Not Working"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnWorkingWithChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "Working as",
        value = uiState?.workingAs ?: "",
        hint = "Select profession",
        list = listOf("Software Engineer", "Sportsman", "Sales Professional", "Doctor", "Teacher", "Engineer", "Manager", "Designer", "Accountant", "Lawyer", "Other"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnWorkingAsChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "Annual income range",
        value = uiState?.annualIncome ?: "",
        hint = "Select income range",
        list = listOf("Upto INR 1 Lakh", "1 Lakh to 2 Lakhs", "2 Lakhs to 5 Lakhs", "5 Lakhs to 10 Lakhs", "10 Lakhs to 20 Lakhs", "20 Lakhs to 50 Lakhs", "50+ Lakhs"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnAnnualIncomeChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    TextFieldOuterLabel(
        label = "Employer name",
        value = uiState?.employerName ?: "",
        hint = "e.g. Google, TCS, Microsoft",
        onValueChange = { onEvent(ProfileSetupEvent.OnEmployerNameChanged(it)) },
        isBorder = true,
        borderColor = Color(0xFF4A154B),
        bgColor = Color.White
    )
}

@Composable
fun ProfileStep8Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    Text(
        text = "Family Details",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Tell us a bit about your family.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    DropDownTextField(
        label = "Mother details",
        value = uiState?.motherDetails ?: "",
        hint = "Select mother's occupation",
        list = listOf("Homemaker", "Businesswoman", "Retired", "Working Professional", "Teacher", "Doctor", "Government Employee"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnMotherDetailsChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "Father details",
        value = uiState?.fatherDetails ?: "",
        hint = "Select father's occupation",
        list = listOf("Employed", "Businessman", "Retired", "Government Employee", "Doctor", "Teacher", "Farmer", "Expired/Late"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnFatherDetailsChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "No. of sisters",
        value = uiState?.numberOfSisters ?: "0",
        hint = "Select number of sisters",
        list = listOf("0", "1", "2", "3", "4+"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnSistersCountChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )

    DropDownTextField(
        label = "No. of brothers",
        value = uiState?.numberOfBrothers ?: "0",
        hint = "Select number of brothers",
        list = listOf("0", "1", "2", "3", "4+"),
        getText = { it },
        onSelect = { onEvent(ProfileSetupEvent.OnBrothersCountChanged(it)) },
        trailingIcon = R.drawable.baseline_keyboard_arrow_down_24
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileStep9Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    if (uiState?.isHobbiesBottomSheetOpen == true) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(ProfileSetupEvent.OnHobbiesBottomSheetToggled(false)) },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Select Hobbies & Interests",
                    color = Color(0xFF2C1635),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fun",
                    color = Color(0xFF4A154B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val funList = listOf("Movies", "Music", "Travel", "Reading", "Photography", "Sports", "Gaming", "Art")
                    funList.forEach { interest ->
                        ProfileOptionPill(
                            text = interest,
                            isSelected = uiState.hobbiesAndInterests.contains(interest),
                            onClick = { onEvent(ProfileSetupEvent.OnToggleInterest(interest)) }
                        )
                    }
                }

                Text(
                    text = "Creative",
                    color = Color(0xFF4A154B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val creativeList = listOf("Writing", "Cooking", "Painting", "Dancing", "Gardening", "Singing", "Design")
                    creativeList.forEach { interest ->
                        ProfileOptionPill(
                            text = interest,
                            isSelected = uiState.hobbiesAndInterests.contains(interest),
                            onClick = { onEvent(ProfileSetupEvent.OnToggleInterest(interest)) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    Text(
        text = "Lifestyle & Interests",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Share your diet preferences and hobbies.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Diet",
        color = Color(0xFF2C1635),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val diets = listOf("Veg", "Non-veg", "Occasionally non-veg", "Eggetarian", "Vegan", "Jain")
        diets.forEach { option ->
            ProfileOptionPill(
                text = option,
                isSelected = uiState?.diet == option,
                onClick = { onEvent(ProfileSetupEvent.OnDietChanged(option)) }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Hobbies & Interests",
        color = Color(0xFF2C1635),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, Shape_15)
            .border(1.dp, Color(0xFF4A154B), Shape_15)
            .clickable { onEvent(ProfileSetupEvent.OnHobbiesBottomSheetToggled(true)) }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (uiState?.hobbiesAndInterests.isNullOrEmpty()) "Select your hobbies & interests" else "${uiState?.hobbiesAndInterests?.size} selected",
                color = if (uiState?.hobbiesAndInterests.isNullOrEmpty()) Color(0xFFB3B3B3) else Color(0xFF4A154B),
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

    if (!uiState?.hobbiesAndInterests.isNullOrEmpty()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            uiState?.hobbiesAndInterests?.forEach { interest ->
                ProfileOptionPill(text = interest, isSelected = true, onClick = {})
            }
        }
    }
}

@Composable
fun ProfileStep10Content(
    uiState: ProfileSetupState?,
    onEvent: (ProfileSetupEvent) -> Unit
) {
    val photos = uiState?.profilePhotos ?: listOf(null, null, null, null, null, null, null, null)
    val filledCount = photos.count { !it.isNullOrEmpty() }

    // Coordinates of each box for drag-and-drop swapping
    val boxBoundsMap = remember { mutableStateMapOf<Int, androidx.compose.ui.geometry.Rect>() }
    var draggingIndex by remember { mutableStateOf<Int?>(null) }
    var dragOffset by remember { mutableStateOf(androidx.compose.ui.geometry.Offset.Zero) }

    // Gallery picker launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onEvent(ProfileSetupEvent.OnAddPhoto(it.toString()))
        }
    }

    // Image Options Selector (Camera / Gallery BottomSheet)
    ImageOptionsSelector(
        showDialog = uiState?.isImageOptionsSelectorOpen == true,
        onDismiss = { onEvent(ProfileSetupEvent.OnCloseImageOptions(false)) },
        onCamera = {
            galleryLauncher.launch("image/*")
        },
        onGallery = {
            galleryLauncher.launch("image/*")
        }
    )

    // Zoomable Image Viewer Dialog (Enlarge image when clicked, with cross icon to close and delete option)
    if (uiState?.zoomedPhotoIndex != null) {
        val zoomIdx = uiState.zoomedPhotoIndex
        val photoUrl = photos.getOrNull(zoomIdx)
        if (!photoUrl.isNullOrEmpty()) {
            ZoomableImageViewer(
                imageUrl = photoUrl,
                title = if (zoomIdx == 0) "Main Photo" else "Photo $zoomIdx",
                onDismiss = { onEvent(ProfileSetupEvent.OnZoomPhoto(null)) },
                onRemove = { onEvent(ProfileSetupEvent.OnRemovePhoto(zoomIdx)) },
                onMakeMainPhoto = if (zoomIdx > 0) {
                    { onEvent(ProfileSetupEvent.OnMakeMainPhoto(zoomIdx)) }
                } else null
            )
        }
    }

    Text(
        text = "Photos that feel like you",
        color = Color(0xFF2C1635),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
    Text(
        text = "Add up to eight photos. Long hold and drag to reorder; click to enlarge.",
        color = Color(0xFF6B7280),
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
        text = "Your gallery · $filledCount of 8",
        color = Color(0xFF2C1635),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    // 8 slots in a grid (2 columns x 4 rows)
    val rows = photos.chunked(2)
    rows.forEachIndexed { rowIndex, rowPhotos ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rowPhotos.forEachIndexed { colIndex, photo ->
                val absoluteIndex = rowIndex * 2 + colIndex
                val isDragging = draggingIndex == absoluteIndex

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(135.dp)
                        .onGloballyPositioned { coordinates ->
                            boxBoundsMap[absoluteIndex] = coordinates.boundsInRoot()
                        }
                        .background(Color.White, Shape_15)
                        .border(
                            width = if (absoluteIndex == 0) 2.dp else 1.dp,
                            color = if (absoluteIndex == 0) Color(0xFF4A154B) else Color(0xFFE5E7EB),
                            shape = Shape_15
                        )
                        .clip(Shape_15)
                        .clickable {
                            if (photo.isNullOrEmpty()) {
                                onEvent(ProfileSetupEvent.OnOpenImageOptions(absoluteIndex))
                            } else {
                                // Click to enlarge with zoomable feature (ZoomableImageViewer)
                                onEvent(ProfileSetupEvent.OnZoomPhoto(absoluteIndex))
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (!photo.isNullOrEmpty()) {
                        // Only the image reflection is draggable via long hold (detectDragGesturesAfterLongPress)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .pointerInput(absoluteIndex, photo) {
                                    detectDragGesturesAfterLongPress(
                                        onDragStart = {
                                            draggingIndex = absoluteIndex
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            dragOffset += dragAmount
                                        },
                                        onDragEnd = {
                                            draggingIndex?.let { from ->
                                                val dropRootX = boxBoundsMap[from]?.center?.x ?: 0f
                                                val dropRootY = boxBoundsMap[from]?.center?.y ?: 0f
                                                val currentX = dropRootX + dragOffset.x
                                                val currentY = dropRootY + dragOffset.y

                                                var targetIdx: Int? = null
                                                boxBoundsMap.forEach { (idx, rect) ->
                                                    if (rect.contains(androidx.compose.ui.geometry.Offset(currentX, currentY))) {
                                                        targetIdx = idx
                                                    }
                                                }

                                                if (targetIdx != null && targetIdx != from) {
                                                    onEvent(ProfileSetupEvent.OnSwapPhotos(from, targetIdx))
                                                }
                                            }
                                            draggingIndex = null
                                            dragOffset = androidx.compose.ui.geometry.Offset.Zero
                                        },
                                        onDragCancel = {
                                            draggingIndex = null
                                            dragOffset = androidx.compose.ui.geometry.Offset.Zero
                                        }
                                    )
                                }
                                .offset {
                                    if (isDragging) {
                                        IntOffset(dragOffset.x.roundToInt(), dragOffset.y.roundToInt())
                                    } else {
                                        IntOffset(0, 0)
                                    }
                                }
                        ) {
                            AsyncImageWithLoader(
                                image = photo,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(Shape_15),
                                contentScale = ContentScale.Crop
                            )
                        }

                        // Badge for main photo
                        if (absoluteIndex == 0) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(6.dp)
                                    .background(Color(0xFF4A154B), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Main",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_insert_photo_24),
                                contentDescription = null,
                                tint = Color(0xFF9CA3AF),
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Add photo",
                                color = Color(0xFF6B7280),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
            if (rowPhotos.size < 2) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(4.dp))

    // Album privacy section
    Text(
        text = "Album privacy",
        color = Color(0xFF2C1635),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    val privacyOptions = listOf("Verified members", "Everyone", "On request", "After mutual interest")
    @OptIn(ExperimentalLayoutApi::class)
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        privacyOptions.forEach { privacy ->
            ProfileOptionPill(
                text = privacy,
                isSelected = uiState?.albumPrivacy == privacy,
                onClick = { onEvent(ProfileSetupEvent.OnAlbumPrivacyChanged(privacy)) }
            )
        }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Info text below Album privacy explaining who can see photos
    Text(
        text = "Your photos are shared according to your privacy preference. Verified members and approved connections can view your gallery.",
        color = Color(0xFF6B7280),
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    // A gentle photo coach card
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, Shape_15)
            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "A gentle photo coach",
                color = Color(0xFF2C1635),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "A well-lit, recent photo with one visible face works best. No beauty or skin-tone scoring.",
                color = Color(0xFF6B7280),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}
