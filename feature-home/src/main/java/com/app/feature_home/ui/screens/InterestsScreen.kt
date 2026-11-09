package com.app.feature_home.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_ui.R
import com.app.core_ui.local_providers.LocalBackStackNav
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

enum class InterestTab { RECEIVED, ACCEPTED, SENT }
enum class StatBottomSheetType { VISITORS, SHORTLISTED, CONTACT_VIEWERS, PHOTO_REQUESTS, DECLINED }

data class ProfileItem(
    val name: String,
    val age: Int,
    val subtitle: String,
    val location: String,
    val dateLabel: String,
    val statusLabel: String,
    val managedBy: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestsScreen(
    appBackStack: NavBackStack<NavKey> = LocalBackStackNav.current
) {
    var selectedTab by remember { mutableStateOf(InterestTab.RECEIVED) }
    var activeBottomSheet by remember { mutableStateOf<StatBottomSheetType?>(null) }

    val receivedProfiles = listOf(
        ProfileItem("Vikramaditya Bedi", 29, "5'11\" • AI Research Scientist • Earns ₹2...", "Punjabi, Sikh Khatri • Chandigarh, Punjab", "Accepted Interest on 28 October, 2026", "Online now", "Profile managed by Sibling", R.drawable.ananya),
        ProfileItem("Arjun Mehta", 28, "5'11\" • Product Designer • Earns ₹18-22...", "Hindi, Khatri • Bengaluru, Karnataka", "Received Interest on 02 November, 2026", "Online now", "Profile managed by Self", R.drawable.ananya)
    )

    val acceptedProfiles = listOf(
        ProfileItem("Rohan Verma", 27, "5'10\" • Software Engineer • Earns ₹20-25...", "Delhi NCR • Verified", "Accepted on 30 October, 2026", "Online now", "Profile managed by Self", R.drawable.ananya),
        ProfileItem("Vikramaditya Bedi", 29, "5'11\" • AI Research Scientist • Earns ₹2...", "Punjabi, Sikh Khatri • Chandigarh, Punjab", "Accepted Interest on 28 October, 2026", "Online now", "Profile managed by Sibling", R.drawable.ananya)
    )

    val sentProfiles = listOf(
        ProfileItem("Aarav Khanna", 28, "5'11\" • Founder & CEO • Earns ₹35-50...", "Punjabi, Khatri • New Delhi, Delhi NCR", "Sent Interest on 01 November, 2026", "Active 1h ago", "Profile managed by Self", R.drawable.ananya)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Stats Cards (Horizontal scrollable row)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                StatCard(count = "24", title = "Profile Visitors", icon = R.drawable.baseline_visibility_24) {
                    activeBottomSheet = StatBottomSheetType.VISITORS
                }
            }
            item {
                StatCard(count = "12", title = "Shortlisted Profiles", icon = R.drawable.baseline_star_border_24) {
                    activeBottomSheet = StatBottomSheetType.SHORTLISTED
                }
            }
            item {
                StatCard(count = "05", title = "Contact Viewers", icon = R.drawable.verifies_iconsymbol) {
                    activeBottomSheet = StatBottomSheetType.CONTACT_VIEWERS
                }
            }
            item {
                StatCard(count = "08", title = "Photo Requests", icon = R.drawable.baseline_camera_alt_24) {
                    activeBottomSheet = StatBottomSheetType.PHOTO_REQUESTS
                }
            }
            item {
                StatCard(count = "04", title = "Declined", icon = R.drawable.baseline_close_24) {
                    activeBottomSheet = StatBottomSheetType.DECLINED
                }
            }
        }

        // Segmented Tabs Row (Received, Accepted, Sent)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFF2C1635), CircleShape)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabPill(title = "Received (14)", isSelected = selectedTab == InterestTab.RECEIVED) {
                selectedTab = InterestTab.RECEIVED
            }
            TabPill(title = "Accepted (16)", isSelected = selectedTab == InterestTab.ACCEPTED) {
                selectedTab = InterestTab.ACCEPTED
            }
            TabPill(title = "Sent (42)", isSelected = selectedTab == InterestTab.SENT) {
                selectedTab = InterestTab.SENT
            }
        }

        // Animated Content & Carousel when switching tabs
        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                (fadeIn(animationSpec = tween(350)) + slideInHorizontally(
                    animationSpec = tween(350),
                    initialOffsetX = { if (targetState.ordinal > initialState.ordinal) it else -it }
                )) togetherWith
                (fadeOut(animationSpec = tween(350)) + slideOutHorizontally(
                    animationSpec = tween(350),
                    targetOffsetX = { if (targetState.ordinal > initialState.ordinal) -it else it }
                ))
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            label = "InterestTabAnimation"
        ) { tab ->
            val currentProfiles = when (tab) {
                InterestTab.RECEIVED -> receivedProfiles
                InterestTab.ACCEPTED -> acceptedProfiles
                InterestTab.SENT -> sentProfiles
            }

            val carouselState = rememberCarouselState(itemCount = { currentProfiles.size })

            HorizontalMultiBrowseCarousel(
                state = carouselState,
                preferredItemWidth = 320.dp,
                modifier = Modifier
                    .fillMaxSize(),
                itemSpacing = 16.dp,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) { index ->
                val profile = currentProfiles[index]
                InterestProfileCard(
                    profile = profile,
                    tab = tab,
                    onClick = { appBackStack.add(AppScreens.ProfileDetailsScreen) }
                )
            }
        }
    }

    // Bottom Sheet for Stats
    if (activeBottomSheet != null) {
        ModalBottomSheet(
            onDismissRequest = { activeBottomSheet = null }
        ) {
            StatDetailsBottomSheetContent(type = activeBottomSheet!!, onProfileClick = {
                activeBottomSheet = null
                appBackStack.add(AppScreens.ProfileDetailsScreen)
            })
        }
    }
}

