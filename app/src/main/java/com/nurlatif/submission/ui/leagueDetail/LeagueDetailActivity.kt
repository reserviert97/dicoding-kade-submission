package com.nurlatif.submission.ui.leagueDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*
import com.nurlatif.submission.R.layout.activity_detail_league
import com.nurlatif.submission.model.League
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.ui.searchMatch.SearchMatchActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView, AnkoLogger {
    private lateinit var presenter: LeagueDetailPresenter

    companion object {
        const val ITEM_KEY = "league_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_league)

        val league = intent.extras?.getParcelable<League>(ITEM_KEY)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = league?.name

        val request = ApiRepository()
        val gson = Gson()

        presenter = LeagueDetailPresenter(this, request, gson)
        presenter.getLeagueDetail(league?.id.toString())

        view_pager.adapter = DetailPagerAdapter(supportFragmentManager)
        tab_detail_league.setupWithViewPager(view_pager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.search_match_menu){
            startActivity<SearchMatchActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadData(data: LeagueResponse) {

        data.trophy.let {
            Picasso.get().load(it).error(R.drawable.ic_broken)
                .placeholder(R.drawable.loading_animation).into(img_trophy)
        }

        tv_league_name.text = data.leagueNickname.toString()
        tv_country.text = data.country.toString()
        tv_season.text = data.currentSeason.toString()
        tv_gender.text = data.gender.toString()
        tv_type.text = data.type

    }

    override fun showLoading() {
        pb_detail_league.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_league.visibility = View.GONE
    }
}