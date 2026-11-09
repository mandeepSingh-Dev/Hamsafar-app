package com.app.feature_home.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20
import kotlinx.coroutines.delay

@Composable
fun ProfileSettingsScreen(
    backStack: NavBackStack<NavKey>? = null
) {
    var fullName by remember { mutableStateOf("Mandeep Singh Duggal") }
    var privacyStatus by remember { mutableStateOf("Visible to everyone off") }
    var gender by remember { mutableStateOf("Male") }
    var dob by remember { mutableStateOf("14 August 1990 (Age: 34)") }
    var maritalStatus by remember { mutableStateOf("Never Married") }
    var height by remember { mutableStateOf("5'10\" (177 cm)") }
    var bio by remember { mutableStateOf("Senior Software Engineer living in Delhi NCR. Loving walking, high performance mobile apps, machine learning, food, and spending quality time with family and friends.") }

    var religion by remember { mutableStateOf("Sikh") }
    var motherTongue by remember { mutableStateOf("Punjabi") }
    var community by remember { mutableStateOf("Ramgharia / Lok / Ahluwalia") }
    var subCommunity by remember { mutableStateOf("Arora") }
    var manglikStatus by remember { mutableStateOf("Non-Manglik") }

    var country by remember { mutableStateOf("India") }
    var state by remember { mutableStateOf("Delhi") }
    var city by remember { mutableStateOf("New Delhi") }
    var residencyStatus by remember { mutableStateOf("Citizen") }
    var citizenship by remember { mutableStateOf("Trading") }
    var growUpIn by remember { mutableStateOf("Delhi & Punjab") }

    var education by remember { mutableStateOf("B.Tech - Computer Science") }
    var college by remember { mutableStateOf("Guru Gobind Singh Indraprastha University") }
    var workingWith by remember { mutableStateOf("Private Sector / Smartphone OEM") }
    var workingAs by remember { mutableStateOf("Device Mobile Application Developer") }
    var annualIncome by remember { mutableStateOf("₹20 Lakh - ₹30 Lakh") }

    // Staggered visibility states for smooth lag-free opening
    var isHeaderVisible by remember { mutableStateOf(false) }
    var isPreferencesBannerVisible by remember { mutableStateOf(false) }
    var isBasicInfoVisible by remember { mutableStateOf(false) }
    var isPersonalVisible by remember { mutableStateOf(false) }
    var isBioVisible by remember { mutableStateOf(false) }
    var isReligionVisible by remember { mutableStateOf(false) }
    var isLocationVisible by remember { mutableStateOf(false) }
    var isEducationVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isHeaderVisible = true
        delay(30L)
        isPreferencesBannerVisible = true
        delay(30L)
        isBasicInfoVisible = true
        delay(30L)
        isPersonalVisible = true
        delay(30L)
        isBioVisible = true
        delay(30L)
        isReligionVisible = true
        delay(30L)
        isLocationVisible = true
        delay(30L)
        isEducationVisible = true
    }

    BoxCommon(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        showBackgroundImage = false,
        isAppearanceLightStatusBars = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAF6F7))
                .animateContentSize()
        ) {
            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .animateContentSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Profile Avatar & ID Header (Staged)
                AnimatedVisibility(
                    visible = isHeaderVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color(0xFFE2D4E5), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ananya),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(76.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color(0xFF7C1D3E), CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .align(Alignment.BottomEnd),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.animateContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFD97706), Shape_15)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("👑 77505305", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Text(
                            text = "Edit Photo Gallery",
                            color = Color(0xFF7C1D3E),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { }
                        )
                    }
                }

                // Partner Preferences Banner (Staged)
                AnimatedVisibility(
                    visible = isPreferencesBannerVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .background(Color(0xFF2C1635), Shape_20)
                            .padding(16.dp)
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
                                Text(
                                    text = "Partner Preferences",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Set criteria for ideal matches & search",
                                    color = Color(0xFFD8B4FE),
                                    fontSize = 11.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF7C1D3E), CircleShape)
                                    .clickable { backStack?.add(AppScreens.PartnerPreferencesScreen) }
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("EDIT", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Basic Info Section (Staged)
                AnimatedVisibility(
                    visible = isBasicInfoVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "FULL NAME & PRIVACY") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ProfileEditableField(label = "Full Name", value = fullName)
                            ProfileDropdownField(label = "Profile Privacy", value = privacyStatus)
                        }
                    }
                }

                // Personal Details Section (Staged)
                AnimatedVisibility(
                    visible = isPersonalVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "Personal Details", action = "Edit") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ProfileDropdownField(label = "Gender", value = gender)
                            ProfileDropdownField(label = "Date of Birth", value = dob)
                            ProfileDropdownField(label = "Marital Status", value = maritalStatus)
                            ProfileDropdownField(label = "Height", value = height)
                        }
                    }
                }

                // About Me (Bio) Section (Staged)
                AnimatedVisibility(
                    visible = isBioVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "About Me (Bio)", action = "View more") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = bio,
                                color = Color(0xFF2B1920),
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                // Religious & Social Background (Staged)
                AnimatedVisibility(
                    visible = isReligionVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "Religious & Social Background") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ProfileDropdownField(label = "Religion", value = religion)
                            ProfileDropdownField(label = "Mother Tongue", value = motherTongue)
                            ProfileDropdownField(label = "Community", value = community)
                            ProfileDropdownField(label = "Sub-Community / Formats", value = subCommunity)
                            ProfileDropdownField(label = "Manglik Status", value = manglikStatus)
                        }
                    }
                }

                // Location & Living In (Staged)
                AnimatedVisibility(
                    visible = isLocationVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "Location & Living In") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ProfileDropdownField(label = "Country Living In", value = country)
                            ProfileDropdownField(label = "State Living In", value = state)
                            ProfileDropdownField(label = "City Living In", value = city)
                            ProfileDropdownField(label = "Residency Status", value = residencyStatus)
                            ProfileDropdownField(label = "Citizen / Visa Status", value = citizenship)
                            ProfileDropdownField(label = "Grew Up In", value = growUpIn)
                        }
                    }
                }

                // Education & Career (Staged)
                AnimatedVisibility(
                    visible = isEducationVisible,
                    enter = fadeIn(animationSpec = tween(250)) + expandVertically(animationSpec = tween(250))
                ) {
                    ProfileSectionCard(title = "Education & Career") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ProfileDropdownField(label = "Highest Qualification", value = education)
                            ProfileDropdownField(label = "College / Univ.", value = college)
                            ProfileDropdownField(label = "Working With", value = workingWith)
                            ProfileDropdownField(label = "Working As", value = workingAs)
                            ProfileDropdownField(label = "Annual Income Range", value = annualIncome)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Bottom Sticky Save Button
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
                        .clickable { backStack?.removeLastOrNull() }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.animateContentSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Save Profile",
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
fun ProfileSectionCard(
    title: String,
    action: String? = null,
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
                Text(
                    text = title,
                    color = Color(0xFF1F1318),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                action?.let {
                    Text(
                        text = it,
                        color = Color(0xFF7C1D3E),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { }
                    )
                }
            }
            content()
        }
    }
}

@Composable
fun ProfileDropdownField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFF726A7A),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
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
            Text(
                text = value,
                color = Color(0xFF2B1920),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF5A424B))
            )
        }
    }
}

@Composable
fun ProfileEditableField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFF726A7A),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
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
            Text(
                text = value,
                color = Color(0xFF2B1920),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF5A424B))
            )
        }
    }
}
