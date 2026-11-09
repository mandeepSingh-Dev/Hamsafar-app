package com.app.feature_home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_ui.R
import com.app.core_ui.local_providers.LocalBackStackNav
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@Composable
fun ProfileDetailsScreen(
    backStack: NavBackStack<NavKey> = LocalBackStackNav.current
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, CircleShape)
                            .border(1.dp, Color(0xFFE5E7EB), CircleShape)
                            .clickable { backStack.removeLastOrNull() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "Simran Kaur",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C1635)
                        )
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFE6F4EA), CircleShape)
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.verifies_iconsymbol),
                                contentDescription = null,
                                modifier = Modifier.size(10.dp)
                            )
                            Text("Verified", color = Color(0xFF0D9488), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape)
                        .border(1.dp, Color(0xFFE5E7EB), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_info_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Scrollable Content with Motion / Scroll Animations
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 90.dp)
            ) {
                // 1. Photo Section (Main Content)
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                            .clip(Shape_20)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ananya),
                            contentDescription = "Simran Kaur",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Gradient Overlay
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                        startY = 100f
                                    )
                                )
                        )

                        // Top-left Badges
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(modifier = Modifier
                                    .size(6.dp)
                                    .background(Color(0xFF22C55E), CircleShape))
                                Text("Online now", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }

                            Row(
                                modifier = Modifier
                                    .background(Color(0xFF4A154B).copy(alpha = 0.85f), CircleShape)
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_star_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFFFEF3C7))
                                )
                                Text("Check compatibility", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                        }

                        // Top-right Icons
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.Black.copy(alpha = 0.5f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                                    contentDescription = "Share",
                                    modifier = Modifier.size(18.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.Black.copy(alpha = 0.5f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.outline_more_vert_24),
                                    contentDescription = "More",
                                    modifier = Modifier.size(18.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }

                        // Left/Right Carousel Arrows
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 12.dp)
                                .size(36.dp)
                                .background(Color.Black.copy(alpha = 0.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("‹", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 12.dp)
                                .size(36.dp)
                                .background(Color.Black.copy(alpha = 0.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("›", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }

                        // Bottom Overlay Text & Dots
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Simran Kaur",
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.verifies_iconsymbol),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Text(
                                text = "27 yrs, 5'4\" height • Senior Product Designer",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Punjabi, Ramgarhia • Mohali, Punjab",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                repeat(4) { i ->
                                    Box(
                                        modifier = Modifier
                                            .size(if (i == 0) 16.dp else 6.dp, 6.dp)
                                            .background(if (i == 0) Color.White else Color.White.copy(alpha = 0.5f), CircleShape)
                                    )
                                }
                            }
                        }
                    }
                }

                // 2. Basic Details Card
                item {
                    DetailCard(title = "Basic Details", icon = R.drawable.age_iconcontainer) {
                        DetailRow("Name & ID", "Simran Kaur • HMSF-849201")
                        DetailRow("Age · Date of birth", "27 yrs · 14 Oct 1999")
                        DetailRow("Lives in", "Mohali, Punjab, India")
                        DetailRow("Height", "5'4\" (162 cm)")
                        DetailRow("Religion · Mother tongue", "Sikh · Punjabi")
                        DetailRow("Earns", "INR 4 - 5 Lakh per annum")
                        DetailRow("Residential status", "Citizen / Permanent Resident")
                        DetailRow("Diet", "Non-Veg")
                        DetailRow("Gotra", "Saggu / Ramgarhia")
                        DetailRow("Profile Managed by", "Self & Parents")
                    }
                }

                // 3. Contact Details Card (Locked)
                item {
                    DetailCard(title = "Contact Details", icon = R.drawable.baseline_local_phone_24, locked = true) {
                        DetailRow("Contact No.", "+91 98765 ***** 🔒")
                        DetailRow("Email ID", "simran.******@gmail.com 🔒")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "To unlock Contact No. and Email ID, upgrade to a member plan.",
                            color = Color(0xFF6B7280),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF581C87), Shape_20)
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("👑", fontSize = 16.sp)
                                Text(
                                    text = "Purchase a Cheap Plan",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // 4. Career Details Card
                item {
                    DetailCard(title = "Career Details", icon = R.drawable.baseline_info_24) {
                        DetailRow("Profession", "Senior Product Designer")
                        DetailRow("Employer name", "Infosys Ltd.")
                        DetailRow("Annual Income", "INR 4 - 5 Lakh per annum")
                    }
                }

                // 5. Education Details Card
                item {
                    DetailCard(title = "Education Details", icon = R.drawable.baseline_info_24) {
                        DetailRow("Highest Qualification", "B.Tech / B.E.")
                        DetailRow("Education field", "Computer Science & Engineering")
                    }
                }

                // 6. Family Details Card
                item {
                    DetailCard(title = "Family Details", icon = R.drawable.age_iconcontainer) {
                        DetailRow("Father", "Businessman")
                        DetailRow("Mother", "Home Maker")
                        DetailRow("Brothers and Sisters", "1 Brother (0 Married) and 1 Sister (0 Married)")
                        DetailRow("Stays in", "Mohali")
                        DetailRow("Native place", "Chandigarh")
                    }
                }

                // 7. Match Preferences Card
                item {
                    DetailCard(title = "Match Preferences", icon = R.drawable.baseline_star_24, badgeText = "10/11 Matched") {
                        Text(
                            text = "Her partner preferences compared with your profile details",
                            color = Color(0xFF6B7280),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        PreferenceRow("Age", "20 to 29", true)
                        PreferenceRow("Height", "5'4\" to 5'11\"", true)
                        PreferenceRow("Marital status", "Never married", true)
                        PreferenceRow("Religion", "Hindu, Sikh", true)
                        PreferenceRow("Caste", "Ramgarhia, Jat, Khatri, Arora", true)
                        PreferenceRow("Mother tongue", "Punjabi, Hindi", true)
                        PreferenceRow("Country living in", "India, Canada, America, Australia", true)
                        PreferenceRow("City living in", "Delhi NCR, Punjab, Chandigarh", true)
                        PreferenceRow("Diet", "Non-Veg", true)
                        PreferenceRow("Annual Income", "INR 1 lakh to 15 lakh", true)
                        PreferenceRow("Education", "Diploma, B.Tech, Masters", false)
                    }
                }

                // 8. Common Between You and Her Card
                item {
                    DetailCard(title = "Common Between You and Her", icon = R.drawable.baseline_star_24) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            CommonItem("She too enjoys Non-veg")
                            CommonItem("You both live in Delhi-NCR")
                            CommonItem("She too is from the Punjabi community")
                            CommonItem("You both like working out, Pets and Travelling")
                        }
                    }
                }
            }
        }

        // Bottom Fixed Action Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Super Connect Button
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFFEF3C7), Shape_20)
                        .border(1.dp, Color(0xFFFDE68A), Shape_20)
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "Super Connect",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color(0xFFD97706))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Super Connect",
                        color = Color(0xFF92400E),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Connect Button
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF4A154B), Shape_20)
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.verifies_iconsymbol),
                        contentDescription = "Connect",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Connect",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Shortlist Button
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, Shape_20)
                        .border(1.dp, Color(0xFFE5E7EB), Shape_20)
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_border_24),
                        contentDescription = "Shortlist",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color(0xFF374151))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Shortlist",
                        color = Color(0xFF374151),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun DetailCard(
    title: String,
    icon: Int,
    locked: Boolean = false,
    badgeText: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, Shape_15)
            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF3E8FF), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(Color(0xFF6B21A8))
                        )
                    }
                    Text(
                        text = title,
                        color = Color(0xFF2C1635),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (locked) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFEF3C7), CircleShape)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("🔒 Locked", color = Color(0xFFB45309), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                } else if (badgeText != null) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE6F4EA), CircleShape)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(badgeText, color = Color(0xFF0D9488), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            content()
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, color = Color(0xFF6B7280), fontSize = 13.sp)
            Text(text = value, color = Color(0xFF111827), fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF3F4F6))
        )
    }
}

@Composable
fun PreferenceRow(label: String, value: String, isMatched: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(text = label, color = Color(0xFF6B7280), fontSize = 12.sp)
                Text(text = value, color = Color(0xFF111827), fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(if (isMatched) Color(0xFFE6F4EA) else Color(0xFFFEE2E2), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isMatched) "✓" else "✕",
                    color = if (isMatched) Color(0xFF0D9488) else Color(0xFFDC2626),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF3F4F6))
        )
    }
}

@Composable
fun CommonItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9FAFB), Shape_15)
            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Color(0xFFFEF3C7), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("🍽️", fontSize = 12.sp)
        }
        Text(text = text, color = Color(0xFF2C1635), fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
