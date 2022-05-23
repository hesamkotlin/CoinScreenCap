package com.example.coinscreencap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinscreencap.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCoins().collectLatest {
                coinAdapter.submitData(it)
            }
        }
    }
    fun initRecyclerView(){
        mBinding.coinRecycler.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }
}




