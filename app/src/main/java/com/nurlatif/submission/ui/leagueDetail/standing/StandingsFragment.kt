package com.nurlatif.submission.ui.leagueDetail.standing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R.layout.fragment_standings
import com.nurlatif.submission.model.League
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Standing
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import kotlinx.android.synthetic.main.fragment_standings.*

class StandingsFragment : Fragment(), StandingsView {

    private lateinit var standingsPresenter: StandingsPresenter
    private lateinit var adapter: StandingsAdapter
    private var standings = mutableListOf<Standing>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        standingsPresenter = StandingsPresenter(this, request, gson)

        adapter = StandingsAdapter(requireContext(), standings)
        rv_standings.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_standings, container, false)
    }

    override fun onResume() {
        super.onResume()
        val leagueId =
            requireActivity().intent.extras?.getParcelable<League>(LeagueDetailActivity.ITEM_KEY)?.id.toString()
        standingsPresenter.getLeagueStandings(leagueId)
    }

    override fun loadData(data: List<Standing>) {
        standings.clear()
        standings.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_standings.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_standings.visibility = View.GONE
    }

}