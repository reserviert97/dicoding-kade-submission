package com.nurlatif.submission.ui.leagueDetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nurlatif.submission.ui.leagueDetail.match.MatchesFragment

class DetailPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MatchesFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "Matches"
            1 -> "Standings"
            else -> "Teams"
        }
    }

}