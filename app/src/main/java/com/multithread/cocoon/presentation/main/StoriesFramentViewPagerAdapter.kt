package com.multithread.cocoon.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.multithread.cocoon.presentation.favorite.FavoriteFragment
import com.multithread.cocoon.presentation.topstories.TopStoriesFragment

class StoriesFragmentViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(
        fragmentManager,
        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    private val fragments = listOf(
        TopStoriesFragment.newInstance(),
        FavoriteFragment.newInstance()
    )

    override fun getCount() = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> "Top Stories"
        1 -> "Favorites"
        else -> super.getPageTitle(position)
    }

}