package com.app.feature_home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.AppScreens
import com.app.core_ui.R
import com.app.core_ui.components.GradientButton
import com.zodiaq.ui.theme.Shape_15

@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "04 · THOUGHTFUL MATCHING",
                color = Color(0xFF6B7280),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Good morning, Aditi",
                color = Color(0xFF2C1635),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            )
            Text(
                text = "A few thoughtful introductions. At your pace.",
                color = Color(0xFF6B7280),
                fontSize = 14.sp
            )
        }

        // Your profile, your pace Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, Shape_15)
                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Your profile, your pace",
                        color = Color(0xFF2C1635),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Complete your values to help us find thoughtful matches.",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Daily matches",
                        color = Color(0xFF2C1635),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Today, chosen for you",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Profile Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, Shape_15)
                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ananya), // Replace with guy's image
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(Shape_15),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Verified Chip
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .background(Color.White, CircleShape)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.verifies_iconsymbol),
                            contentDescription = "Verified",
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Verified",
                            color = Color(0xFF0D9488),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Arjun Mehta",
                        color = Color(0xFF2C1635),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "28 · Product designer",
                        color = Color(0xFF374151),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Bengaluru · Hindi, English",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )

                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFF0FDFA), CircleShape)
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.verifies_iconsymbol),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "Dowry-free",
                                color = Color(0xFF0D9488),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFF3E8FF), CircleShape)
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_star_24),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "3 shared values",
                                color = Color(0xFF6B21A8),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Text(
                        text = "Career, family & an open mind",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFFF3E8FF), Shape_15),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_star_border_24), // bookmark icon
                                contentDescription = "Bookmark",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        
                        GradientButton(
                            onClick = { backStack.add(AppScreens.ProfileDetailsScreen) },
                            text = "View profile",
                            showEndIcon = true,
                            endIcon = R.drawable.right_arrow,
                            bgColor = Color(0xFF4A154B),
                            borderClr = Color(0xFF4A154B),
                            fontColor = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Verified Info Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, Shape_15)
                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Verified means identity checks, not a guarantee.",
                    color = Color(0xFF2C1635),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Take time to get to know someone. Your private filters remain private.",
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        // Another Introduction Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, Shape_15)
                .border(1.dp, Color(0xFFE5E7EB), Shape_15)
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Another introduction",
                    color = Color(0xFF2C1635),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Kabir Shah, 30",
                        color = Color(0xFF111827),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Architect · Pune · Verified",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFE5E7EB)))

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Nikhil Rao, 29",
                        color = Color(0xFF111827),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Researcher · Bengaluru · Verified",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }
            }
        }

        GradientButton(
            onClick = { },
            text = "Explore matches",
            showEndIcon = true,
            endIcon = R.drawable.right_arrow,
            bgColor = Color(0xFF4A154B),
            borderClr = Color(0xFF4A154B),
            fontColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        
        Text(
            text = "Privacy first. Always your choice.",
            color = Color(0xFF9CA3AF),
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}
