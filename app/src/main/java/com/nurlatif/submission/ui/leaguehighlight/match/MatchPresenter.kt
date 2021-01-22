package com.nurlatif.submission.ui.leaguehighlight.match

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


interface MatchView {
    fun loadData(data: List<Event>)
}

class MatchPresenter(
    private val view: MatchView,
    private val api: ApiRepository,
    private val gson: Gson,
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
}