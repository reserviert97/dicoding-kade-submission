package com.nurlatif.submission.ui.leaguehighlight

import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailLeagueResponse
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.network.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface HighlightView {
    fun loadData(data: LeagueResponse)
}

class HighlightPresenter(private val view: HighlightView, private val api: ApiRepository, private val gson: Gson) {

    fun getLeagueDetail(leagueId: String) {

        doAsync {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailLeague(leagueId)),
                DetailLeagueResponse::class.java
            )

            uiThread {
                view.loadData(data.leagues[0])
            }

        }
    }
}