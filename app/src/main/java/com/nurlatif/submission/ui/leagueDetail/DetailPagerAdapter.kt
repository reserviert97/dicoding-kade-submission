package com.nurlatif.submission.ui.leagueDetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nurlatif.submission.ui.leagueDetail.match.MatchesFragment
import com.nurlatif.submission.ui.leagueDetail.standing.StandingsFragment
import com.nurlatif.submission.ui.leagueDetail.team.TeamsFragment

class DetailPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    override fun getCount() = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MatchesFragment()
            1 -> StandingsFragment()
            else -> TeamsFragment()
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