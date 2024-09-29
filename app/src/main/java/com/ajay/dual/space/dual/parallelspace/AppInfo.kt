package com.ajay.dual.space.dual.parallelspace

import android.graphics.Bitmap
import java.io.Serializable

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: Bitmap,
    var isSelected: Boolean = false, // Added isSelected property for selection
) : Serializable