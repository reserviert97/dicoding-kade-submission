package com.nurlatif.submission.ui.matchDetail

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface DetailMatchView {
    fun loadData(data: MatchResponse)
    fun loadImage(homeBadge: String, awayBadge: String)
}

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetail(matchId: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailMatch(matchId)).await(),
                DetailMatchResponse::class.java
            )

            Log.d("DetailMatchPresenter", "${data.events[0]}")

            view.loadData(data.events[0])

            getTeamBadgeUrl(data.events[0].awayId, data.events[0].homeId)

        }

    }

    private fun getTeamBadgeUrl(teamAwayId: String, teamHomeId: String) {
        GlobalScope.launch(context.main) {
            val teamAway = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailTeam(teamAwayId)).await(),
                DetailTeamResponse::class.java
            )

            val teamHome = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailTeam(teamHomeId)).await(),
                DetailTeamResponse::class.java
            )

            Log.d("DetailMatchPresenter", "away : ${teamAway.teams[0]}")
            Log.d("DetailMatchPresenter", "home : ${teamHome.teams[0]}")

            view.loadImage(teamHome.teams[0].badge, teamAway.teams[0].badge)

        }
    }
}