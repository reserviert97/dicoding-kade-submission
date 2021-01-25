package com.nurlatif.submission.ui.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nurlatif.submission.ui.favorites.match.NextMatchFragment
import com.nurlatif.submission.ui.favorites.match.LastMatchFragment

class PagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LastMatchFragment()
            else -> NextMatchFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Last Match"
            else -> "Next Match"
        }
    }

}