package com.nurlatif.submission.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    val id: String?,
    @SerializedName("")
    val name: String?,
    @SerializedName("")
    val formedYear: String?,
    @SerializedName("strStadium")
    val stadium: String?,
    @SerializedName("strStadiumLocation")
    val location: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strTeamBadge")
    val badge: String?
)
