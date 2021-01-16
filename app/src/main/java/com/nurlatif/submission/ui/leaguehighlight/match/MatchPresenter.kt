package com.nurlatif.submission.ui.leaguehighlight.match

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


interface MatchView {
    fun loadData(data: List<Event>)
}

class MatchPresenter(private val view: MatchView, private val api: ApiRepository, private val gson: Gson) : AnkoLogger {

    fun getLastMatch(leagueId: String) {

        doAsync {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getPastMatch(leagueId)),
                EventsResponse::class.java
            )

            uiThread {
                view.loadData(data.events)
                debug("MatchPresenter, ${data.events}")
            }

        }

    }

    fun getNextMatch(leagueId: String) {

        doAsync {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getNextMatch(leagueId)),
                EventsResponse::class.java
            )

            uiThread {
                view.loadData(data.events)
                debug("MatchPresenter, ${data.events}")
            }

        }

    }
}