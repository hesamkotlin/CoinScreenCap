package com.example.coinscreencap.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.coinscreencap.R
import com.example.coinscreencap.databinding.FragmentFavoritesBinding
import com.example.coinscreencap.shared.model.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var mBinding : FragmentFavoritesBinding
    private val viewModel : FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.coin){
            Log.d("FavoritesFragment", it.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }


}