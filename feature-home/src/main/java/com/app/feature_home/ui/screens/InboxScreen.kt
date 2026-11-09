package com.app.feature_home.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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

data class ChatItem(
    val id: String,
    val name: String,
    val initials: String,
    val status: ChatStatus,
    val time: String,
    val message: String,
    val unreadCount: Int = 0,
    val isOnline: Boolean = true
)

enum class ChatStatus { ACCEPTED, RECEIVED, SENT }

@Composable
fun InboxScreen(
    appBackStack: NavBackStack<NavKey> = LocalBackStackNav.current
) {
    var selectedTab by remember { mutableStateOf("All") }

    val activeStories = listOf(
        Pair("Simran K.", "SK"),
        Pair("Harpreet", "HB"),
        Pair("Gurleen", "GS"),
        Pair("Jasleen", "JK"),
        Pair("Manpreet", "MK")
    )

    val chatList = remember {
        listOf(
            ChatItem("1", "Simran Kaur", "SK", ChatStatus.ACCEPTED, "10:42 AM", "Sure! Saturday evening around 6 PM would well...", 2, true),
            ChatItem("2", "Harpreet Bhatia", "HB", ChatStatus.RECEIVED, "2h ago", "Resonated with your background. Would love to connect", 0, true),
            ChatItem("3", "Gurleen Sandhu", "GS", ChatStatus.ACCEPTED, "Yesterday", "✓ You: Glad to connect! How is your week going?", 0, false),
            ChatItem("4", "Jasleen Khurana", "JK", ChatStatus.ACCEPTED, "Yesterday", "Shared my contact details with you as we...", 1, true),
            ChatItem("5", "Navneet Gill", "NG", ChatStatus.RECEIVED, "Nov 4", "Sent you an Invitation to Connect", 0, true),
            ChatItem("6", "Amanpreet Kaur", "AK", ChatStatus.ACCEPTED, "Nov 2", "Nice talking with your parents. Let's touch base soon", 0, false),
            ChatItem("7", "Ramanjit Dhillon", "RD", ChatStatus.ACCEPTED, "Oct 30", "📞 Video Call Scheduled for Sunday, 7:30 PM", 0, true),
            ChatItem("8", "Kirandeep Virdi", "KV", ChatStatus.SENT, "Oct 28", "Invitation pending approval from Kirandeep", 0, false),
            ChatItem("9", "Manjot Ahluwalia", "MA", ChatStatus.RECEIVED, "Oct 26", "Sent you a Connect Request", 0, true)
        )
    }

    val filteredChats = remember(selectedTab, chatList) {
        when (selectedTab) {
            "Unread" -> chatList.filter { it.unreadCount > 0 }
            "Accepted" -> chatList.filter { it.status == ChatStatus.ACCEPTED }
            "Received" -> chatList.filter { it.status == ChatStatus.RECEIVED }
            else -> chatList
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF6F7))
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chats",
                color = Color(0xFF1F1318),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Safety Badge
            Row(
                modifier = Modifier
                    .background(Color(0xFFF3E8FF), CircleShape)
                    .border(1.dp, Color(0xFFD8B4FE), CircleShape)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("🛡️", fontSize = 12.sp)
                Text(
                    text = "Safety",
                    color = Color(0xFF7C1D3E),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Horizontal Stories Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(activeStories) { story ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.animateContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF7C1D3E), Color(0xFFF43F5E), Color(0xFF9333EA))
                                ),
                                shape = CircleShape
                            )
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFFFCE7F3), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = story.second,
                                color = Color(0xFF7C1D3E),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            // Online indicator dot
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(Color(0xFF22C55E), CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                    }
                    Text(
                        text = story.first,
                        color = Color(0xFF2B1920),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Filter Tabs Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "Unread", "Accepted", "Received").forEach { tab ->
                val isSelected = tab == selectedTab
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) Color(0xFF2C1635) else Color(0xFFF3EFEF),
                            CircleShape
                        )
                        .clickable { selectedTab = tab }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = tab,
                            color = if (isSelected) Color.White else Color(0xFF6B7280),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                        if (tab == "Unread") {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(if (isSelected) Color.White else Color(0xFF7C1D3E), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("5", color = if (isSelected) Color(0xFF7C1D3E) else Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        if (tab == "Received") {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(Color(0xFFEF4444), CircleShape)
                            )
                        }
                    }
                }
            }
        }

        // Section header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SHOWING ALL CHATS",
                color = Color(0xFF8C6D79),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp
            )
            Text(
                text = "${filteredChats.size} Matches",
                color = Color(0xFF7C1D3E),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Chat List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .animateContentSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredChats) { chat ->
                ChatCard(chat) {
                    appBackStack.add(AppScreens.ChatScreen(chat.id, chat.name))
                }
            }
        }
    }
}

@Composable
fun ChatCard(chat: ChatItem, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(Color.White, Shape_20)
            .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
            .clickable { onClick() }
            .padding(14.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Avatar with Gradient Ring & Online Dot
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF7C1D3E), Color(0xFF9333EA))
                        ),
                        shape = CircleShape
                    )
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFCE7F3), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.initials,
                        color = Color(0xFF7C1D3E),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (chat.isOnline) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(Color(0xFF22C55E), CircleShape)
                                .border(1.5.dp, Color.White, CircleShape)
                                .align(Alignment.BottomEnd)
                        )
                    }
                }
            }

            // Details Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = chat.name,
                            color = Color(0xFF1F1318),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // Status Badge
                        val badgeBg = when (chat.status) {
                            ChatStatus.ACCEPTED -> Color(0xFFF3E8FF)
                            ChatStatus.RECEIVED -> Color(0xFFFFF1F2)
                            ChatStatus.SENT -> Color(0xFFFEF3C7)
                        }
                        val badgeText = when (chat.status) {
                            ChatStatus.ACCEPTED -> Color(0xFF7C1D3E)
                            ChatStatus.RECEIVED -> Color(0xFFE11D48)
                            ChatStatus.SENT -> Color(0xFFD97706)
                        }
                        Box(
                            modifier = Modifier
                                .background(badgeBg, Shape_15)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = chat.status.name,
                                color = badgeText,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = chat.time,
                        color = Color(0xFF9CA3AF),
                        fontSize = 11.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = chat.message,
                        color = Color(0xFF726A7A),
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )

                    if (chat.unreadCount > 0) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFF7C1D3E), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = chat.unreadCount.toString(),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // If Received status, show Accept / Decline buttons
                if (chat.status == ChatStatus.RECEIVED) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF7C1D3E), CircleShape)
                                .clickable {}
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Accept", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFF3EFEF), CircleShape)
                                .border(1.dp, Color(0xFFE5D5DA), CircleShape)
                                .clickable {}
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Decline", color = Color(0xFF2B1920), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
