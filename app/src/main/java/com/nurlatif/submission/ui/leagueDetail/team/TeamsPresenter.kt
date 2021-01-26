package com.nurlatif.submission.ui.leagueDetail.team

import com.google.gson.Gson
import com.nurlatif.submission.model.Team
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.ListTeamResponse
import com.nurlatif.submission.network.TheSportDBApi
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


interface TeamsView {
    fun loadData(data: List<Team>)
    fun showLoading()
    fun hideLoading()
}

class TeamsPresenter(
    private val view: TeamsView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueTeams(leagueId: String) = GlobalScope.launch(context.main) {
        view.showLoading()
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getLeagueTeams(leagueId)).await(),
            ListTeamResponse::class.java
        )

        if (!data?.teams.isNullOrEmpty()) {
            view.loadData(data.teams)
        }
        view.hideLoading()
    }


}