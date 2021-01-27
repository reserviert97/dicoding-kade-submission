package com.nurlatif.submission.ui.favorites.team

import android.content.Context
import com.nurlatif.submission.database.TeamEntity
import com.nurlatif.submission.database.database
import com.nurlatif.submission.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

interface FavoritesTeamView {
    fun loadData(data: List<Team>)
}

class FavoritesTeamPresenter(
    private val view: FavoritesTeamView,
    private val ctx: Context
) {

    fun getFavoritesTeam() = ctx.database.use {
        val result = select(TeamEntity.TABLE_FAVORITE_TEAM)
        val favorite = result.parseList(classParser<TeamEntity>())
        view.loadData(favorite.map {
            Team(
                id = it.id,
                name = it.name,
                formedYear = it.formedYear,
                stadium = it.stadium,
                location = it.location,
                description = it.description,
                badge = it.badge
            )
        })
    }
}