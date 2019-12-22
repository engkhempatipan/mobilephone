package com.mvpclean.scb.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mvpclean.scb.ui.favoritelist.fragment.FavoriteFragment
import com.mvpclean.scb.ui.mobilelist.fragment.MobilesFragment

class SimpleFragmentPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                return MobilesFragment.newInstance()
            }
            else -> FavoriteFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "MOBILE LIST"
            }
            else -> "FAVORITE LIST"
        }

    }

    override fun getCount(): Int {
        return 2
    }
}