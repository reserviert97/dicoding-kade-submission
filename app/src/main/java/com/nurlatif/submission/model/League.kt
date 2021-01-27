package com.nurlatif.submission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(val id: Int, val name: String, val image: Int) : Parcelable
