package com.app.feature_home.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
fun SearchScreen(
    backStack: NavBackStack<NavKey>
) {
    // Expansion states: Only Age & height and Background opened by default; rest collapsed
    var isQuickSearchExpanded by remember { mutableStateOf(false) }
    var isAgeHeightExpanded by remember { mutableStateOf(true) }
    var isBackgroundExpanded by remember { mutableStateOf(true) }
    var isLocationProfileExpanded by remember { mutableStateOf(false) }
    var isEducationCareerExpanded by remember { mutableStateOf(false) }
    var isReligionExpanded by remember { mutableStateOf(false) }
    var isLifestyleExpanded by remember { mutableStateOf(false) }

    // Quick Search state
    var selectedQuickTab by remember { mutableStateOf("Profile ID") }
    var searchQuery by remember { mutableStateOf("") }

    // Age & Height state
    var ageRange by remember { mutableStateOf(25f..32f) }
    var heightMin by remember { mutableFloatStateOf(5.16f) }
    var heightMax by remember { mutableFloatStateOf(5.83f) }

    // Background state
    val maritalStatuses = remember { mutableStateListOf("Never married", "Divorced") }
    val religions = remember { mutableStateListOf("Hindu", "Sikh") }
    val communities = remember { mutableStateListOf<String>() }
    var selectedMotherTongue by remember { mutableStateOf("Hindi") }

    // Location & Profile state
    var currentLocation by remember { mutableStateOf("New Delhi, India") }
    val profileManagedBy = remember { mutableStateListOf("Self", "Parents") }

    // Education & Career state
    val educations = remember { mutableStateListOf("B.E/B.Tech", "MBA / PGDM") }
    var occupation by remember { mutableStateOf("Software Professional") }
    var annualIncome by remember { mutableStateOf("₹ 10-25 Lakh per year") }

    // Religion & Caste state (Preferences)
    val prefReligions = remember { mutableStateListOf("Hindu", "Sikh") }
    var caste by remember { mutableStateOf("") }
    val manglikStatuses = remember { mutableStateListOf("Non-Manglik") }

    // Lifestyle state
    val dietaryHabits = remember { mutableStateListOf("Vegetarian") }
    val drinkingHabits = remember { mutableStateListOf("Never") }
    val smokingHabits = remember { mutableStateListOf("Never") }

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
        ) {
            // Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
                    text = "Reset all",
                    color = Color(0xFF7C1D3E),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        searchQuery = ""
                        ageRange = 18f..60f
                        maritalStatuses.clear()
                        religions.clear()
                        communities.clear()
                        profileManagedBy.clear()
                        educations.clear()
                        prefReligions.clear()
                        manglikStatuses.clear()
                        dietaryHabits.clear()
                        drinkingHabits.clear()
                        smokingHabits.clear()
                        caste = ""
                    }
                )
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title Header
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "PARTNER SEARCH",
                        color = Color(0xFF7C1D3E),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Tell us what you are looking for",
                        color = Color(0xFF1F1318),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Search by profile ID, profession or community, or set your preferences below.",
                        color = Color(0xFF726A7A),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }

                // 1. Quick Search Card (Collapsed by default)
                CollapsibleCard(
                    title = "QUICK SEARCH",
                    isExpanded = isQuickSearchExpanded,
                    onToggle = { isQuickSearchExpanded = !isQuickSearchExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        // Segmented control pills
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF6F0F2), CircleShape)
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            listOf("Profile ID", "Profession", "Community").forEach { tab ->
                                val isSelected = tab == selectedQuickTab
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(CircleShape)
                                        .background(if (isSelected) Color.White else Color.Transparent)
                                        .clickable { selectedQuickTab = tab }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = tab,
                                        color = if (isSelected) Color(0xFF2B1920) else Color(0xFF8C6D79),
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }
                        }

                        // Search Input Box
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_visibility_24),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                colorFilter = ColorFilter.tint(Color(0xFF8C6D79))
                            )

                            Box(modifier = Modifier.weight(1f)) {
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = "Enter $selectedQuickTab",
                                        color = Color(0xFF9CA3AF),
                                        fontSize = 13.sp
                                    )
                                }
                                BasicTextField(
                                    value = searchQuery,
                                    onValueChange = { searchQuery = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color(0xFF7C1D3E), CircleShape)
                                    .clickable {},
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.right_arrow),
                                    contentDescription = "Submit",
                                    modifier = Modifier.size(16.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }
                    }
                }

                // 2. Age & height Card (OPEN by default)
                CollapsibleCard(
                    title = "Age & height",
                    isExpanded = isAgeHeightExpanded,
                    onToggle = { isAgeHeightExpanded = !isAgeHeightExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        // Age range
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Age range", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFFFF1F4), Shape_15)
                                        .border(0.8.dp, Color(0xFFF3D5DD), Shape_15)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "${ageRange.start.toInt()} – ${ageRange.endInclusive.toInt()} yrs",
                                        color = Color(0xFF7C1D3E),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("18", color = Color(0xFF9CA3AF), fontSize = 11.sp)
                                Text("60", color = Color(0xFF9CA3AF), fontSize = 11.sp)
                            }
                        }

                        // Height range
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Height range", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFFFF1F4), Shape_15)
                                        .border(0.8.dp, Color(0xFFF3D5DD), Shape_15)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "5' 2\" – 5' 10\"",
                                        color = Color(0xFF7C1D3E),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            RangeSlider(
                                value = heightMin..heightMax,
                                onValueChange = {
                                    heightMin = it.start
                                    heightMax = it.endInclusive
                                },
                                valueRange = 4.0f..7.0f,
                                colors = SliderDefaults.colors(
                                    activeTrackColor = Color(0xFF7C1D3E),
                                    inactiveTrackColor = Color(0xFFF3E8FF),
                                    thumbColor = Color(0xFF7C1D3E)
                                )
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("4' 0\"", color = Color(0xFF9CA3AF), fontSize = 11.sp)
                                Text("7' 0\"", color = Color(0xFF9CA3AF), fontSize = 11.sp)
                            }
                        }
                    }
                }

                // 3. Background Card (OPEN by default)
                CollapsibleCard(
                    title = "Background",
                    isExpanded = isBackgroundExpanded,
                    onToggle = { isBackgroundExpanded = !isBackgroundExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        // Marital status
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Marital status", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Multi-select", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Never married", "Divorced", "Widowed", "Awaiting divorce").forEach { status ->
                                    val isSelected = maritalStatuses.contains(status)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) maritalStatuses.remove(status) else maritalStatuses.add(status)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = status,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        // Religion
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Religion", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Multi-select", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Hindu", "Sikh", "Muslim", "Christian", "Jain").forEach { religion ->
                                    val isSelected = religions.contains(religion)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) religions.remove(religion) else religions.add(religion)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = religion,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        // Community Selector
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Community", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Multi-select", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Select communities", color = Color(0xFF9A848C), fontSize = 13.sp)
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }

                        // Mother tongue Selector
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Mother tongue", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(selectedMotherTongue, color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }
                    }
                }

                // 4. Location & profile Card (Collapsed by default)
                CollapsibleCard(
                    title = "Location & profile",
                    isExpanded = isLocationProfileExpanded,
                    onToggle = { isLocationProfileExpanded = !isLocationProfileExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Current location", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.locationcontainer),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = ColorFilter.tint(Color(0xFF7C1D3E))
                                    )
                                    Text(currentLocation, color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Profile managed by", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Self", "Parents", "Sibling", "Friend").forEach { item ->
                                    val isSelected = profileManagedBy.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) profileManagedBy.remove(item) else profileManagedBy.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 5. Education & career Card (Collapsed by default)
                CollapsibleCard(
                    title = "Education & career",
                    isExpanded = isEducationCareerExpanded,
                    onToggle = { isEducationCareerExpanded = !isEducationCareerExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Education", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Opens sheet", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                FlowRow(
                                    modifier = Modifier.weight(1f),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    educations.forEach { edu ->
                                        Box(
                                            modifier = Modifier
                                                .background(Color(0xFF7C1D3E), CircleShape)
                                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text(edu, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                        }
                                    }
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Occupation", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Searchable list", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.baseline_visibility_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = ColorFilter.tint(Color(0xFF7C1D3E))
                                    )
                                    Text(occupation, color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Annual income", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Range slider", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(annualIncome, color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }
                    }
                }

                // 6. Religion Card (Collapsed by default)
                CollapsibleCard(
                    title = "Religion & caste preferences",
                    isExpanded = isReligionExpanded,
                    onToggle = { isReligionExpanded = !isReligionExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Religion", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Multi-select", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Hindu", "Sikh", "Muslim", "Christian", "Jain").forEach { item ->
                                    val isSelected = prefReligions.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) prefReligions.remove(item) else prefReligions.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Caste / sub-caste", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF9F5F6), Shape_15)
                                    .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(if (caste.isEmpty()) "Select caste or sub-caste" else caste, color = if (caste.isEmpty()) Color(0xFF9A848C) else Color(0xFF2B1920), fontSize = 13.sp)
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFF5A424B))
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Manglik status", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Multi-select", color = Color(0xFF9A848C), fontSize = 11.sp)
                            }
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Non-Manglik", "Manglik", "Anshik", "Don't know").forEach { item ->
                                    val isSelected = manglikStatuses.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) manglikStatuses.remove(item) else manglikStatuses.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 7. Lifestyle Card (Collapsed by default)
                CollapsibleCard(
                    title = "Lifestyle",
                    isExpanded = isLifestyleExpanded,
                    onToggle = { isLifestyleExpanded = !isLifestyleExpanded }
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Dietary habits", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Vegetarian", "Non-vegetarian", "Eggetarian", "Vegan").forEach { item ->
                                    val isSelected = dietaryHabits.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) dietaryHabits.remove(item) else dietaryHabits.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Drinking habits", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Never", "Occasionally", "Regularly").forEach { item ->
                                    val isSelected = drinkingHabits.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) drinkingHabits.remove(item) else drinkingHabits.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Smoking habits", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Never", "Occasionally", "Regularly").forEach { item ->
                                    val isSelected = smokingHabits.contains(item)
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6),
                                                CircleShape
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD),
                                                CircleShape
                                            )
                                            .clickable {
                                                if (isSelected) smokingHabits.remove(item) else smokingHabits.add(item)
                                            }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = item,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Bottom Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(1.dp, Color(0xFFEDE0E4))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFF5EEF0), CircleShape)
                        .border(1.dp, Color(0xFFE5D5DA), CircleShape)
                        .clickable {
                            searchQuery = ""
                            ageRange = 18f..60f
                            maritalStatuses.clear()
                            religions.clear()
                            communities.clear()
                            profileManagedBy.clear()
                            educations.clear()
                            prefReligions.clear()
                            manglikStatuses.clear()
                            dietaryHabits.clear()
                            drinkingHabits.clear()
                            smokingHabits.clear()
                            caste = ""
                        }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Reset", color = Color(0xFF2B1920), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .background(Color(0xFF7C1D3E), CircleShape)
                        .clickable { backStack.removeLastOrNull() }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text("Show matches", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CollapsibleCard(
    title: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, Shape_20)
            .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
            .clip(Shape_20)
            .clickable { onToggle() }
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = Color(0xFF1F1318),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(if (isExpanded) 180f else 0f),
                    colorFilter = ColorFilter.tint(Color(0xFF7C1D3E))
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = false) {}
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        content()
                    }
                }
            }
        }
    }
}
