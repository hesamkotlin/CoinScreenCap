package com.example.coinscreencap.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.FragmentMainBinding
import com.example.coinscreencap.shared.model.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mBinding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val coinAdapter = CoinAdapter()
    var swipeRefreshLayout: SwipeRefreshLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToSplashFragmentIfNeeded()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewmodel = viewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateCryptos()
        initRecyclerView()
        observe(viewModel.navigateToDetail) { navigateToDetail(it) }

        swipeRefreshLayout = mBinding.swipeRefreshLayout
    }


    private fun initRecyclerView() {
        mBinding.coinRecycler.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext())
            coinAdapter.setOnItemClickListener { viewModel.navigateToDetail(it) }
            coinAdapter.setOnFavClickedListener { viewModel.toggleFavorite(it) }
        }
    }

    private fun navigateToDetail(coinId: String) {
        val navDirection = ContainerFragmentDirections.navigateToDetail(coinId)
        findNavController().navigate(navDirection)
    }

    private fun navigateToSplashFragmentIfNeeded() {
        if (!viewModel.splashSeen) {
            viewModel.splashSeen = true
            findNavController().navigate(R.id.splashFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getCoins().collect {
                coinAdapter.submitList(it)
            }
        }

        swipeRefreshLayout!!.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                viewModel.updateCryptos()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                    viewModel.getCoins().collect {
                        coinAdapter.submitList(it)
                    }

                }
                swipeRefreshLayout!!.isRefreshing = false
            }
        }
    }
}




