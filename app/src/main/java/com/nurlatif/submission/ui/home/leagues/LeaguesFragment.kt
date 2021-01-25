package com.nurlatif.submission.ui.home.leagues

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R
import com.nurlatif.submission.model.League
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import kotlinx.android.synthetic.main.fragment_leagues.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class LeaguesFragment : Fragment(), AnkoLogger {

    private var items: MutableList<League> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initData()

        league_recyclerview.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        league_recyclerview.adapter = LeaguesAdapter(requireContext(), items){
            toast(it.name)
            startActivity<LeagueDetailActivity>(LeagueDetailActivity.ITEM_KEY to it)
        }

        super.onActivityCreated(savedInstanceState)
    }

    private fun initData(){
        val id = resources.getIntArray(R.array.leaguesId)
        val name = resources.getStringArray(R.array.leaguesName)
        val desc = resources.getStringArray(R.array.leaguesDesc)
        val image = resources.obtainTypedArray(R.array.leaguesImage)

        items.clear()
        for (i in id.indices) {
            items.add(League(id[i], name[i], desc[i], image.getResourceId(i, 0)))
        }

        debug("[LeaguesActivity] total leagues data : ${items.size}")
        image.recycle()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    companion object
}