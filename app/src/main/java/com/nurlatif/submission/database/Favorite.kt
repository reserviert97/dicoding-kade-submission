package com.nurlatif.submission.database

import com.google.gson.annotations.SerializedName

data class Favorite(
    val matchId: String?,
    val matchDate: String?,
    val homeTeam: String?,
    val homeId: String?,
    val homeScore: String?,
    val awayTeam: String?,
    val awayId: String?,
    val awayScore: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val MATCH_ID = "MATCH_ID"
        const val DATE = "DATE"
        const val TEAM_HOME = "TEAM_HOME"
        const val TEAM_HOME_ID = "TEAM_HOME_ID"
        const val TEAM_HOME_SCORE = "TEAM_HOME_SCORE"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_SCORE = "AWAY_TEAM_SCORE"

    }
}
