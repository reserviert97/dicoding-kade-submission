package com.nurlatif.submission.ui.teamDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.nurlatif.submission.R
import com.nurlatif.submission.R.layout.activity_detail_team
import com.nurlatif.submission.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private var menuItem: Menu? = null
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var team: Team
    private var isFavorite: Boolean = false

    companion object {
        const val ITEM_KEY = "team_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_team)

        team = intent?.extras?.getParcelable(ITEM_KEY)!!

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = team.name

        presenter = DetailTeamPresenter(this, applicationContext)

        team.let {
            Picasso.get().load(it.badge).error(R.drawable.ic_broken)
                .placeholder(R.drawable.loading_animation)
                .into(img_logo)

            tv_team_name.text = it.name
            tv_stadion.text = it.stadium
            tv_description.text = it.description
            presenter.checkFavorite(it.id!!)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) presenter.removeFromFavorite(team.id!!) else presenter.addToFavorite(team)
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun setFavoriteState(state: Boolean) {
        isFavorite = state
    }
}