package com.app.core_navigation

import androidx.navigation3.runtime.NavKey
import com.app.core_navigation.UserScreens.AuthScreens
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreens : NavKey{
    @Serializable
    object SplashScreen : AppScreens

    @Serializable
    object LanguageSelectionScreen : AppScreens

    @Serializable
    object OnBoardingScreen : AppScreens
    @Serializable
    object  ChangePasswordScreen : AuthScreens
    @Serializable
    object  ForgotPasswordScreen : AuthScreens
    @Serializable
    object UserTypeSelectionScreen : AppScreens



    @Serializable
    data class FeedbackScreen(val partnerId : String?, val callId : String?, val profilePic : String?) : AppScreens

    @Serializable
    object NotificationListScreen : AppScreens
    @Serializable
    data class LeaderboardListScreen(val userType : Int) : AppScreens
    @Serializable
    object HelpSupportScreen : AppScreens
    @Serializable
    data class CMSScreen(val type : Int, val role : Int) : AppScreens
    @Serializable
    object ReviewsListScreen : AppScreens




    @Serializable
    object HomeBottomContainerScreen : UserScreens
    @Serializable
    object HomeBottomContainerHostScreen : UserScreens
    @Serializable
    object ProfileDetailsScreen : AppScreens

    @Serializable
    object SearchScreen : AppScreens

    @Serializable
    object PartnerPreferencesScreen : AppScreens

    @Serializable
    object AccountSettingsScreen : AppScreens

    @Serializable
    object SubscriptionScreen : AppScreens

    @Serializable
    data class ChatScreen(val userId: String?, val userName: String?) : AppScreens}

@Serializable
sealed interface StoriesScreens : AppScreens{

    @Serializable
    data class StoryViewScreen( val clickedUserID : String? = null,
                                val clickedStoryIdOfSingleUser : String? = null,
                                val isForOnlySingleUser : Boolean = false,) : StoriesScreens
    @Serializable
    object StoryPostScreen : StoriesScreens
}

