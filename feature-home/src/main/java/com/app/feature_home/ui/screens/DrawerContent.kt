package com.app.feature_home.ui.screens

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
import com.app.core_ui.R
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@Composable
fun DrawerContent(
    onClose: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Top Profile Header & Close Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color(0xFFF3E8FF), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ananya),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "Arjun Mehta",
                        color = Color(0xFF2C1635),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFD97706), Shape_15)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text("👑 PREMIUM", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                        Text("ID: 454593", color = Color(0xFF6B7280), fontSize = 11.sp)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFF3F4F6), CircleShape)
                    .clickable { onClose() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "Close",
                    modifier = Modifier.size(14.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF374151))
                )
            }
        }

        // Edit Profile Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAF6FB), Shape_20)
                .border(1.dp, Color(0xFFE2D4E5), Shape_20)
                .clickable { onNavigate("EditProfile") }
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.editpencil_edit_02),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF521854))
                )
                Text(
                    text = "Edit Profile",
                    color = Color(0xFF521854),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        // Menu Items (Decreased spacing)
        DrawerMenuItem(icon = R.drawable.lovevector, title = "Partner Preferences", subtitle = "Tell us what you are looking for") { onNavigate("Preferences") }
        DrawerMenuItem(icon = R.drawable.baseline_visibility_24, title = "Account Settings", subtitle = "Privacy, contact rules & preferences") { onNavigate("AccountSettings") }
        DrawerMenuItem(icon = R.drawable.baseline_star_24, title = "Hamsafar VIP", subtitle = "Upgrade to Premium & Connect 5x faster", showNewBadge = true) { onNavigate("Subscription") }
        DrawerMenuItem(icon = R.drawable.baseline_visibility_24, title = "Search by Profile ID") { onNavigate("SearchID") }
        DrawerMenuItem(icon = R.drawable.baseline_star_24, title = "Spotlight", showNewBadge = true) { onNavigate("Spotlight") }
        DrawerMenuItem(icon = R.drawable.baseline_info_24, title = "Safety Center") { onNavigate("Safety") }
        DrawerMenuItem(icon = R.drawable.baseline_info_24, title = "Help & Support") { onNavigate("Support") }
        DrawerMenuItem(icon = R.drawable.baseline_star_border_24, title = "Rate the App") { onNavigate("Rate") }
        DrawerMenuItem(icon = R.drawable.outline_block_24, title = "Blocked Profiles") { onNavigate("Blocked") }
        DrawerMenuItem(icon = R.drawable.baseline_insert_photo_24, title = "Download your Biodata") { onNavigate("Biodata") }

        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFF1EDF3)))
        Spacer(modifier = Modifier.height(4.dp))

        // Log out (Right below divider with little space)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigate("Logout") }
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFFFF1F2), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_delete_24),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFC22D4A))
                )
            }
            Text(
                text = "Log out",
                color = Color(0xFFC22D4A),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Footer version info
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text("Hamsafar • Safeat Matrimony", color = Color(0xFF726A7A), fontSize = 11.sp)
            Text("v3.6.1", color = Color(0xFF9CA3AF), fontSize = 10.sp)
        }
    }
}

@Composable
fun DrawerMenuItem(
    icon: Int,
    title: String,
    subtitle: String? = null,
    showNewBadge: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFF4EEF6), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                colorFilter = ColorFilter.tint(Color(0xFF521854))
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    color = Color(0xFF2C1635),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                if (showNewBadge) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFEF3C7), Shape_15)
                            .border(0.8.dp, Color(0xFFF59E0B), Shape_15)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("NEW", color = Color(0xFFD97706), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            subtitle?.let {
                Text(
                    text = it,
                    color = Color(0xFF726A7A),
                    fontSize = 10.sp
                )
            }
        }
    }
}
