package com.nurlatif.submission.ui.leagueDetail.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R.layout.fragment_league_teams
import com.nurlatif.submission.model.League
import com.nurlatif.submission.model.Team
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import kotlinx.android.synthetic.main.fragment_league_teams.*
import org.jetbrains.anko.support.v4.toast

class TeamsFragment : Fragment(), TeamsView {

    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private var teams = mutableListOf<Team>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        adapter = TeamsAdapter(requireContext(), teams){
            toast("Clicked at ${it.name}")
        }

        rv_league_teams.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_league_teams, container, false)
    }

    override fun onResume() {
        super.onResume()
        val leagueId =
            requireActivity().intent.extras?.getParcelable<League>(LeagueDetailActivity.ITEM_KEY)?.id.toString()
        presenter.getLeagueTeams(leagueId)
    }

    override fun loadData(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_teams?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_teams?.visibility = View.GONE
    }


}