package com.nurlatif.submission.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nurlatif.submission.R
import com.nurlatif.submission.ui.home.favorites.FavoritesFragment
import com.nurlatif.submission.ui.home.leagues.LeaguesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.leagues-> {
                    loadLeaguesFragment(savedInstanceState)
                }

                R.id.favorites -> {
                     loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.leagues
    }

    private fun loadLeaguesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeaguesFragment(), LeaguesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }
}