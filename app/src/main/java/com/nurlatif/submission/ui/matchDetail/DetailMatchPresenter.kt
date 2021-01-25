package com.nurlatif.submission.ui.matchDetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.database.MatchEntity
import com.nurlatif.submission.database.database
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

interface DetailMatchView {
    fun loadData(data: MatchResponse)
    fun loadImage(homeBadge: String, awayBadge: String)
    fun setFavoriteState(state: Boolean)
    fun showToast(message: String)
}

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val ctx: Context,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetail(matchId: String) = GlobalScope.launch(context.main) {
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getDetailMatch(matchId)).await(),
            DetailMatchResponse::class.java
        )
        view.loadData(data.events[0])
    }


    fun getTeamBadgeUrl(teamAwayId: String, teamHomeId: String) = GlobalScope.launch(context.main) {
        val teamAway = gson.fromJson(
            api.doRequest(TheSportDBApi.getDetailTeam(teamAwayId)).await(),
            DetailTeamResponse::class.java
        )

        val teamHome = gson.fromJson(
            api.doRequest(TheSportDBApi.getDetailTeam(teamHomeId)).await(),
            DetailTeamResponse::class.java
        )
        view.loadImage(teamHome.teams[0].badge, teamAway.teams[0].badge)
    }

    fun checkFavorite(id: String) {
        ctx.database.use {
            val result = select(MatchEntity.TABLE_FAVORITE_MATCH)
                .whereArgs("(${MatchEntity.MATCH_ID}) = {id}", "id" to id)
            val favorite = result.parseList(classParser<MatchEntity>())

            if (favorite.isEmpty()) {
                view.setFavoriteState(false)
            } else {
                view.setFavoriteState(true)
            }
        }
    }

    fun removeFromFavorite(id: String) {
        try {
            ctx.database.use {
                delete(
                    MatchEntity.TABLE_FAVORITE_MATCH,
                    "(${MatchEntity.MATCH_ID} = {id})",
                    "id" to id
                )
            }
            view.showToast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            view.showToast(e.localizedMessage)
        }
    }

    fun addToFavorite(match: MatchResponse, type: String) {
        try {
            ctx.database.use {
                insert(
                    MatchEntity.TABLE_FAVORITE_MATCH,
                    MatchEntity.MATCH_ID to match.matchId,
                    MatchEntity.DATE to match.matchDate,
                    MatchEntity.TEAM_HOME to match.homeTeam,
                    MatchEntity.TEAM_HOME_ID to match.homeId,
                    MatchEntity.TEAM_HOME_SCORE to match.homeScore,
                    MatchEntity.AWAY_TEAM to match.awayTeam,
                    MatchEntity.AWAY_TEAM_ID to match.awayId,
                    MatchEntity.AWAY_TEAM_SCORE to match.awayScore,
                    MatchEntity.MATCH_TYPE to type
                )
            }

            view.showToast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            view.showToast(e.localizedMessage)
        }
    }

}