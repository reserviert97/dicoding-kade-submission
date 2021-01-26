package com.nurlatif.submission.ui.leagueDetail.standing

import com.google.gson.Gson
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


interface StandingsView {
    fun loadData(data: List<Standing>)
    fun showLoading()
    fun hideLoading()
}

class StandingsPresenter(
    private val view: StandingsView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueStandings(leagueId: String) = GlobalScope.launch(context.main) {
        view.showLoading()
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getLeagueStandings(leagueId)).await(),
            StandingsResponse::class.java
        )

        if (!data.table.isNullOrEmpty()) {
            view.loadData(data.table)
        }
        view.hideLoading()
    }


}