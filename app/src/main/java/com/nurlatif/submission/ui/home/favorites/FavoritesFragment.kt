package com.nurlatif.submission.ui.home.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.nurlatif.submission.R
import com.nurlatif.submission.database.Favorite
import com.nurlatif.submission.database.database
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.ui.leaguehighlight.match.MatchAdapter
import com.nurlatif.submission.ui.matchDetail.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity


class FavoritesFragment : Fragment() {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: FavoritesAdapter
    private lateinit var gson: Gson
    private lateinit var request: ApiRepository


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        request = ApiRepository()
        gson = Gson()

        adapter = FavoritesAdapter(requireContext(), events, request, gson) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ITEM_KEY to it.eventId,
                DetailMatchActivity.ITEM_NAME to it.eventName
            )
        }

        favorites_recyclerview.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        events.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())

            events.addAll(favorite.map {
                Event(
                    eventId = it.matchId,
                    eventType = null,
                    eventDate = it.matchDate,
                    teamHomeId = it.homeId,
                    teamHomeName = it.homeTeam,
                    teamHomeScore = it.homeScore,
                    teamAwayId = it.awayId,
                    teamAwayName = it.awayTeam,
                    teamAwayScore = it.awayScore
                )
            })
            adapter.notifyDataSetChanged()
        }
    }


}