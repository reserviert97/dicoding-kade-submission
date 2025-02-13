package com.nurlatif.submission.ui.favorites.match

import android.content.Context
import com.google.gson.Gson
import com.nurlatif.submission.database.MatchEntity
import com.nurlatif.submission.database.database
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


interface MatchView {
    fun loadData(data: List<Event>)
}

class MatchPresenter(
    private val view: MatchView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val ctx: Context,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLastMatch(leagueId: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getPastMatch(leagueId)).await(),
                EventsResponse::class.java
            )

            view.loadData(data.events)

        }

    }

    fun getNextMatch(leagueId: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getNextMatch(leagueId)).await(),
                EventsResponse::class.java
            )

            view.loadData(data.events)
        }

    }

    fun getLastMatchFromLocal() = ctx.database.use {
        val result = select(MatchEntity.TABLE_FAVORITE_MATCH).whereArgs(
            "(${MatchEntity.MATCH_TYPE}) = {id}",
            "id" to "last_match"
        )

        val favorite = result.parseList(classParser<MatchEntity>())
        view.loadData(favorite.map {
            Event(
                eventId = it.matchId,
                eventType = null,
                eventDate = it.matchDate,
                teamHomeId = it.homeId,
                teamHomeName = it.homeTeam,
                teamHomeScore = it.homeScore,
                teamAwayId = it.awayId,
                teamAwayName = it.awayTeam,
                teamAwayScore = it.awayScore
            )
        })
    }

    fun getNextMatchFromLocal() = ctx.database.use {
        val result = select(MatchEntity.TABLE_FAVORITE_MATCH).whereArgs(
            "(${MatchEntity.MATCH_TYPE}) = {id}",
            "id" to "next_match"
        )

        val favorite = result.parseList(classParser<MatchEntity>())
        view.loadData(favorite.map {
            Event(
                eventId = it.matchId,
                eventType = null,
                eventDate = it.matchDate,
                teamHomeId = it.homeId,
                teamHomeName = it.homeTeam,
                teamHomeScore = it.homeScore,
                teamAwayId = it.awayId,
                teamAwayName = it.awayTeam,
                teamAwayScore = it.awayScore
            )
        })
    }




}