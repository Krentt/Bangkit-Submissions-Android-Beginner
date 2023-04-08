package com.example.actionfigureapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Figures(
    val name: String,
    val description: String,
    val photo: Int,
    val price: String,
    val sellerName : String,
    val sellerPhoto: String
) : Parcelable
