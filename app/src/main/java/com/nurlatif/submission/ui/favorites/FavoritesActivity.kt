package com.nurlatif.submission.ui.favorites

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nurlatif.submission.R.layout.activity_favorites
import com.nurlatif.submission.R.string.favorites
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_favorites)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(favorites)
        view_pager.adapter = FavoritesPagerAdapter(supportFragmentManager)
        tab_favorites.setupWithViewPager(view_pager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}