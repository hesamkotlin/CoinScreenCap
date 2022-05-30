package com.example.coinscreencap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.FragmentContainerBinding
import com.google.android.material.tabs.TabLayoutMediator


class ContainerFragment : Fragment() {
    private lateinit var mBinding: FragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentContainerBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewpagerWithTabLayout()
    }

    private fun setupViewpagerWithTabLayout() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.apply {
            addFragments(MainFragment(), getString(R.string.home_key))
            addFragments(FavoritesFragment(), getString(R.string.fav_key))
        }

        with(mBinding) {
            viewPager.adapter = adapter

            tabLayout.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    tabLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_main_fragment)
                    tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_favorites)
                }
            })

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = adapter.getItemTitle(position)
            }.attach()
        }
    }

}