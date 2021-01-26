package com.nurlatif.submission.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @SerializedName("idTeam")
    val id: String?,
    @SerializedName("strTeam")
    val name: String?,
    @SerializedName("intFormedYear")
    val formedYear: String?,
    @SerializedName("strStadium")
    val stadium: String?,
    @SerializedName("strStadiumLocation")
    val location: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strTeamBadge")
    val badge: String?
) : Parcelable
