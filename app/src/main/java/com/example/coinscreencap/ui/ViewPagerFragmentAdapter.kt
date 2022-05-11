package com.example.coinscreencap.ui

import android.graphics.drawable.Icon
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifeCycle) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()


    private val size: Int
        get() = fragments.size

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragments(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)

    }

    fun getItemTitle(position: Int): String {
        return titles[position]
    }


}