package com.app.feature_home.ui.screens

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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

@Composable
fun AccountSettingsScreen(
    backStack: NavBackStack<NavKey>? = null
) {
    var phoneNumber by remember { mutableStateOf("+91 98765 *****") }
    var email by remember { mutableStateOf("mandeep****@gmail.com") }
    var selectedCallType by remember { mutableStateOf("Voice & Video") }
    var whoCanCall by remember { mutableStateOf("Members you have interacted with") }
    var startTime by remember { mutableStateOf("07:00 PM") }
    var endTime by remember { mutableStateOf("10:00 PM") }
    var selectedDayOption by remember { mutableStateOf("Weekends") }

    var ageRange by remember { mutableStateOf(23f..29f) }
    var heightMin by remember { mutableFloatStateOf(5.0f) }
    var heightMax by remember { mutableFloatStateOf(5.75f) } // 5'9"

    var maritalStatus by remember { mutableStateOf("Never Married") }
    var religion by remember { mutableStateOf("Sikh, Hindu") }
    var community by remember { mutableStateOf("Open to all communities") }
    var minIncome by remember { mutableStateOf("₹10 Lakh & above") }
    var workingWith by remember { mutableStateOf("Private Company, MNC, Govt/PSU") }
    var motherTongue by remember { mutableStateOf("Punjabi, Hindi, English") }
    var countryLiving by remember { mutableStateOf("India, Canada, United Kingdom") }
    var cityLiving by remember { mutableStateOf("Any City") }

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
                        .clickable { backStack?.removeLastOrNull() },
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
                    text = "Account Settings",
                    color = Color(0xFF1F1318),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(36.dp))
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .animateContentSize()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Intro
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Privacy & Preferences",
                        color = Color(0xFF1F1318),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Control who sees your info, communication rules, and accounts.",
                        color = Color(0xFF726A7A),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }

                // 1. Contact Privacy & Call Preferences Card
                AccountCard(title = "Contact Privacy", subtitle = "Manage phone, email, and incoming direct call rules.") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AccountFieldWithBadge(label = "Phone Number", value = phoneNumber, badgeText = "Only on Mutual Match")
                        AccountFieldWithBadge(label = "Email Address", value = email, badgeText = "Keep Private")

                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Call Preferences", color = Color(0xFF1F1318), fontSize = 14.sp, fontWeight = FontWeight.Bold)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CallPreferenceChip(text = "✓ Voice Call", isSelected = true)
                            CallPreferenceChip(text = "✓ Video Call", isSelected = true)
                        }

                        ProfileDropdownField(label = "Who Can Call You", value = whoCanCall)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("Your Availability Time", color = Color(0xFF726A7A), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(Color(0xFFF9F5F6), Shape_15)
                                        .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                        .padding(12.dp)
                                ) {
                                    Text("Start: $startTime", color = Color(0xFF2B1920), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(Color(0xFFF9F5F6), Shape_15)
                                        .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                        .padding(12.dp)
                                ) {
                                    Text("End: $endTime", color = Color(0xFF2B1920), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("Available On", color = Color(0xFF726A7A), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Everyday", "Weekdays", "✓ Weekends").forEach { day ->
                                    val isSelected = day == selectedDayOption
                                    Box(
                                        modifier = Modifier
                                            .background(if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6), CircleShape)
                                            .border(1.dp, if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD), CircleShape)
                                            .clickable { selectedDayOption = day }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = day,
                                            color = if (isSelected) Color.White else Color(0xFF3B2830),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 2. Contact Visibility Permissions Card
                AccountCard(title = "Contact Visibility Permissions", subtitle = "Define requirements for prospects allowed to view your private contact card.") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Age Range Slider
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Allowed Age Range", color = Color(0xFF726A7A), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                Text("${ageRange.start.toInt()} - ${ageRange.endInclusive.toInt()} Years", color = Color(0xFF7C1D3E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                        }

                        // Height Range Slider
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Allowed Height Range", color = Color(0xFF726A7A), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                Text("5'0\" - 5'9\"", color = Color(0xFF7C1D3E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                        }

                        ProfileDropdownField(label = "Marital Status", value = maritalStatus)
                        ProfileDropdownField(label = "Religion", value = religion)
                        ProfileDropdownField(label = "Community", value = community)
                        ProfileDropdownField(label = "Minimum Annual Income", value = minIncome)
                        ProfileDropdownField(label = "Working With", value = workingWith)
                        ProfileDropdownField(label = "Mother Tongue", value = motherTongue)
                        ProfileDropdownField(label = "Country Living In", value = countryLiving)
                        ProfileDropdownField(label = "City Living In", value = cityLiving)

                        // Instant Protection Notice
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text("Instant Protection Active", color = Color(0xFF7C1D3E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("Profiles that fail any of these conditions cannot view your contact number, email, or initiate calls.", color = Color(0xFF726A7A), fontSize = 11.sp)
                            }
                        }
                    }
                }

                // 3. Automated Messages Card
                AccountCard(title = "Automated Messages", subtitle = "Personalize greeting notes sent with your invites, responses & updates.") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Invitation Greeting Note", color = Color(0xFF1F1318), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text("CURRENT DEFAULT MESSAGE", color = Color(0xFF8C6D79), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                Text("Hi, I reviewed your profile and felt our aspirations and values align well. Looking forward to taking the conversation forward.", color = Color(0xFF2B1920), fontSize = 12.sp)
                            }
                        }

                        Text("Message on Request Acceptance", color = Color(0xFF1F1318), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text("CURRENT DEFAULT MESSAGE", color = Color(0xFF8C6D79), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                Text("Thank you for reaching out! Glad to connect with you. Feel free to drop a message or suggest a good time to talk.", color = Color(0xFF2B1920), fontSize = 12.sp)
                            }
                        }
                    }
                }

                // 4. Account Visibility & Deactivation Card
                AccountCard(title = "Account Visibility & Deactivation", subtitle = "Temporarily take down off or permanently terminate your profile.") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Hide Profile Box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFF9F5F6), Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .padding(14.dp)
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateContentSize(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.animateContentSize(),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(modifier = Modifier.size(8.dp).background(Color(0xFF22C55E), CircleShape))
                                        Text("Hide Profile", color = Color(0xFF1F1318), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFFDCFCE7), Shape_15)
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("PROFILE CURRENTLY ACTIVE", color = Color(0xFF16A34A), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Text("While your profile is hidden, other members cannot discover you in search results, explore your biography, or reach out. Ongoing matches and chat rooms stay paused.", color = Color(0xFF726A7A), fontSize = 11.sp, lineHeight = 16.sp)

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateContentSize()
                                        .border(1.2.dp, Color(0xFF7C1D3E), CircleShape)
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Hide Profile Temporarily", color = Color(0xFF7C1D3E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        // Delete Profile Box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color(0xFFFFF1F2), Shape_15)
                                .border(1.dp, Color(0xFFFECDD3), Shape_15)
                                .padding(14.dp)
                        ) {
                            Column(
                                modifier = Modifier.animateContentSize(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("⚠️ Delete Profile Permanently", color = Color(0xFFE11D48), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Text("Terminating your account will erase your marital records, uploaded documents, mutual matches, chat transcripts, and forfeit any active premium subscription balances.", color = Color(0xFF726A7A), fontSize = 11.sp, lineHeight = 16.sp)
                                Text("▲ This action cannot be reversed or retrieved.", color = Color(0xFFE11D48), fontSize = 10.sp, fontWeight = FontWeight.Bold)

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateContentSize()
                                        .background(Color(0xFFE11D48), CircleShape)
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Delete Account Permanently", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        // Log Out
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .background(Color.White, Shape_15)
                                .border(1.dp, Color(0xFFE7D8DD), Shape_15)
                                .clickable {}
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("🚪 Log Out", color = Color(0xFFE11D48), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }

                        // Version info
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("App Version 3.6.1 (Build 5092)", color = Color(0xFF9CA3AF), fontSize = 10.sp)
                            Text("Terms of use | Privacy Policy | Help center", color = Color(0xFF7C1D3E), fontSize = 11.sp, fontWeight = FontWeight.Medium)
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
                            text = "Save Account Settings",
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
fun AccountCard(
    title: String,
    subtitle: String? = null,
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
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
            content()
        }
    }
}

@Composable
fun AccountFieldWithBadge(label: String, value: String, badgeText: String) {
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
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFF1F4), Shape_15)
                    .border(0.8.dp, Color(0xFFF3D5DD), Shape_15)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = badgeText,
                    color = Color(0xFF7C1D3E),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CallPreferenceChip(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .animateContentSize()
            .background(if (isSelected) Color(0xFF7C1D3E) else Color(0xFFF9F5F6), CircleShape)
            .border(1.dp, if (isSelected) Color(0xFF7C1D3E) else Color(0xFFE7D8DD), CircleShape)
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
