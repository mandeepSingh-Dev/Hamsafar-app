package com.app.core_auth.model.requestBody

data class ChangePasswordRequestBody(
    val currentPassword: String? = null,
    val newPassword: String?
)