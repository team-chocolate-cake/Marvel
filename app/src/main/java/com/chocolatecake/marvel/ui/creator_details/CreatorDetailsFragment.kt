package com.chocolatecake.marvel.ui.creator_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentCreatorDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.creator_details.adapter.ComicAdapter
import com.chocolatecake.marvel.ui.creator_details.adapter.SeriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatorDetailsFragment :
    BaseFragment<FragmentCreatorDetailsBinding, CreatorDetailsViewModel>() {

    private val args: CreatorDetailsFragmentArgs by navArgs()

    override val viewModel: CreatorDetailsViewModel by viewModels()

    override val layoutIdFragment = R.layout.fragment_creator_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.creatorId = args.creatorId
        viewModel.loadData()

        val comicAdapter = ComicAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComic.adapter = comicAdapter

        val seriesAdapter = SeriesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSeries.adapter = seriesAdapter
    }
}