package com.nurlatif.submission.ui.leaguehighlight.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.ui.leaguehighlight.HighlightActivity
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), MatchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val leagueId =  requireActivity().intent.extras?.getString(HighlightActivity.ITEM_KEY)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getNextMatch(leagueId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MatchAdapter(requireContext(), events) {
            toast(it.eventId.toString())
        }
        nextMatchRv.adapter = adapter
    }

    override fun loadData(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }


}