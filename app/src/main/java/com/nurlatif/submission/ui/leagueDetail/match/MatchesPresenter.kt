package com.nurlatif.submission.ui.leagueDetail.match

import android.content.Context
import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.network.EventsResponse
import com.nurlatif.submission.network.TheSportDBApi
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


interface MatchesView {
    fun loadDataLastMatches(data: List<Event>)
    fun loadNextLastMatches(data: List<Event>)
    fun showLoading()
    fun hideLoading()
}

class MatchesPresenter(
    private val view: MatchesView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLastMatch(leagueId: String) = GlobalScope.launch(context.main) {
        view.showLoading()
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getPastMatch(leagueId)).await(),
            EventsResponse::class.java
        )

        view.loadDataLastMatches(data.events)
        view.hideLoading()
    }


    fun getNextMatch(leagueId: String) = GlobalScope.launch(context.main) {
        view.showLoading()
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getNextMatch(leagueId)).await(),
            EventsResponse::class.java
        )

        view.loadNextLastMatches(data.events)
        view.hideLoading()
    }


}