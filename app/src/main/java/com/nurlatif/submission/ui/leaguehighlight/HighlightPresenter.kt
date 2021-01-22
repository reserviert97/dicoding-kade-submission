package com.nurlatif.submission.ui.leaguehighlight

import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailLeagueResponse
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.network.TheSportDBApi
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface HighlightView {
    fun loadData(data: LeagueResponse)
}

class HighlightPresenter(
    private val view: HighlightView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueDetail(leagueId: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.getDetailLeague(leagueId)).await(),
                DetailLeagueResponse::class.java
            )

            view.loadData(data.leagues[0])

        }
    }
}