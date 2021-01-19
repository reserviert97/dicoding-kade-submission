package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class DetailLeagueResponse(val leagues: List<LeagueResponse>)

data class LeagueResponse(
    @SerializedName("idLeague")
    val leagueId: String? = null,

    @SerializedName("strLeagueAlternate")
    val leagueNickname: String? = null,

    @SerializedName("strCurrentSeason")
    val currentSeason: String? = null,

    @SerializedName("strCountry")
    val country: String? = null,

    @SerializedName("strTrophy")
    val trophy: String? = null,

    @SerializedName("strFanart1")
    val fanArt1: String? = null,

    @SerializedName("strFanart2")
    val fanArt2: String? = null,

    @SerializedName("strFanart3")
    val fanArt3: String? = null,

    @SerializedName("strFanart4")
    val fanArt4: String? = null
)



