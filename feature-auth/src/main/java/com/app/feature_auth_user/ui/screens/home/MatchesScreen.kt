package com.app.feature_auth_user.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import com.zodiaq.ui.theme.Shape_15
import com.zodiaq.ui.theme.Shape_20

@Composable
fun MatchesScreen(backStack: NavBackStack<NavKey>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Search Bar and Chips
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F4F6), Shape_15)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF9CA3AF))
                )
                Text("Search name, profession, caste, city...", color = Color(0xFF9CA3AF), fontSize = 14.sp)
            }

            // Chips Scrollable Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .border(1.dp, Color(0xFFE5E7EB), CircleShape)
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "Filter", color = Color(0xFF374151), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Image(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(Color(0xFF374151))
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFF2C1635), CircleShape)
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.locationcontainer),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text(text = "Nearby Profiles", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .border(1.dp, Color(0xFFE5E7EB), CircleShape)
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.verifies_iconsymbol),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(text = "Verified", color = Color(0xFF374151), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        // Horizontal Carousel list (HorizontalPager with page spacing / padding)
        val pagerState = rememberPagerState(pageCount = { 5 })
        
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            FullProfileCard(page = page, totalPages = 5)
        }
    }
}

@Composable
fun FullProfileCard(page: Int, totalPages: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        // Image with Details Overlay taking the remaining space
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(Shape_20)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ananya),
                contentDescription = "Profile",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay at bottom
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

            // Top-left Chips
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
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

                Row(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.locationcontainer),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text("2.4 km away", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }

            // Top-right New badge & page indicator
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3E8FF), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("New", color = Color(0xFF6B21A8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("${page + 1} / $totalPages", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }

            // Text details overlay at bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Badges row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            colorFilter = ColorFilter.tint(Color(0xFFDFA031))
                        )
                        Text("3 shared values", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }

                    Row(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Dowry-free", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Arjun Mehta",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.verifies_iconsymbol),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "28 yrs · 5'11\" · Product Designer",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Khatri · Bengaluru, Karnataka",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp
                )

                // Dots indicator
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .size(if (index == 0) 16.dp else 6.dp, 6.dp)
                                .background(if (index == 0) Color.White else Color.White.copy(alpha = 0.5f), CircleShape)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3 Action Buttons Below Image with proper text and icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Super Connect Button
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFFEF3C7), Shape_20)
                    .border(1.dp, Color(0xFFFDE68A), Shape_20)
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = "Super Connect",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFD97706))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Super Connect",
                    color = Color(0xFF92400E),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Connect Button
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF4A154B), Shape_20)
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.verifies_iconsymbol),
                    contentDescription = "Connect",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Connect",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Shortlist Button
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, Shape_20)
                    .border(1.dp, Color(0xFFE5E7EB), Shape_20)
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_star_border_24),
                    contentDescription = "Shortlist",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF374151))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Shortlist",
                    color = Color(0xFF374151),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
