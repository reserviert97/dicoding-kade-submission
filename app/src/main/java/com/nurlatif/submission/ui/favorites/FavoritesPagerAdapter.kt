package com.nurlatif.submission.ui.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nurlatif.submission.ui.favorites.match.NextMatchesFragment
import com.nurlatif.submission.ui.favorites.match.LastMatchesFragment
import com.nurlatif.submission.ui.favorites.team.FavoritesTeamFragment

class FavoritesPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LastMatchesFragment()
            1 -> NextMatchesFragment()
            else -> FavoritesTeamFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> "Team"
        }
    }

}