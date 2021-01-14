package com.nurlatif.submission.ui.leagues

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.nurlatif.submission.R.array.*
import com.nurlatif.submission.R.layout.activity_leagues
import com.nurlatif.submission.model.Item
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import kotlinx.android.synthetic.main.activity_leagues.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class LeaguesActivity : AppCompatActivity(), AnkoLogger {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_leagues)
        initData()

        league_recyclerview.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        league_recyclerview.adapter = LeaguesAdapter(this, items){
            toast(it.name)
            startActivity<LeagueDetailActivity>(LeagueDetailActivity.ITEM_KEY to it)
        }


    }

    private fun initData(){
        val id = resources.getIntArray(leaguesId)
        val name = resources.getStringArray(leaguesName)
        val desc = resources.getStringArray(leaguesDesc)
        val image = resources.obtainTypedArray(leaguesImage)

        items.clear()
        for (i in id.indices) {
            items.add(Item(id[i], name[i], desc[i], image.getResourceId(i, 0)))
        }

        debug("[LeaguesActivity] total leagues data : ${items.size}")
        image.recycle()
    }
}