package com.app.feature_home.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PartnerPreferencesScreen(
    backStack: NavBackStack<NavKey>
) {
    // Filter states
    val matchTypes = remember { mutableStateListOf("Marriage", "Long term") }
    val maritalStatuses = remember { mutableStateListOf("Never Married") }
    val religions = remember { mutableStateListOf("Sikh", "Hindu") }
    val motherTongues = remember { mutableStateListOf("Punjabi") }
    var locationCountry by remember { mutableStateOf("India") }
    var locationState by remember { mutableStateOf("Delhi NCR, Punjab, Chandigarh") }
    var incomeRange by remember { mutableStateOf(15f..35f) }
    val educations = remember { mutableStateListOf("B.Tech / M.E.", "M.Tech / M.S.") }
    val workFields = remember { mutableStateListOf("Software / IT") }
    val dietPreferences = remember { mutableStateListOf("Vegetarian", "Non-Veg") }
    val drinkingHabits = remember { mutableStateListOf("Never") }
    var isNonSmokerOnly by remember { mutableStateOf(true) }
    var heightRange by remember { mutableStateOf(5.0f..5.75f) } // 5'0" to 5'9"
    var ageRange by remember { mutableStateOf(24f..29f) }
    val manglikStatuses = remember { mutableStateListOf("Non-Manglik") }
    var onlyPhotos by remember { mutableStateOf(true) }
    var govtVerifiedOnly by remember { mutableStateOf(false) }

    // Expandable sections state
    var isReligionExpanded by remember { mutableStateOf(true) }
    var isMotherTongueExpanded by remember { mutableStateOf(true) }

    BoxCommon(
        modifier = Modifier.fillMaxSize(),
        showBackgroundImage = false,
        isAppearanceLightStatusBars = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .background(Color(0xFFFAF6F7))
                .animateContentSize()
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFF2EAEB), CircleShape)
                        .border(1.dp, Color(0xFFE6D9DC), CircleShape)
                        .clickable { backStack.removeLastOrNull() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier.size(18.dp),
                        colorFilter = ColorFilter.tint(Color(0xFF2B1920))
                    )
                }

                Text(
                    text = "RESET",
                    color = Color(0xFF7C1D3E),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp,
                    modifier = Modifier.clickable {
                        matchTypes.clear()
                        maritalStatuses.clear()
                        religions.clear()
                        motherTongues.clear()
                        educations.clear()
                        workFields.clear()
                        dietPreferences.clear()
                        drinkingHabits.clear()
                        manglikStatuses.clear()
                        isNonSmokerOnly = false
                        onlyPhotos = false
                        govtVerifiedOnly = false
                    }
                )
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .animateContentSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Title Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "SEARCH PREFERENCES",
                        color = Color(0xFF7C1D3E),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Who are you hoping to meet?",
                        color = Color(0xFF1F1318),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Set basic criteria to match your background, lifestyle and preferences exactly.",
                        color = Color(0xFF726A7A),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }

                // Matches Found Banner Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_20)
                        .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                        .animateContentSize()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "MATCHES FOUND",
                                    color = Color(0xFF8C6D79),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                )
                                Text(
                                    text = "248 Profiles",
                                    color = Color(0xFF1F1318),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column(
                                modifier = Modifier.animateContentSize(),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "FILTER POWER",
                                    color = Color(0xFF8C6D79),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                )
                                Text(
                                    text = "Very Specific",
                                    color = Color(0xFF7C1D3E),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        LinearProgressIndicator(
                            progress = { 0.85f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(CircleShape),
                            color = Color(0xFF7C1D3E),
                            trackColor = Color(0xFFF3E8FF)
                        )
                    }
                }

                // 1. Type of match
                PreferenceCard(
                    title = "Type of match",
                    actionText = "ANY/ALL"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Marriage", "Long term", "Friendship first").forEach { item ->
                            val isSelected = matchTypes.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) matchTypes.remove(item) else matchTypes.add(item)
                                }
                            )
                        }
                    }
                }

                // 2. Marital status
                PreferenceCard(
                    title = "Marital status",
                    actionText = "ANY/ALL"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Never Married", "Divorced", "Awaiting Div...", "Widowed").forEach { item ->
                            val isSelected = maritalStatuses.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) maritalStatuses.remove(item) else maritalStatuses.add(item)
                                }
                            )
                        }
                    }
                }

                // 3. Religion & Beliefs
                PreferenceCard(
                    title = "Religion & Beliefs",
                    subtitle = "Choose spiritual community",
                    actionText = if (isReligionExpanded) "COLLAPSE" else "EXPAND",
                    onActionClick = { isReligionExpanded = !isReligionExpanded }
                ) {
                    AnimatedVisibility(
                        visible = isReligionExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Sikh", "Hindu", "Muslim", "Christian", "Jain", "Buddhist", "No Religion").forEach { item ->
                                val isSelected = religions.contains(item)
                                PreferenceChip(
                                    text = if (isSelected) "✓ $item" else item,
                                    isSelected = isSelected,
                                    onClick = {
                                        if (isSelected) religions.remove(item) else religions.add(item)
                                    }
                                )
                            }
                        }
                    }
                }

                // 4. Mother Tongue
                PreferenceCard(
                    title = "Mother Tongue",
                    actionText = if (isMotherTongueExpanded) "LESS" else "MORE",
                    onActionClick = { isMotherTongueExpanded = !isMotherTongueExpanded }
                ) {
                    AnimatedVisibility(
                        visible = isMotherTongueExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Punjabi", "Hindi", "English", "Bengal", "Gujarati", "Marathi", "Tamil", "Telugu").forEach { item ->
                                val isSelected = motherTongues.contains(item)
                                PreferenceChip(
                                    text = if (isSelected) "✓ $item" else item,
                                    isSelected = isSelected,
                                    onClick = {
                                        if (isSelected) motherTongues.remove(item) else motherTongues.add(item)
                                    }
                                )
                            }
                        }
                    }
                }

                // 5. Location & Living in
                PreferenceCard(
                    title = "Location & Living in",
                    actionText = "CHANGE"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DropdownSelectorBox(value = locationCountry)
                        DropdownSelectorBox(value = locationState)
                    }
                }

                // 6. Annual Income
                PreferenceCard(
                    title = "Annual Income",
                    actionText = "IN LPA"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "₹${incomeRange.start.toInt()} – ₹${incomeRange.endInclusive.toInt()} LPA",
                                color = Color(0xFF7C1D3E),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        RangeSlider(
                            value = incomeRange,
                            onValueChange = { incomeRange = it },
                            valueRange = 1f..100f,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color(0xFF7C1D3E),
                                inactiveTrackColor = Color(0xFFF3E8FF),
                                thumbColor = Color(0xFF7C1D3E)
                            )
                        )
                    }
                }

                // 7. Highest Education
                PreferenceCard(
                    title = "Highest Education",
                    actionText = "ANY/ALL"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("B.Tech / M.E.", "M.Tech / M.S.", "MBA", "MCA", "Doctorate", "Any Graduate").forEach { item ->
                            val isSelected = educations.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) educations.remove(item) else educations.add(item)
                                }
                            )
                        }
                    }
                }

                // 8. Working With / Field
                PreferenceCard(
                    title = "Working With / Field",
                    actionText = "ANY/ALL"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Software / IT", "Engineering", "Finance", "Healthcare", "Civil Services", "Business").forEach { item ->
                            val isSelected = workFields.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) workFields.remove(item) else workFields.add(item)
                                }
                            )
                        }
                    }
                }

                // 9. Diet Preference
                PreferenceCard(
                    title = "Diet Preference",
                    actionText = "RESET",
                    onActionClick = { dietPreferences.clear() }
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Vegetarian", "Non-Veg", "Eggetarian", "Jain").forEach { item ->
                            val isSelected = dietPreferences.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) dietPreferences.remove(item) else dietPreferences.add(item)
                                }
                            )
                        }
                    }
                }

                // 10. Habits & Lifestyle
                PreferenceCard(
                    title = "Habits & Lifestyle"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("Drinking habits", color = Color(0xFF726A7A), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Never", "Occasionally", "Regular").forEach { item ->
                                    val isSelected = drinkingHabits.contains(item)
                                    PreferenceChip(
                                        text = if (isSelected) "✓ $item" else item,
                                        isSelected = isSelected,
                                        onClick = {
                                            if (isSelected) drinkingHabits.remove(item) else drinkingHabits.add(item)
                                        }
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Strictly Non-Smoker only", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Switch(
                                checked = isNonSmokerOnly,
                                onCheckedChange = { isNonSmokerOnly = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7C1D3E)
                                )
                            )
                        }
                    }
                }

                // 11. Height Range
                PreferenceCard(
                    title = "Height Range",
                    actionText = "FT/IN"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "5'0\" – 5'9\"",
                                color = Color(0xFF7C1D3E),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "152 - 175 cm",
                                color = Color(0xFF726A7A),
                                fontSize = 12.sp
                            )
                        }
                        RangeSlider(
                            value = heightRange,
                            onValueChange = { heightRange = it },
                            valueRange = 4.0f..7.0f,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color(0xFF7C1D3E),
                                inactiveTrackColor = Color(0xFFF3E8FF),
                                thumbColor = Color(0xFF7C1D3E)
                            )
                        )
                    }
                }

                // 12. Age Range
                PreferenceCard(
                    title = "Age Range",
                    actionText = "YRS"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "${ageRange.start.toInt()} – ${ageRange.endInclusive.toInt()} years",
                            color = Color(0xFF7C1D3E),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        RangeSlider(
                            value = ageRange,
                            onValueChange = { ageRange = it },
                            valueRange = 18f..60f,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color(0xFF7C1D3E),
                                inactiveTrackColor = Color(0xFFF3E8FF),
                                thumbColor = Color(0xFF7C1D3E)
                            )
                        )
                    }
                }

                // 13. Manglik Status
                PreferenceCard(
                    title = "Manglik Status",
                    actionText = "ANY/ALL"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Non-Manglik", "Manglik", "Doesn't Matter").forEach { item ->
                            val isSelected = manglikStatuses.contains(item)
                            PreferenceChip(
                                text = if (isSelected) "✓ $item" else item,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) manglikStatuses.remove(item) else manglikStatuses.add(item)
                                }
                            )
                        }
                    }
                }

                // 14. Trust & Verification
                PreferenceCard(
                    title = "Trust & Verification"
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text("Only profiles with photo", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Filters out avatar & blurred accounts", color = Color(0xFF726A7A), fontSize = 11.sp)
                            }
                            Switch(
                                checked = onlyPhotos,
                                onCheckedChange = { onlyPhotos = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7C1D3E)
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text("Govt. ID verified profiles only", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Verified via Aadhaar / Passport badge", color = Color(0xFF726A7A), fontSize = 11.sp)
                            }
                            Switch(
                                checked = govtVerifiedOnly,
                                onCheckedChange = { govtVerifiedOnly = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7C1D3E)
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Bottom Sticky Apply Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .background(Color.White)
                    .border(1.dp, Color(0xFFEDE0E4))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .background(Color(0xFF7C1D3E), CircleShape)
                        .clickable { backStack.removeLastOrNull() }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.animateContentSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Apply Filters (248 Matches)",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PreferenceCard(
    title: String,
    subtitle: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(Color.White, Shape_20)
            .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.animateContentSize(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = title,
                        color = Color(0xFF1F1318),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    subtitle?.let {
                        Text(
                            text = it,
                            color = Color(0xFF726A7A),
                            fontSize = 11.sp
                        )
                    }
                }
                actionText?.let {
                    Text(
                        text = it,
                        color = Color(0xFF7C1D3E),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp,
                        modifier = Modifier.clickable(enabled = onActionClick != null) {
                            onActionClick?.invoke()
                        }
                    )
                }
            }
            content()
        }
    }
}

@Composable
fun PreferenceChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .animateContentSize()
            .background(
                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                CircleShape
            )
            .border(
                1.dp,
                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                CircleShape
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color(0xFF3B2830),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DropdownSelectorBox(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(Color(0xFFF9F5F6), Shape_15)
            .border(1.dp, Color(0xFFE7D8DD), Shape_15)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(value, color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
        Image(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            colorFilter = ColorFilter.tint(Color(0xFF5A424B))
        )
    }
}
