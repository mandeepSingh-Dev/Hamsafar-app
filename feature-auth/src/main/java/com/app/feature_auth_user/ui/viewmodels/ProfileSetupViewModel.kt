package com.app.feature_auth_user.ui.viewmodels

import com.app.core_common.UserManager
import com.app.core_common.base.BaseViewModel
import com.app.feature_auth_user.ui.events.ProfileSetupEvent
import com.app.feature_auth_user.ui.states.ProfileSetupState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSetupViewModel @Inject constructor(
    private val userManager: UserManager
) : BaseViewModel<ProfileSetupState>(ProfileSetupState()) {

    fun onEvent(event: ProfileSetupEvent) {
        when (event) {
            is ProfileSetupEvent.OnProfileForChanged -> {
                updateState { it.copy(profileFor = event.profileFor) }
            }
            is ProfileSetupEvent.OnFullNameChanged -> {
                updateState { it.copy(fullName = event.name) }
            }
            is ProfileSetupEvent.OnDobChanged -> {
                updateState { it.copy(dob = event.dob, isAgeValid = true) }
            }
            is ProfileSetupEvent.OnGenderChanged -> {
                updateState { it.copy(gender = event.gender) }
            }
            is ProfileSetupEvent.OnReviewDateOfBirthClicked -> {
                updateState { it.copy(isAgeValid = true) }
            }
            is ProfileSetupEvent.OnMaritalStatusChanged -> {
                updateState { it.copy(maritalStatus = event.status) }
            }
            is ProfileSetupEvent.OnHeightChanged -> {
                updateState { it.copy(height = event.height) }
            }
            is ProfileSetupEvent.OnDisabilityChanged -> {
                updateState { it.copy(hasDisability = event.disability) }
            }
            is ProfileSetupEvent.OnReligionChanged -> {
                updateState { it.copy(religion = event.religion) }
            }
            is ProfileSetupEvent.OnCommunityChanged -> {
                updateState { it.copy(community = event.community) }
            }
            is ProfileSetupEvent.OnCommunityPreferenceChanged -> {
                updateState { it.copy(communityPreference = event.preference) }
            }
            is ProfileSetupEvent.OnCountryLivingChanged -> {
                updateState { it.copy(countryOfResidence = event.country) }
            }
            is ProfileSetupEvent.OnStateLivingChanged -> {
                updateState { it.copy(stateOfResidence = event.state) }
            }
            is ProfileSetupEvent.OnResidencyStatusChanged -> {
                updateState { it.copy(residencyStatus = event.status) }
            }
            is ProfileSetupEvent.OnZipCodeChanged -> {
                updateState { it.copy(zipCode = event.zipCode) }
            }
            is ProfileSetupEvent.OnGrewUpInChanged -> {
                updateState { it.copy(grewUpIn = event.grewUpIn) }
            }
            is ProfileSetupEvent.OnHighestQualificationChanged -> {
                updateState { it.copy(highestQualification = event.qualification) }
            }
            is ProfileSetupEvent.OnCollegeAttendedChanged -> {
                updateState { it.copy(collegeAttended = event.college) }
            }
            is ProfileSetupEvent.OnWorkingWithChanged -> {
                updateState { it.copy(workingWith = event.workingWith) }
            }
            is ProfileSetupEvent.OnWorkingAsChanged -> {
                updateState { it.copy(workingAs = event.workingAs) }
            }
            is ProfileSetupEvent.OnAnnualIncomeChanged -> {
                updateState { it.copy(annualIncome = event.income) }
            }
            is ProfileSetupEvent.OnEmployerNameChanged -> {
                updateState { it.copy(employerName = event.employer) }
            }
            is ProfileSetupEvent.OnMotherDetailsChanged -> {
                updateState { it.copy(motherDetails = event.details) }
            }
            is ProfileSetupEvent.OnFatherDetailsChanged -> {
                updateState { it.copy(fatherDetails = event.details) }
            }
            is ProfileSetupEvent.OnSistersCountChanged -> {
                updateState { it.copy(numberOfSisters = event.count) }
            }
            is ProfileSetupEvent.OnBrothersCountChanged -> {
                updateState { it.copy(numberOfBrothers = event.count) }
            }
            is ProfileSetupEvent.OnDietChanged -> {
                updateState { it.copy(diet = event.diet) }
            }
            is ProfileSetupEvent.OnToggleInterest -> {
                val list = uiState.value?.hobbiesAndInterests?.toMutableList() ?: mutableListOf()
                if (list.contains(event.interest)) {
                    list.remove(event.interest)
                } else {
                    list.add(event.interest)
                }
                updateState { it.copy(hobbiesAndInterests = list) }
            }
            is ProfileSetupEvent.OnHobbiesBottomSheetToggled -> {
                updateState { it.copy(isHobbiesBottomSheetOpen = event.isOpen) }
            }
            is ProfileSetupEvent.OnOpenImageOptions -> {
                updateState { it.copy(activePhotoSlotIndex = event.index, isImageOptionsSelectorOpen = true) }
            }
            is ProfileSetupEvent.OnCloseImageOptions -> {
                updateState { it.copy(isImageOptionsSelectorOpen = event.isOpen) }
            }
            is ProfileSetupEvent.OnAddPhoto -> {
                val photos = uiState.value?.profilePhotos?.toMutableList() ?: mutableListOf()
                val idx = uiState.value?.activePhotoSlotIndex ?: 0
                if (idx in photos.indices) {
                    photos[idx] = event.uri
                }
                updateState { it.copy(profilePhotos = photos, isImageOptionsSelectorOpen = false) }
            }
            is ProfileSetupEvent.OnMakeMainPhoto -> {
                val photos = uiState.value?.profilePhotos?.toMutableList() ?: mutableListOf()
                val idx = event.index
                if (idx in photos.indices && idx != 0) {
                    val temp = photos[0]
                    photos[0] = photos[idx]
                    photos[idx] = temp
                }
                updateState { it.copy(profilePhotos = photos, selectedPhotoIndexForDialog = null, zoomedPhotoIndex = null) }
            }
            is ProfileSetupEvent.OnRemovePhoto -> {
                val photos = uiState.value?.profilePhotos?.toMutableList() ?: mutableListOf()
                val idx = event.index
                if (idx in photos.indices) {
                    photos[idx] = null
                }
                updateState { it.copy(profilePhotos = photos, selectedPhotoIndexForDialog = null, zoomedPhotoIndex = null) }
            }
            is ProfileSetupEvent.OnAlbumPrivacyChanged -> {
                updateState { it.copy(albumPrivacy = event.privacy) }
            }
            is ProfileSetupEvent.OnPhotoSelectedForDialog -> {
                updateState { it.copy(selectedPhotoIndexForDialog = event.index) }
            }
            is ProfileSetupEvent.OnSwapPhotos -> {
                val photos = uiState.value?.profilePhotos?.toMutableList() ?: mutableListOf()
                val from = event.fromIndex
                val to = event.toIndex
                if (from in photos.indices && to in photos.indices) {
                    val temp = photos[from]
                    photos[from] = photos[to]
                    photos[to] = temp
                }
                updateState { it.copy(profilePhotos = photos) }
            }
            is ProfileSetupEvent.OnZoomPhoto -> {
                updateState { it.copy(zoomedPhotoIndex = event.index) }
            }
            is ProfileSetupEvent.OnBiodataNameChanged -> {
                updateState { it.copy(biodataName = event.name) }
            }
            is ProfileSetupEvent.OnBiodataEducationChanged -> {
                updateState { it.copy(biodataEducation = event.education) }
            }
            is ProfileSetupEvent.OnBiodataEmployerChanged -> {
                updateState { it.copy(biodataEmployer = event.employer) }
            }
            is ProfileSetupEvent.OnContinueClicked -> {}
            is ProfileSetupEvent.OnSkipClicked -> {}
        }
    }
}
