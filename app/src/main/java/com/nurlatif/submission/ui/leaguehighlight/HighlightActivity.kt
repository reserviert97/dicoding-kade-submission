package com.nurlatif.submission.ui.leaguehighlight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_highlight.*
import com.nurlatif.submission.R.layout.activity_highlight
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.LeagueResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.league_grid_item.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class HighlightActivity : AppCompatActivity(), AnkoLogger, HighlightView {
    private lateinit var presenter: HighlightPresenter
    companion object {
        val ITEM_KEY = "league_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_highlight)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val leagueId = intent.extras?.getString(ITEM_KEY)

        val request = ApiRepository()
        val gson = Gson()
        presenter = HighlightPresenter(this, request, gson)

        presenter.getLeagueDetail(leagueId!!)


        debug("HighlightActivity Successfully pass data : ${leagueId}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun loadData(data: LeagueResponse) {

        data.trophy.let { Picasso.get().load(it).into(leagueBanner) }
        data.fanArt1.let { Picasso.get().load(it).into(subBanner1) }
        data.fanArt2.let { Picasso.get().load(it).into(subBanner2) }
        data.fanArt3.let { Picasso.get().load(it).into(subBanner3) }

        leagueName.text = data.leagueNickname.toString()
        leagueCountry.text = data.country.toString()
        leagueSeason.text = data.country.toString()

    }

}