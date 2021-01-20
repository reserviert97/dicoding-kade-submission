package com.nurlatif.submission.ui.matchDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.MatchResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView{

    private lateinit var presenter: DetailMatchPresenter

    companion object {
        const val ITEM_KEY = "match_id"
        const val ITEM_NAME = "match_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        val matchId = intent.extras?.getString(ITEM_KEY)
        val matchName = intent.extras?.getString(ITEM_NAME)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = matchName


        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)

        presenter.getMatchDetail(matchId!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun loadData(data: MatchResponse) {
        matchDate.text = data.matchDate
        homeName.text = data.homeTeam
        homeScore.text = data.homeScore
        home_goals.text = data.homeGoal
        home_red_card.text = data.homeRedCards
        home_yellow_card.text = data.homeYellowCards
        home_formation.text = data.homeFormation
        home_shot.text = data.homeShots

        awayName.text = data.awayTeam
        awayScore.text = data.awayScore
        away_goals.text = data.awayGoal
        away_red_card.text = data.awayRedCards
        away_yellow_card.text = data.awayYellowCards
        away_formation.text = data.awayFormation
        away_shot.text = data.awayShots
    }

    override fun loadImage(homeBadge: String, awayBadge: String) {
        homeBadge.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(homeTeamLogo) }
        awayBadge.let { Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(AwayLogo) }
    }
}