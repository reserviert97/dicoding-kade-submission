package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class DetailMatchResponse(val events: List<MatchResponse>)

data class MatchResponse(
    @SerializedName("idEvent")
    val matchId: String? = null,
    @SerializedName("dateEvent")
    val matchDate: String? = null,
    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,
    @SerializedName("idHomeTeam")
    val homeId: String,
    @SerializedName("idAwayTeam")
    val awayId: String,
    @SerializedName("strAwayTeam")
    val awayTeam: String? = null,
    @SerializedName("intHomeScore")
    val homeScore: String? = null,
    @SerializedName("intAwayScore")
    val awayScore: String? = null,
    @SerializedName("intHomeShots")
    val homeShots: String? = null,
    @SerializedName("intAwayShots")
    val awayShots: String? = null,
    @SerializedName("strHomeGoalDetails")
    val homeGoal: String? = null,
    @SerializedName("strAwayGoalDetails")
    val awayGoal: String? = null,
    @SerializedName("strHomeRedCards")
    val homeRedCards: String? = null,
    @SerializedName("strAwayRedCards")
    val awayRedCards: String? = null,
    @SerializedName("strHomeYellowCards")
    val homeYellowCards: String? = null,
    @SerializedName("strAwayYellowCards")
    val awayYellowCards: String? = null,
    @SerializedName("strHomeFormation")
    val homeFormation: String? = null,
    @SerializedName("strAwayFormation")
    val awayFormation: String? = null
)