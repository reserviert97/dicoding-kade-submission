package com.nurlatif.submission.ui.leagueDetail

import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailLeagueResponse
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.network.TheSportDBApi
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LeagueDetailView {
    fun loadData(data: LeagueResponse)
    fun showLoading()
    fun hideLoading()
}

class LeagueDetailPresenter(
    private val view: LeagueDetailView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueDetail(leagueId: String) = GlobalScope.launch(context.main) {
        view.showLoading()
        val data = gson.fromJson(
            api.doRequest(TheSportDBApi.getDetailLeague(leagueId)).await(),
            DetailLeagueResponse::class.java
        )
        view.loadData(data.leagues[0])
        view.hideLoading()
    }


}