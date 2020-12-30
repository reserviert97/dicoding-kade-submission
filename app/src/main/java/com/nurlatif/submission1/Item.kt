package com.nurlatif.submission1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Item(val id: Int, val name: String, val description: String, val image: Int) : Parcelable
