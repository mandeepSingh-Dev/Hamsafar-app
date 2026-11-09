package com.app.feature_auth_user.ui.states

import com.app.core_common.base.UIState

data class ProfileSetupState(
    // Step 1
    val profileFor: String? = null,
    // Step 2
    val fullName: String = "",
    val dob: String = "",
    val gender: String = "",
    val isAgeValid: Boolean = true,
    // Step 3
    val maritalStatus: String = "",
    val height: Float = 170f,
    val hasDisability: String = "No",
    // Step 4
    val religion: String = "",
    val community: String = "",
    val communityPreference: String = "",
    // Step 5 (Locations)
    val countryOfResidence: String = "",
    val stateOfResidence: String = "",
    val residencyStatus: String = "",
    val zipCode: String = "",
    val grewUpIn: String = "",
    // Step 6 (Education)
    val highestQualification: String = "",
    val collegeAttended: String = "",
    // Step 7 (Career)
    val workingWith: String = "",
    val workingAs: String = "",
    val annualIncome: String = "",
    val employerName: String = "",
    // Step 8 (Family)
    val motherDetails: String = "",
    val fatherDetails: String = "",
    val numberOfSisters: String = "0",
    val numberOfBrothers: String = "0",
    // Step 9 (Lifestyle)
    val diet: String = "",
    val hobbiesAndInterests: List<String> = emptyList(),
    val isHobbiesBottomSheetOpen: Boolean = false,
    // Step 10 (Profile Photos)
    val profilePhotos: List<String?> = listOf(null, null, null, null, null, null, null, null),
    val albumPrivacy: String = "Verified members",
    val selectedPhotoIndexForDialog: Int? = null,
    val isImageOptionsSelectorOpen: Boolean = false,
    val activePhotoSlotIndex: Int = 0,
    val zoomedPhotoIndex: Int? = null,
    val draggingPhotoIndex: Int? = null,
    
    // Biodata
    val biodataName: String = "",
    val biodataEducation: String = "",
    val biodataEmployer: String = ""
) : UIState
