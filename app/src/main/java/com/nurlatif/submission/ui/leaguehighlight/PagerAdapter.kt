package com.nurlatif.submission.ui.leaguehighlight

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    private val pages = listOf(
        LastMatchFragment(),
        NextMatchFragment()
    )

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Last Match"
            else -> "Next Match"
        }
    }

}