package com.nurlatif.submission.ui.matchDetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.nurlatif.submission.R.id.add_to_favorite
import com.nurlatif.submission.R.layout.activity_detail_match
import com.nurlatif.submission.R.menu.detail_match_menu
import com.nurlatif.submission.R.drawable.ic_broken
import com.nurlatif.submission.R.drawable.loading_animation
import com.nurlatif.submission.R.drawable.ic_added_to_favorites
import com.nurlatif.submission.R.drawable.ic_add_to_favorites
import com.nurlatif.submission.database.MatchEntity
import com.nurlatif.submission.database.database
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.MatchResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var match: MatchResponse
    private var menuItem: Menu? = null
    private lateinit var id: String
    private lateinit var matchType: String
    private var isFavorite: Boolean = false

    companion object {
        const val ITEM_KEY = "match_id"
        const val ITEM_NAME = "match_name"
        const val ITEM_TYPE = "match_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_match)
        val matchId = intent.extras?.getString(ITEM_KEY)
        val matchName = intent.extras?.getString(ITEM_NAME)
        val type = intent?.extras?.getString(ITEM_TYPE)

        matchId?.let { id = it }
        type?.let { matchType = it }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = matchName

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson, this)

        presenter.getMatchDetail(id)
        presenter.checkFavorite(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_match_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

        match = data

        presenter.getTeamBadgeUrl(data.awayId, data.homeId)
    }

    override fun loadImage(homeBadge: String, awayBadge: String) {
        homeBadge.let {
            Picasso.get().load(it).error(ic_broken)
                .placeholder(loading_animation).into(homeTeamLogo)
        }
        awayBadge.let {
            Picasso.get().load(it).error(ic_broken)
                .placeholder(loading_animation).into(AwayLogo)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    MatchEntity.TABLE_FAVORITE_MATCH,
                    MatchEntity.MATCH_ID to match.matchId,
                    MatchEntity.DATE to match.matchDate,
                    MatchEntity.TEAM_HOME to match.homeTeam,
                    MatchEntity.TEAM_HOME_ID to match.homeId,
                    MatchEntity.TEAM_HOME_SCORE to match.homeScore,
                    MatchEntity.AWAY_TEAM to match.awayTeam,
                    MatchEntity.AWAY_TEAM_ID to match.awayId,
                    MatchEntity.AWAY_TEAM_SCORE to match.awayScore,
                    MatchEntity.MATCH_TYPE to matchType
                )
            }

            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    MatchEntity.TABLE_FAVORITE_MATCH,
                    "(${MatchEntity.MATCH_ID} = {id})",
                    "id" to id
                )
            }
            toast("Removed to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    override fun setFavoriteState(state: Boolean) {
        isFavorite = state
    }

}