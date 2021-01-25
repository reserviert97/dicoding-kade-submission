package com.nurlatif.submission.database

data class MatchEntity(
    val matchId: String?,
    val matchDate: String?,
    val homeTeam: String?,
    val homeId: String?,
    val homeScore: String?,
    val awayTeam: String?,
    val awayId: String?,
    val awayScore: String?,
    val matchType: String?
) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val MATCH_ID = "MATCH_ID"
        const val DATE = "DATE"
        const val TEAM_HOME = "TEAM_HOME"
        const val TEAM_HOME_ID = "TEAM_HOME_ID"
        const val TEAM_HOME_SCORE = "TEAM_HOME_SCORE"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_SCORE = "AWAY_TEAM_SCORE"
        const val MATCH_TYPE = "MATCH_TYPE"

    }
}