@Composable
fun StatCard(count: String, title: String, icon: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .background(Color.White, Shape_15)
            .border(1.dp, Color(0xFFE5E7EB), Shape_15)
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3E8FF), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(count, color = Color(0xFF6B21A8), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF6B21A8))
                )
            }
            Text(title, color = Color(0xFF2C1635), fontSize = 12.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp)
        }
    }
}

@Composable
fun TabPill(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) Color.White else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = if (isSelected) Color(0xFF2C1635) else Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InterestProfileCard(profile: ProfileItem, tab: InterestTab, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(Shape_20)
        ) {
            Image(
                painter = painterResource(id = profile.imageRes),
                contentDescription = profile.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Transparent, Color.Black.copy(alpha = 0.85f)),
                            startY = 0f
                        )
                    )
            )

            // Top-left Date Badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.6f), Shape_15)
                    .border(1.dp, Color.White.copy(alpha = 0.15f), Shape_15)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column {
                    Text(
                        text = when (tab) {
                            InterestTab.RECEIVED -> "Received Interest on"
                            InterestTab.ACCEPTED -> "Accepted Interest on"
                            InterestTab.SENT -> "Sent Interest on"
                        },
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp
                    )
                    Text(profile.dateLabel.substringAfter("on "), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Top-right Verified Badge
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(Color(0xFFE6F4EA), CircleShape)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.verifies_iconsymbol),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
                Text("Verified", color = Color(0xFF0D9488), fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }

            // Bottom Profile Info Overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(modifier = Modifier
                        .size(6.dp)
                        .background(Color(0xFF22C55E), CircleShape))
                    Text(profile.statusLabel, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }

                Text(
                    text = "${profile.name}, ${profile.age}",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = profile.subtitle,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = profile.location,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
                Text(
                    text = profile.managedBy,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 11.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Action Buttons Row depending on tab
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (tab) {
                InterestTab.RECEIVED -> {
                    ActionBtn("Chat", Color(0xFF4A154B), Color.White, modifier = Modifier.weight(1f)) {}
                    ActionBtn("Contact", Color.White, Color(0xFF2C1635), modifier = Modifier.weight(1f), border = true) {}
                    ActionBtn("Cancel", Color(0xFFFEE2E2), Color(0xFFDC2626), modifier = Modifier.weight(1f)) {}
                }
                InterestTab.ACCEPTED -> {
                    ActionBtn("Accept Interest", Color(0xFF4A154B), Color.White, modifier = Modifier.weight(1.2f)) {}
                    ActionBtn("Maybe later", Color.White, Color(0xFF2C1635), modifier = Modifier.weight(1.1f), border = true) {}
                    ActionBtn("Decline", Color(0xFFFEE2E2), Color(0xFFDC2626), modifier = Modifier.weight(0.9f)) {}
                }
                InterestTab.SENT -> {
                    ActionBtn("Send Reminder", Color(0xFF4A154B), Color.White, modifier = Modifier.weight(1.2f)) {}
                    ActionBtn("Cancel", Color(0xFFFEE2E2), Color(0xFFDC2626), modifier = Modifier.weight(0.9f)) {}
                }
            }
        }
    }
}

@Composable
fun ActionBtn(
    text: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    border: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(Shape_20)
            .background(bgColor, Shape_20)
            .then(if (border) Modifier.border(1.dp, Color(0xFFE5E7EB), Shape_20) else Modifier)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun StatDetailsBottomSheetContent(type: StatBottomSheetType, onProfileClick: () -> Unit) {
    val (title, count) = when (type) {
        StatBottomSheetType.VISITORS -> "Profile Visitors" to "24"
        StatBottomSheetType.SHORTLISTED -> "Shortlisted Profiles" to "12"
        StatBottomSheetType.CONTACT_VIEWERS -> "Contact Viewers" to "05"
        StatBottomSheetType.PHOTO_REQUESTS -> "Photo Requests by You" to "08"
        StatBottomSheetType.DECLINED -> "Declined / Cancelled" to "04"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3E8FF), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(count, color = Color(0xFF6B21A8), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Text(title, color = Color(0xFF2C1635), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, Shape_15)
                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                .clickable { onProfileClick() }
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ananya),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text("Riya Sharma, 26", color = Color(0xFF2C1635), fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("Product Analyst • Gurugram", color = Color(0xFF6B7280), fontSize = 12.sp)
                        Text("14m ago", color = Color(0xFF9CA3AF), fontSize = 11.sp)
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3E8FF), Shape_20)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("View", color = Color(0xFF6B21A8), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
