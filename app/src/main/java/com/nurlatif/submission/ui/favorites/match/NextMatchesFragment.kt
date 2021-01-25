package com.nurlatif.submission.ui.favorites.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R.layout.fragment_next_match
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.ui.matchDetail.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity

class NextMatchesFragment : Fragment(), MatchView {

    private var matches: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapterFavorites: FavoritesMatchAdapter
    private lateinit var gson: Gson
    private lateinit var request: ApiRepository

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        request = ApiRepository()
        gson = Gson()
        presenter = MatchPresenter(this, request, gson, requireContext())

        adapterFavorites = FavoritesMatchAdapter(requireContext(), matches, request, gson) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ITEM_KEY to it.eventId,
                DetailMatchActivity.ITEM_NAME to it.eventName,
                DetailMatchActivity.ITEM_TYPE to "next_match"
            )
        }

        rv_favorites_next_match.adapter = adapterFavorites
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(fragment_next_match, container, false)
    }


    override fun loadData(data: List<Event>) {
        matches.clear()
        matches.addAll(data)
        adapterFavorites.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.getNextMatchFromLocal()
    }


}