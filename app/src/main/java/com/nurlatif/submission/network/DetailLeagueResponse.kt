package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class DetailLeagueResponse(val leagues: List<LeagueResponse>)

data class LeagueResponse(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeagueAlternate")
    var leagueNickname: String? = null,

    @SerializedName("strCurrentSeason")
    var currentSeason: String? = null,

    @SerializedName("strCountry")
    var country: String? = null,

    @SerializedName("strTrophy")
    var trophy: String? = null,

    @SerializedName("strFanart1")
    var fanArt1: String? = null,

    @SerializedName("strFanart2")
    var fanArt2: String? = null,

    @SerializedName("strFanart3")
    var fanArt3: String? = null,

    @SerializedName("strFanart4")
    var fanArt4: String? = null
)



