package com.chocolatecake.marvel.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentHomeBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter
import com.chocolatecake.marvel.util.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    lateinit var adapter: HomeAdapter
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        updateAdapterItems()
    }


    private fun setAdapter() {
        val layoutManager = GridLayoutManager(
            requireContext(),
            2
        )

        binding.recyclerViewHome.layoutManager = layoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 || position == 1) {
                    2
                } else {
                    1
                }
            }
        }

        adapter = HomeAdapter(
            mutableListOf(
                HomeItem.EventsItem(emptyList()),
                HomeItem.SeriesItem(emptyList()),
            ), viewModel
        )

        binding.recyclerViewHome.adapter = adapter
    }


    private fun updateAdapterItems() {
        viewModel.events.observeNonNull(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(HomeItem.EventsItem(it))
            }
        }
        viewModel.series.observeNonNull(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(HomeItem.SeriesItem(it))
            }
        }
        viewModel.comics.observeNonNull(viewLifecycleOwner){ status ->
            status.toData()?.forEach{
                adapter.setItem(HomeItem.ComicItem(it))
            }
        }
    }
}