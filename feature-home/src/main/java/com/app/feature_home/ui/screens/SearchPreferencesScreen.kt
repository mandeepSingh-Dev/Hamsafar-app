package com.app.feature_home.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SearchPreferencesScreen(
    backStack: NavBackStack<NavKey>
) {
    var currentLocation by remember { mutableStateOf("New Delhi, India") }
    val profileManagedBy = remember { mutableStateListOf("Self", "Parents") }
    val educations = remember { mutableStateListOf("B.E/B.Tech", "MBA / PGDM") }
    var occupation by remember { mutableStateOf("Software Professional") }
    var annualIncome by remember { mutableStateOf("₹ 10-25 Lakh per year") }
    val religions = remember { mutableStateListOf("Hindu", "Sikh") }
    var caste by remember { mutableStateOf("") }
    val manglikStatuses = remember { mutableStateListOf("Non-Manglik") }
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
                        profileManagedBy.clear()
                        educations.clear()
                        religions.clear()
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
                        text = "REFINE YOUR SEARCH",
                        color = Color(0xFF7C1D3E),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Hamsafar preferences",
                        color = Color(0xFF1F1318),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // 1. Location & profile Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_20)
                        .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = "Location & profile",
                            color = Color(0xFF1F1318),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Current location
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

                        // Profile managed by
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

                // 2. Education & career Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_20)
                        .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = "Education & career",
                            color = Color(0xFF1F1318),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Education
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
                                    if (educations.isEmpty()) {
                                        Text("Select education", color = Color(0xFF9A848C), fontSize = 13.sp)
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

                        // Occupation
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

                        // Annual income
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Annual income", color = Color(0xFF2B1920), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("Range slider in sheet", color = Color(0xFF9A848C), fontSize = 11.sp)
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

                // 3. Religion Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_20)
                        .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = "Religion",
                            color = Color(0xFF1F1318),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

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
                                listOf("Hindu", "Sikh", "Muslim", "Christian", "Jain").forEach { item ->
                                    val isSelected = religions.contains(item)
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
                                                if (isSelected) religions.remove(item) else religions.add(item)
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

                        // Caste / sub-caste
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

                        // Manglik status
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

                // 4. Lifestyle Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, Shape_20)
                        .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = "Lifestyle",
                            color = Color(0xFF1F1318),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Dietary habits
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

                        // Drinking habits
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

                        // Smoking habits
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
                            profileManagedBy.clear()
                            educations.clear()
                            religions.clear()
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
