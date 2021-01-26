package com.nurlatif.submission.database


data class TeamEntity(
    val id: String,
    val name: String,
    val formedYear: String,
    val stadium: String,
    val location: String,
    val description: String,
    val badge: String
) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val STADIUM = "STADIUM"
        const val LOCATION = "LOCATION"
        const val DESCRIPTION = "DESCRIPTION"
        const val BADGE = "BADGE_URL"
    }
}
