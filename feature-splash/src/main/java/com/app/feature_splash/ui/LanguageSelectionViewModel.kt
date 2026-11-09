package com.app.feature_splash.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import com.app.core_common.UserManager
import com.app.core_common.base.BaseViewModel
import com.app.core_common.base.UIState
import com.app.core_common.utils.CommonUIEvents
import com.app.core_navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

data class LanguageItem(
    val name: String,
    val nativeName: String,
    val tag: String
)

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val userManager: UserManager
): BaseViewModel<UIState>() {

    val languages = listOf(
        LanguageItem("English", "English", "en"),
        LanguageItem("Hindi", "हिन्दी", "hi"),
        LanguageItem("Punjabi", "ਪੰਜਾਬੀ", "pa"),
        LanguageItem("Gujarati", "ગુજરાતી", "gu"),
        LanguageItem("Bengali", "বাংলা", "bn"),
        LanguageItem("Tamil", "தமிழ்", "ta"),
        LanguageItem("Telugu", "తెలుగు", "te"),
        LanguageItem("Kannada", "ಕನ್ನಡ", "kn"),
        LanguageItem("Malayalam", "മലയാളം", "ml"),
        LanguageItem("Odia", "· Odia", "or"),
        LanguageItem("Urdu", "اردو", "ur"),
        LanguageItem("Marathi", "मराठी", "mr")
    )

    var selectedLanguage by mutableStateOf("English")
        private set

    init {
        viewModelScope.launch {
            val savedLang = userManager.getSelectedLanguage()
            if (savedLang.isNotEmpty()) {
                selectedLanguage = savedLang
            }
        }
    }

    fun selectLanguage(languageName: String, tag: String) {
        selectedLanguage = languageName
        viewModelScope.launch {
            userManager.saveSelectedLanguage(languageName)
            val appLocale = LocaleListCompat.forLanguageTags(tag)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    fun onContinueClicked() {
        onEvent(CommonUIEvents.NavigateTo(AppScreens.OnBoardingScreen))
    }
}
