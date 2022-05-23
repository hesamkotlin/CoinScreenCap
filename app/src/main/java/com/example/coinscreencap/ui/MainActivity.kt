package com.example.coinscreencap.ui

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewpagerWithTabLayout()
    }

    private fun setupViewpagerWithTabLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.apply {
            addFragments(MainFragment(), getString(R.string.home_key))
            addFragments(FavoritesFragment(), getString(R.string.fav_key))
        }
        mBinding.viewPager.adapter = adapter

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

