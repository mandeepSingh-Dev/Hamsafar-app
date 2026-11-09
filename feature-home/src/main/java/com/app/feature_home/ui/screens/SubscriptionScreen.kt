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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@Composable
fun SubscriptionScreen(
    backStack: NavBackStack<NavKey>? = null
) {
    var selectedPlanId by remember { mutableStateOf("3_months") }

    val primaryGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF2C1635), Color(0xFF521854), Color(0xFF2C1635))
    )

    BoxCommon(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        showBackgroundImage = false,
        isAppearanceLightStatusBars = false
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
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // Top Header Banner with Gradient Background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .background(primaryGradient)
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Top bar inside gradient
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
                                    .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
                                    .clickable { backStack?.removeLastOrNull() },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.back),
                                    contentDescription = "Back",
                                    modifier = Modifier.size(18.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }

                            Text(
                                text = "Hamsafar VIP",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Help",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.clickable { }
                            )
                        }

                        // Banner Icon & Title
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color(0xFFD97706), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("👑", fontSize = 24.sp)
                            }
                            Text(
                                text = "Upgrade to Premium",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Connect 5x faster with verified life partners",
                                color = Color(0xFFD8B4FE),
                                fontSize = 13.sp
                            )
                        }

                        // Feature Pills Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.White, Shape_15)
                                    .padding(12.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .background(Color(0xFFF3E8FF), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("📞", fontSize = 12.sp)
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                                        Text("Direct Contacts", color = Color(0xFF1F1318), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Text("View verified phones", color = Color(0xFF726A7A), fontSize = 10.sp)
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.White, Shape_15)
                                    .padding(12.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .background(Color(0xFFF3E8FF), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("💬", fontSize = 12.sp)
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                                        Text("Unlimited Chat", color = Color(0xFF1F1318), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Text("Send custom notes", color = Color(0xFF726A7A), fontSize = 10.sp)
                                    }
                                }
                            }
                        }
                    }
                }

                // Body Content with 16dp horizontal padding
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Select Membership Plan Title
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Select Membership Plan",
                            color = Color(0xFF1F1318),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Cancel or change your subscription at any time",
                            color = Color(0xFF726A7A),
                            fontSize = 12.sp
                        )
                    }

                    // Plan 1: 3 Months VIP
                    MembershipPlanCard(
                        id = "3_months",
                        selectedId = selectedPlanId,
                        title = "3 Months VIP",
                        badgeText = "⭐ MOST POPULAR",
                        badgeColor = Color(0xFFD97706),
                        saveText = "SAVE 42%",
                        price = "₹349",
                        originalPrice = "₹627",
                        subPrice = "Only ₹116 / month",
                        description = "Ideal duration to find and finalize mutual interests",
                        features = "✓ 50 Phone Numbers   ✓ Unlimited Chat   ✓ Standout Badge",
                        onSelect = { selectedPlanId = "3_months" }
                    )

                    // Plan 2: 1 Month Starter
                    MembershipPlanCard(
                        id = "1_month",
                        selectedId = selectedPlanId,
                        title = "1 Month Starter",
                        badgeText = null,
                        badgeColor = Color.Transparent,
                        saveText = null,
                        price = "₹199",
                        originalPrice = null,
                        subPrice = "billed monthly",
                        description = "Explore and test profile compatibility",
                        features = "• 15 Phone Numbers   • Unlimited Messages   • Profile Boost",
                        onSelect = { selectedPlanId = "1_month" }
                    )

                    // Plan 3: 1 Year Till You Marry
                    MembershipPlanCard(
                        id = "1_year",
                        selectedId = selectedPlanId,
                        title = "1 Year Till You Marry",
                        badgeText = "🔥 BEST SAVINGS (62%)",
                        badgeColor = Color(0xFF16A34A),
                        saveText = null,
                        price = "₹899",
                        originalPrice = "₹2,388",
                        subPrice = "Just ₹74 / month",
                        description = "Complete peace of mind until you get engaged",
                        features = "• 250 Contact Unlocks   • Relationship Advisor   • Top Search",
                        onSelect = { selectedPlanId = "1_year" }
                    )

                    // Why Upgrade Card
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
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Text(
                                text = "Why Upgrade on Hamsafar?",
                                color = Color(0xFF1F1318),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            WhyUpgradeItem(
                                title = "Direct Phone & WhatsApp Access",
                                subtitle = "Unlock phone numbers directly and connect with parent or match instantly."
                            )
                            WhyUpgradeItem(
                                title = "Priority Profile Visibility (3x Views)",
                                subtitle = "Appear at the top of daily matches in Delhi, Punjab, and abroad."
                            )
                            WhyUpgradeItem(
                                title = "Private & Discreet Mode",
                                subtitle = "Only verified prospects matching all filters can view your contact info."
                            )
                            WhyUpgradeItem(
                                title = "Automated Daily Horoscope & Match alerts",
                                subtitle = "Instant SMS and push delivery for high-compatibility matches."
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Bottom Sticky Payment Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .background(Color.White)
                    .border(1.dp, Color(0xFFEDE0E4))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
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
                        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = when(selectedPlanId) {
                                        "1_month" -> "₹199"
                                        "1_year" -> "₹899"
                                        else -> "₹349"
                                    },
                                    color = Color(0xFF1F1318),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = when(selectedPlanId) {
                                        "1_month" -> "/ Month"
                                        "1_year" -> "/ Year"
                                        else -> "/ 3 Mos"
                                    },
                                    color = Color(0xFF726A7A),
                                    fontSize = 12.sp
                                )
                            }
                            Text(
                                text = "✓ Includes instant discount",
                                color = Color(0xFF16A34A),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(Color(0xFF7C1D3E), CircleShape)
                                .clickable { backStack?.removeLastOrNull() }
                                .padding(horizontal = 24.dp, vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Continue to Pay",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(">", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Text(
                        text = "🔒 256-bit SSL Encrypted • UPI, Cards, NetBanking Supported",
                        color = Color(0xFF8C6D79),
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun MembershipPlanCard(
    id: String,
    selectedId: String,
    title: String,
    badgeText: String?,
    badgeColor: Color,
    saveText: String?,
    price: String,
    originalPrice: String?,
    subPrice: String,
    description: String,
    features: String,
    onSelect: () -> Unit
) {
    val isSelected = id == selectedId

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(Color.White, Shape_20)
            .border(
                width = if (isSelected) 2.dp else 1.2.dp,
                color = if (isSelected) Color(0xFF7C1D3E) else Color(0xFFEFE4E7),
                shape = Shape_20
            )
            .clickable { onSelect() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Radio button
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(if (isSelected) Color(0xFF7C1D3E) else Color.Transparent, CircleShape)
                            .border(2.dp, if (isSelected) Color(0xFF7C1D3E) else Color(0xFF9CA3AF), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Box(modifier = Modifier.size(8.dp).background(Color.White, CircleShape))
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = title,
                                color = Color(0xFF1F1318),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            saveText?.let {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFFFF1F4), Shape_15)
                                        .border(0.8.dp, Color(0xFFF3D5DD), Shape_15)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(text = it, color = Color(0xFF7C1D3E), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        Text(
                            text = subPrice,
                            color = Color(0xFF16A34A),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    badgeText?.let {
                        Box(
                            modifier = Modifier
                                .background(badgeColor, Shape_15)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(text = it, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        originalPrice?.let {
                            Text(
                                text = it,
                                color = Color(0xFF9CA3AF),
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                        Text(
                            text = price,
                            color = Color(0xFF1F1318),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Text(
                text = description,
                color = Color(0xFF726A7A),
                fontSize = 12.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .background(Color(0xFFF9F5F6), Shape_15)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = features,
                    color = Color(0xFF521854),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun WhyUpgradeItem(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFFF3E8FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("✓", color = Color(0xFF7C1D3E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = title,
                color = Color(0xFF2B1920),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = Color(0xFF726A7A),
                fontSize = 11.sp,
                lineHeight = 16.sp
            )
        }
    }
}
