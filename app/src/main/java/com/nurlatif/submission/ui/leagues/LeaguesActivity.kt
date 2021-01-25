package com.nurlatif.submission.ui.leagues

import android.support.v7.app.AppCompatActivity
import com.nurlatif.submission.R.layout.activity_leagues
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.nurlatif.submission.model.League
import com.nurlatif.submission.ui.leagueDetail.LeagueDetailActivity
import kotlinx.android.synthetic.main.activity_leagues.*
import org.jetbrains.anko.startActivity
import com.nurlatif.submission.R.array.*
import com.nurlatif.submission.ui.favorites.FavoritesActivity
import com.nurlatif.submission.R.menu.home_menu
import com.nurlatif.submission.R.id.favorites_menu

class LeaguesActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_leagues)

        initData()

        rv_leagues.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        rv_leagues.adapter = LeaguesAdapter(this, leagues) {
            startActivity<LeagueDetailActivity>(LeagueDetailActivity.ITEM_KEY to it)
        }
    }

    private fun initData() {
        val id = resources.getIntArray(leaguesId)
        val name = resources.getStringArray(leaguesName)
        val desc = resources.getStringArray(leaguesDesc)
        val image = resources.obtainTypedArray(leaguesImage)

        leagues.clear()
        for (i in id.indices) {
            leagues.add(League(id[i], name[i], desc[i], image.getResourceId(i, 0)))
        }

        image.recycle()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == favorites_menu) {
            startActivity<FavoritesActivity>()
        } else {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(home_menu, menu)
        return true
    }
}