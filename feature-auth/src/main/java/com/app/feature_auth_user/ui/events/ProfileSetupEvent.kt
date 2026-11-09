package com.app.feature_auth_user.ui.events

sealed interface ProfileSetupEvent {
    // Step 1
    data class OnProfileForChanged(val profileFor: String) : ProfileSetupEvent
    
    // Step 2
    data class OnFullNameChanged(val name: String) : ProfileSetupEvent
    data class OnDobChanged(val dob: String) : ProfileSetupEvent
    data class OnGenderChanged(val gender: String) : ProfileSetupEvent
    object OnReviewDateOfBirthClicked : ProfileSetupEvent

    // Step 3
    data class OnMaritalStatusChanged(val status: String) : ProfileSetupEvent
    data class OnHeightChanged(val height: Float) : ProfileSetupEvent
    data class OnDisabilityChanged(val disability: String) : ProfileSetupEvent

    // Step 4
    data class OnReligionChanged(val religion: String) : ProfileSetupEvent
    data class OnCommunityChanged(val community: String) : ProfileSetupEvent
    data class OnCommunityPreferenceChanged(val preference: String) : ProfileSetupEvent

    // Step 5 (Locations)
    data class OnCountryLivingChanged(val country: String) : ProfileSetupEvent
    data class OnStateLivingChanged(val state: String) : ProfileSetupEvent
    data class OnResidencyStatusChanged(val status: String) : ProfileSetupEvent
    data class OnZipCodeChanged(val zipCode: String) : ProfileSetupEvent
    data class OnGrewUpInChanged(val grewUpIn: String) : ProfileSetupEvent

    // Step 6 (Education)
    data class OnHighestQualificationChanged(val qualification: String) : ProfileSetupEvent
    data class OnCollegeAttendedChanged(val college: String) : ProfileSetupEvent

    // Step 7 (Career)
    data class OnWorkingWithChanged(val workingWith: String) : ProfileSetupEvent
    data class OnWorkingAsChanged(val workingAs: String) : ProfileSetupEvent
    data class OnAnnualIncomeChanged(val income: String) : ProfileSetupEvent
    data class OnEmployerNameChanged(val employer: String) : ProfileSetupEvent

    // Step 8 (Family)
    data class OnMotherDetailsChanged(val details: String) : ProfileSetupEvent
    data class OnFatherDetailsChanged(val details: String) : ProfileSetupEvent
    data class OnSistersCountChanged(val count: String) : ProfileSetupEvent
    data class OnBrothersCountChanged(val count: String) : ProfileSetupEvent

    // Step 9 (Lifestyle)
    data class OnDietChanged(val diet: String) : ProfileSetupEvent
    data class OnToggleInterest(val interest: String) : ProfileSetupEvent
    data class OnHobbiesBottomSheetToggled(val isOpen: Boolean) : ProfileSetupEvent

    // Step 10 (Photos)
    data class OnOpenImageOptions(val index: Int) : ProfileSetupEvent
    data class OnCloseImageOptions(val isOpen: Boolean) : ProfileSetupEvent
    data class OnAddPhoto(val uri: String) : ProfileSetupEvent
    data class OnMakeMainPhoto(val index: Int) : ProfileSetupEvent
    data class OnRemovePhoto(val index: Int) : ProfileSetupEvent
    data class OnAlbumPrivacyChanged(val privacy: String) : ProfileSetupEvent
    data class OnPhotoSelectedForDialog(val index: Int?) : ProfileSetupEvent
    data class OnSwapPhotos(val fromIndex: Int, val toIndex: Int) : ProfileSetupEvent
    data class OnZoomPhoto(val index: Int?) : ProfileSetupEvent

    // Biodata
    data class OnBiodataNameChanged(val name: String) : ProfileSetupEvent
    data class OnBiodataEducationChanged(val education: String) : ProfileSetupEvent
    data class OnBiodataEmployerChanged(val employer: String) : ProfileSetupEvent
    
    object OnContinueClicked : ProfileSetupEvent
    object OnSkipClicked : ProfileSetupEvent
}
