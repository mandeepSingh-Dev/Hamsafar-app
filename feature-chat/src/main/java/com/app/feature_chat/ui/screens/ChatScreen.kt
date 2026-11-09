package com.app.feature_chat.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ChatMessage(
    val id: String,
    val text: String,
    val isIncoming: Boolean,
    val time: String
)

@Composable
fun ChatScreen(
    userId: String? = null,
    userName: String? = "Simran Kaur",
    backStack: NavBackStack<NavKey>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputMessage by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    val messages = remember {
        mutableStateListOf(
            ChatMessage("1", "Hi Mandeep, thanks for connecting! Glad to hear from you.", true, "10:40 AM"),
            ChatMessage("2", "Hello Simran! Pleasure connecting. Would you be free for a brief call?", false, "10:41 AM"),
            ChatMessage("3", "Sure! Saturday evening around 6 PM works really well for me.", true, "10:42 AM"),
            ChatMessage("4", "Perfect, let's catch up then!", false, "10:44 AM")
        )
    }

    val sendMessage = {
        if (inputMessage.isNotBlank()) {
            val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            messages.add(
                ChatMessage(
                    id = System.currentTimeMillis().toString(),
                    text = inputMessage.trim(),
                    isIncoming = false,
                    time = currentTime
                )
            )
            inputMessage = ""
            keyboardController?.hide()
        }
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
                .statusBarsPadding()
                .navigationBarsPadding()
                .background(Color(0xFFFAF6F7))
                .animateContentSize()
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
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

                    // Avatar with online status
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(Color(0xFFFCE7F3), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userName?.take(2)?.uppercase() ?: "SK",
                            color = Color(0xFF7C1D3E),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .background(Color(0xFF22C55E), CircleShape)
                                .border(1.5.dp, Color.White, CircleShape)
                                .align(Alignment.BottomEnd)
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                        Text(
                            text = userName ?: "Simran Kaur",
                            color = Color(0xFF1F1318),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Online now",
                            color = Color(0xFF22C55E),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFFF2EAEB), CircleShape)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👤", fontSize = 14.sp)
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFFF2EAEB), CircleShape)
                            .clickable { showMenu = !showMenu },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⋮", color = Color(0xFF2B1920), fontSize = 16.sp, fontWeight = FontWeight.Bold)

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Report Profile", color = Color(0xFF1F1318), fontSize = 13.sp) },
                                onClick = { showMenu = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Block Member", color = Color(0xFFE11D48), fontSize = 13.sp, fontWeight = FontWeight.Bold) },
                                onClick = { showMenu = false }
                            )
                        }
                    }
                }
            }

            // Scrollable Message History & Profile Info Cards
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .animateContentSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 1. Accepted Member Banner
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .background(Color.White, Shape_20)
                            .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                            .padding(14.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Color(0xFFF3E8FF), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("✓", color = Color(0xFF7C1D3E), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "Accepted Member",
                                    color = Color(0xFF1F1318),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "You sent a request and ${userName ?: "Simran"} accepted",
                                    color = Color(0xFF726A7A),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }

                // 2. Profile Summary Card
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .background(Color.White, Shape_20)
                            .border(1.2.dp, Color(0xFFEFE4E7), Shape_20)
                            .padding(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = userName ?: "Simran Kaur",
                                    color = Color(0xFF1F1318),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFF3E8FF), Shape_15)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text("Accepted member", color = Color(0xFF7C1D3E), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }

                            Text(
                                text = "• Age / Ht: 26 yrs, 5'5\" (165 cm)  • Location: New Delhi, Delhi",
                                color = Color(0xFF726A7A),
                                fontSize = 11.sp
                            )
                            Text(
                                text = "• Career: Not working  • ✓ Govt ID Verified",
                                color = Color(0xFF726A7A),
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                // Date Pill TODAY
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .padding(vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFEFE4E7), Shape_15)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text("TODAY", color = Color(0xFF726A7A), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Message Bubbles
                items(messages) { msg ->
                    ChatBubble(msg)
                }

                // Guidelines notice pill
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .padding(vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFF3E8FF), Shape_20)
                                .border(1.dp, Color(0xFFD8B4FE), Shape_20)
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "🕒 Keep chat courteous & aligned with Hamsafar guidelines",
                                color = Color(0xFF7C1D3E),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Bottom Input Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .background(Color.White)
                    .border(1.dp, Color(0xFFEDE0E4))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFF9F5F6), Shape_20)
                            .border(1.dp, Color(0xFFE7D8DD), Shape_20)
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (inputMessage.isEmpty()) {
                            Text(
                                text = "Write a message...",
                                color = Color(0xFF9CA3AF),
                                fontSize = 13.sp
                            )
                        }
                        BasicTextField(
                            value = inputMessage,
                            onValueChange = { inputMessage = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = false,
                            maxLines = 4
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color(0xFF7C1D3E), CircleShape)
                            .clickable { sendMessage() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = "Send",
                            modifier = Modifier.size(18.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(msg: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        horizontalArrangement = if (msg.isIncoming) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier
                    .align(if (msg.isIncoming) Alignment.CenterStart else Alignment.CenterEnd)
                    .background(
                        color = if (msg.isIncoming) Color.White else Color(0xFF3B182B),
                        shape = Shape_20
                    )
                    .border(
                        width = if (msg.isIncoming) 1.dp else 0.dp,
                        color = if (msg.isIncoming) Color(0xFFEFE4E7) else Color.Transparent,
                        shape = Shape_20
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = msg.text,
                    color = if (msg.isIncoming) Color(0xFF1F1318) else Color.White,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = msg.time,
                        color = if (msg.isIncoming) Color(0xFF9CA3AF) else Color(0xFFD8B4FE),
                        fontSize = 10.sp
                    )
                    if (!msg.isIncoming) {
                        Text(
                            text = "✓✓",
                            color = Color(0xFF22C55E),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
