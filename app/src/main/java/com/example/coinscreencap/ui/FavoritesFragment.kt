package com.example.coinscreencap.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.FragmentFavoritesBinding
import com.example.coinscreencap.shared.model.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var mBinding: FragmentFavoritesBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private val favAdapter = FavoritesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFavRecyclerView()
        observe(viewModel.coin) {
            Log.d("FavoritesFragment", it.toString())
        }
    }


    private fun initFavRecyclerView() {
        mBinding.favReycler.apply {
            adapter = favAdapter
            layoutManager = LinearLayoutManager(requireContext())
            favAdapter.setOnFavClickedListener { viewModel.toggleFavorite(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFavorites().collect {
                favAdapter.submitList(it)
            }
        }
    }
}