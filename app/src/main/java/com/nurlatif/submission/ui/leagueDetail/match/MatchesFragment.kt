package com.nurlatif.submission.ui.leagueDetail.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R.layout.fragment_leagues_matches
import com.nurlatif.submission.model.League
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import com.nurlatif.submission.ui.matchDetail.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_leagues_matches.*
import org.jetbrains.anko.support.v4.startActivity


class MatchesFragment : Fragment(), MatchesView {
    private lateinit var presenter: MatchesPresenter
    private lateinit var lastMatchAdapter: MatchesAdapter
    private lateinit var nextMatchAdapter: MatchesAdapter
    private var lastMatches: MutableList<Event> = mutableListOf()
    private var nextMatches: MutableList<Event> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchesPresenter(this, request, Gson())


        lastMatchAdapter = MatchesAdapter(requireContext(), lastMatches, request, gson) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ITEM_KEY to it.eventId,
                DetailMatchActivity.ITEM_NAME to it.eventName,
                DetailMatchActivity.ITEM_TYPE to "last_match"
            )
        }

        nextMatchAdapter = MatchesAdapter(requireContext(), nextMatches, request, gson) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ITEM_KEY to it.eventId,
                DetailMatchActivity.ITEM_NAME to it.eventName,
                DetailMatchActivity.ITEM_TYPE to "next_match"
            )
        }

        rv_league_last_matches.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rv_league_last_matches.adapter = lastMatchAdapter

        rv_league_next_matches.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rv_league_next_matches.adapter = nextMatchAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_leagues_matches, container, false)
    }

    override fun onResume() {
        super.onResume()

        val leagueId =
            requireActivity().intent.extras?.getParcelable<League>(LeagueDetailActivity.ITEM_KEY)?.id.toString()

        presenter.getLastMatch(leagueId)
        presenter.getNextMatch(leagueId)
    }

    override fun loadDataLastMatches(data: List<Event>) {
        lastMatches.clear()
        lastMatches.addAll(data)
        lastMatchAdapter.notifyDataSetChanged()
    }

    override fun loadNextLastMatches(data: List<Event>) {
        nextMatches.clear()
        nextMatches.addAll(data)
        nextMatchAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        rv_league_last_matches?.visibility = View.INVISIBLE
        rv_league_next_matches?.visibility = View.INVISIBLE
        pb_last_matches?.visibility = View.VISIBLE
        pb_next_matches?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rv_league_last_matches?.visibility = View.VISIBLE
        rv_league_next_matches?.visibility = View.VISIBLE
        pb_last_matches?.visibility = View.GONE
        pb_next_matches?.visibility = View.GONE
    }


}