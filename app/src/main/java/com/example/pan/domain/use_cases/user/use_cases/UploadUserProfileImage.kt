package com.example.pan.domain.use_cases.user.use_cases

import android.graphics.Bitmap
import com.example.pan.domain.repository.user.UserRepository

class UploadUserProfileImage constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(imageBitmap: Bitmap) = repo.uploadUserProfileImage(imageBitmap)
}