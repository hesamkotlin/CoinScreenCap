package com.example.coinscreencap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinscreencap.databinding.FragmentMainBinding
import com.example.coinscreencap.shared.model.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mBinding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val coinAdapter = CoinAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getCoins().collect {
                coinAdapter.submitList(it)
            }
        }

    }

    private fun initRecyclerView() {
        mBinding.coinRecycler.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext())
            coinAdapter.setOnItemClickListener{ viewModel.navigateToDetail(it) }
            coinAdapter.setOnFavClickedListener { viewModel.toggleFavorite(it) }
        }
    }

    private fun navigateToDetail(coinId :String) {
        val navDirection = ContainerFragmentDirections.navigateToDetail(coinId)
        findNavController().navigate(navDirection)
    }
}




