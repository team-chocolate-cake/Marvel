package com.chocolatecake.marvel.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentHomeBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter
import com.chocolatecake.marvel.ui.home.model.HomeItem
import com.chocolatecake.marvel.ui.home.view_model.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    lateinit var adapter: HomeAdapter
    override val viewModel: HomeViewModel by viewModels()

    override val layoutIdFragment: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        updateAdapterItems()
        handelNavigation()
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
        viewModel.events.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(HomeItem.EventsItem(it))
            }
        }
        viewModel.series.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(HomeItem.SeriesItem(it))
            }
        }
        viewModel.comics.observe(viewLifecycleOwner){ status ->
            status.toData()?.forEach{
                adapter.setItem(HomeItem.ComicItem(it))
            }
        }
    }

    private fun handelNavigation() {
        viewModel.eventId.observe(viewLifecycleOwner) {
            if (it != null) {
                //ToDo:Navigate To Event Details With It
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.seriesId.observe(viewLifecycleOwner) {
            if (it != null) {
                //ToDo:Navigate To Series Details With It
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.comicId.observe(viewLifecycleOwner){
            if(it != null){
                //ToDo:Navigate To Comic Details With It
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.navigateToSeries.observe(viewLifecycleOwner){
            if (it) {
                //ToDo:Navigate To Series
                Toast.makeText(requireContext(), "Navigate To Series", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.navigateToComic.observe(viewLifecycleOwner){
            if (it) {
                //ToDo:Navigate To Comics
                Toast.makeText(requireContext(), "Navigate To Comics", Toast.LENGTH_SHORT).show()
            }
        }
    }

}