package com.nurlatif.submission.ui.matchDetail

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface DetailMatchView {
    fun loadData(data: MatchResponse)
    fun loadImage(homeBadge: String, awayBadge: String)
}

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val api: ApiRepository,
    private val gson: Gson
) {

    fun getMatchDetail(matchId: String) {

        doAsync {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailMatch(matchId)),
                DetailMatchResponse::class.java
            )

            Log.d("DetailMatchPresenter", "${data.events[0]}")

            uiThread {
                view.loadData(data.events[0])
            }

            getTeamBadgeUrl(data.events[0].awayId, data.events[0].homeId)

        }

    }

    fun getTeamBadgeUrl(teamAwayId: String, teamHomeId: String) {
        doAsync {
            val teamAway = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailTeam(teamAwayId)),
                DetailTeamResponse::class.java
            )

            val teamHome = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailTeam(teamHomeId)),
                DetailTeamResponse::class.java
            )

            Log.d("DetailMatchPresenter", "away : ${teamAway.teams[0]}")
            Log.d("DetailMatchPresenter", "home : ${teamHome.teams[0]}")

            uiThread {
                view.loadImage(teamHome.teams[0].badge, teamAway.teams[0].badge )
            }

        }
    }
}