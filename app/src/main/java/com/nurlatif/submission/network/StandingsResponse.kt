package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class StandingsResponse(val table: List<Standing>)

data class Standing(
    var name: String? = null,
    var teamid: String? = null,
    @SerializedName("played")
    var totalPlayed: Int,
    @SerializedName("goalsfor")
    var goalsFor: Int,
    @SerializedName("goalsagainst")
    var goalsagainst: Int,
    @SerializedName("goalsdifference")
    var goalsdifference: Int,
    var win: Int,
    var draw: Int,
    var loss: Int,
    @SerializedName("total")
    var totalPoint: Int
)
