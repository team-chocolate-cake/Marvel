package com.chocolatecake.marvel.ui.creatorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentCreatorDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.character_details.CharacterDetailsFragmentArgs
import com.chocolatecake.marvel.ui.core.factory.ViewModeFactory
import com.chocolatecake.marvel.ui.creatorDetails.adapter.ComicAdapter
import com.chocolatecake.marvel.ui.creatorDetails.adapter.SeriesAdapter

class CreatorDetailsFragment :
    BaseFragment<FragmentCreatorDetailsBinding, CreatorDetailsViewModel>() {

    private val args: CreatorDetailsFragmentArgs by navArgs()

    override val viewModel: CreatorDetailsViewModel by viewModels { ViewModeFactory(args.creatorId) }

    override val layoutIdFragment = R.layout.fragment_creator_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comicAdapter = ComicAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComic.adapter = comicAdapter

        val seriesAdapter = SeriesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSeries.adapter = seriesAdapter
    }
}