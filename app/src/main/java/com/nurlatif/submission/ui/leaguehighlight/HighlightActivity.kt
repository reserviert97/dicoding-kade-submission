package com.nurlatif.submission.ui.leaguehighlight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.nurlatif.submission.R
import kotlinx.android.synthetic.main.activity_highlight.*
import com.nurlatif.submission.R.layout.activity_highlight
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.ui.searchMatch.SearchMatchActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity

class HighlightActivity : AppCompatActivity(), AnkoLogger, HighlightView {
    private lateinit var presenter: HighlightPresenter
    companion object {
        val ITEM_KEY = "league_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_highlight)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "League Highlight"

        val leagueId = intent.extras?.getString(ITEM_KEY)

        val request = ApiRepository()
        val gson = Gson()
        presenter = HighlightPresenter(this, request, gson)
        presenter.getLeagueDetail(leagueId!!)

        matchViewPager.adapter = PagerAdapter(supportFragmentManager)
        matchTab.setupWithViewPager(matchViewPager)

        debug("HighlightActivity Successfully pass data : ${leagueId}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.searchMenu) {
            startActivity<SearchMatchActivity>()
        } else {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.highlight_menu, menu)
        return true
    }

    override fun loadData(data: LeagueResponse) {

        data.trophy.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(leagueBanner) }
        data.fanArt1.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(subBanner1) }
        data.fanArt2.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(subBanner2) }
        data.fanArt3.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(subBanner3) }

        leagueName.text = data.leagueNickname.toString()
        leagueCountry.text = data.country.toString()
        leagueSeason.text = data.currentSeason.toString()

    }

}