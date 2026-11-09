package com.app.feature_home.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.app.core_navigation.AppScreens
import com.app.core_ui.R
import com.app.core_ui.components.BoxCommon
import com.app.core_ui.components.CommonAuthHeader
import com.app.core_ui.components.dropShadowCustom
import com.app.core_ui.local_providers.LocalBackStackNav
import com.zodiaq.ui.theme.Shape_20
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable object HomeTabKey : NavKey
@Serializable object MatchesTabKey : NavKey
@Serializable object InterestsTabKey : NavKey
@Serializable object InboxTabKey : NavKey
@Serializable object MeTabKey : NavKey

@Composable
fun HomeBottomContainerScreen(
    appBackStack: NavBackStack<NavKey> = LocalBackStackNav.current
) {
    val bottomNavBackStack = rememberNavBackStack(MatchesTabKey)
    val currentTab = bottomNavBackStack.lastOrNull() ?: MatchesTabKey
    val headerTitle = when (currentTab) {
        is MeTabKey -> "Edit Profile"
        else -> "Hamsafar"
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(320.dp),
                drawerContainerColor = Color.White
            ) {
                DrawerContent(
                    onClose = { scope.launch { drawerState.close() } },
                    onNavigate = { route ->
                        scope.launch { drawerState.close() }
                        when (route) {
                            "Preferences" -> appBackStack.add(AppScreens.PartnerPreferencesScreen)
                            "AccountSettings" -> appBackStack.add(AppScreens.AccountSettingsScreen)
                            "Subscription" -> appBackStack.add(AppScreens.SubscriptionScreen)
                            "SearchID" -> appBackStack.add(AppScreens.SearchScreen)
                        }
                    }
                )
            }
        }
    ) {
        BoxCommon(
            modifier = Modifier.fillMaxSize(),
            showBackgroundImage = false,
            isAppearanceLightStatusBars = true
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                // Common Header for all tabs in HomeContainerScreen (Left icon opens drawer)
                CommonAuthHeader(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    title = headerTitle,
                    leftIcon = R.drawable.outline_more_vert_24,
                    rightIcon = R.drawable.baseline_info_24,
                    onLeftIconClick = { scope.launch { drawerState.open() } },
                    backStack = appBackStack
                )

                // Content
                Box(modifier = Modifier.weight(1f)) {
                    NavDisplay(
                        backStack = bottomNavBackStack,
                        onBack = {
                            if (bottomNavBackStack.size > 1) {
                                bottomNavBackStack.removeLastOrNull()
                            } else {
                                appBackStack.removeLastOrNull()
                            }
                        },
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
                        },
                        entryProvider = androidx.navigation3.runtime.entryProvider {
                            entry<MatchesTabKey> { MatchesScreen(appBackStack) }
                            entry<InterestsTabKey> { InterestsScreen(appBackStack) }
                            entry<InboxTabKey> { InboxScreen() }
                            entry<MeTabKey> { ProfileSettingsScreen(appBackStack) }
                        }
                    )
                }

                // Bottom Navigation Bar with safe navigation bars padding
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .dropShadowCustom(shape = Shape_20, color = Color.Black.copy(0.1f), radius = 10.dp)
                        .navigationBarsPadding()
                        .height(70.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(
                        icon = R.drawable.baseline_visibility_24, 
                        label = "Matches",
                        isSelected = currentTab is MatchesTabKey,
                        onClick = { if (currentTab !is MatchesTabKey) bottomNavBackStack.add(MatchesTabKey) }
                    )
                    BottomNavItem(
                        icon = R.drawable.baseline_visibility_24, 
                        label = "Interests",
                        isSelected = currentTab is InterestsTabKey,
                        onClick = { if (currentTab !is InterestsTabKey) bottomNavBackStack.add(InterestsTabKey) }
                    )
                    BottomNavItem(
                        icon = R.drawable.baseline_visibility_24, 
                        label = "Inbox",
                        isSelected = currentTab is InboxTabKey,
                        onClick = { if (currentTab !is InboxTabKey) bottomNavBackStack.add(InboxTabKey) }
                    )
                    BottomNavItem(
                        icon = R.drawable.baseline_visibility_24, 
                        label = "Me",
                        isSelected = currentTab is MeTabKey,
                        onClick = { if (currentTab !is MeTabKey) bottomNavBackStack.add(MeTabKey) }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) Color(0xFF4A154B) else Color(0xFF9CA3AF)
    val bgColor = if (isSelected) Color(0xFFF3E8FF) else Color.Transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(Shape_20)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .background(bgColor)
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(color)
        )
        AnimatedVisibility(visible = isSelected) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = color,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


