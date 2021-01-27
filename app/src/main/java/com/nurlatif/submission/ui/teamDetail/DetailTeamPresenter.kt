package com.nurlatif.submission.ui.teamDetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.nurlatif.submission.database.TeamEntity
import com.nurlatif.submission.database.database
import com.nurlatif.submission.model.Team
import org.jetbrains.anko.db.*


interface DetailTeamView {
    fun setFavoriteState(state: Boolean)
    fun showToast(message: String)
}

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val ctx: Context
) {

    fun checkFavorite(id: String) {
        ctx.database.use {
            val result = select(TeamEntity.TABLE_FAVORITE_TEAM)
                .whereArgs("(${TeamEntity.TEAM_ID}) = {id}", "id" to id)
            val favorite = result.parseList(classParser<TeamEntity>())

            if (favorite.isEmpty()) {
                view.setFavoriteState(false)
            } else {
                view.setFavoriteState(true)
            }
        }
    }

    fun addToFavorite(team: Team) {
        try {
            ctx.database.use {
                insert(
                    TeamEntity.TABLE_FAVORITE_TEAM,
                    TeamEntity.TEAM_ID to team.id,
                    TeamEntity.TEAM_NAME to team.name,
                    TeamEntity.FORMED_YEAR to team.formedYear,
                    TeamEntity.STADIUM to team.stadium,
                    TeamEntity.LOCATION to team.location,
                    TeamEntity.DESCRIPTION to team.description,
                    TeamEntity.BADGE to team.badge
                )
            }

            view.showToast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            view.showToast(e.localizedMessage)
        }
    }

    fun removeFromFavorite(id: String) {
        try {
            ctx.database.use {
                delete(
                    TeamEntity.TABLE_FAVORITE_TEAM,
                    "(${TeamEntity.TEAM_ID} = {id})",
                    "id" to id
                )
            }
            view.showToast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            view.showToast(e.localizedMessage)
        }
    }

}