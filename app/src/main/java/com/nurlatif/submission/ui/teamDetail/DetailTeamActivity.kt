package com.nurlatif.submission.ui.teamDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nurlatif.submission.R
import com.nurlatif.submission.R.layout.activity_detail_team
import com.nurlatif.submission.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    private var menuItem: Menu? = null

    companion object {
        const val ITEM_KEY = "team_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_team)

        val team = intent.extras?.getParcelable<Team>(ITEM_KEY)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = team?.name

        team?.let {
            Picasso.get().load(team.badge).error(R.drawable.ic_broken)
                .placeholder(R.drawable.loading_animation)
                .into(img_logo)

            tv_team_name.text = team.name
            tv_stadion.text = team.stadium
            tv_description.text = team.description
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_favorite_menu, menu)
        menuItem = menu
//        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }
}