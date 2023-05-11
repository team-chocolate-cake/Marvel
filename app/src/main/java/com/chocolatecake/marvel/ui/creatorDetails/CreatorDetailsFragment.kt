package com.chocolatecake.marvel.ui.creatorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentCreatorDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.creatorDetails.adapter.ComicAdapter
import com.chocolatecake.marvel.ui.creatorDetails.adapter.SeriesAdapter

class CreatorDetailsFragment :
    BaseFragment<FragmentCreatorDetailsBinding, CreatorDetailsViewModel>() {
    override val viewModel: CreatorDetailsViewModel by viewModels()

    override val layoutIdFragment = R.layout.fragment_creator_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comicAdapter = ComicAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComic.adapter = comicAdapter

        val seriesAdapter = SeriesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSeries.adapter = seriesAdapter
    }
}