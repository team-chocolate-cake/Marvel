package com.chocolatecake.marvel.ui.series_details.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeriesDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.core.factory.ViewModeFactory
import com.chocolatecake.marvel.ui.series_details.SeriesDetailsItem
import com.chocolatecake.marvel.ui.series_details.viewModel.SeriesDetailsViewModel
import com.chocolatecake.marvel.ui.series_details.adapters.SeriesDetailsAdapter

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    private val args: SeriesDetailsFragmentArgs by navArgs()
    override val viewModel: SeriesDetailsViewModel by viewModels{ ViewModeFactory(args.seriesId)}
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_details
    lateinit var seriesDetailsAdapter: SeriesDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        updateItems()
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFragmentSeriesDetails.layoutManager = layoutManager

        seriesDetailsAdapter = SeriesDetailsAdapter(
            mutableListOf(
                SeriesDetailsItem.CharactersItem(emptyList()),
                SeriesDetailsItem.ComicsItem(emptyList()),
                SeriesDetailsItem.EventsItem(emptyList()),
                SeriesDetailsItem.SeriesItem(null),
            ), viewModel
        )
        binding.recyclerViewFragmentSeriesDetails.adapter = seriesDetailsAdapter
    }

    private fun updateItems() {
        viewModel.events.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                seriesDetailsAdapter.setItem(SeriesDetailsItem.EventsItem(it))
            }
        }

        viewModel.comics.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                seriesDetailsAdapter.setItem(SeriesDetailsItem.ComicsItem(it))
            }
        }

        viewModel.series.observe(viewLifecycleOwner) {
            it.toData()?.let {
                seriesDetailsAdapter.setItem(SeriesDetailsItem.SeriesItem(it))
            }
        }

        viewModel.characters.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                seriesDetailsAdapter.setItem(SeriesDetailsItem.CharactersItem(it))
            }
        }
    }
}