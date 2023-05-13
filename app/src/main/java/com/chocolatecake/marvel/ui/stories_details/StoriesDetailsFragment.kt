package com.chocolatecake.marvel.ui.stories_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentStoriesDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.core.factory.ViewModeFactory
import com.chocolatecake.marvel.ui.stories_details.adapter.ComicsAdapter
import com.chocolatecake.marvel.ui.stories_details.adapter.CreatorsAdapter
import com.chocolatecake.marvel.ui.stories_details.adapter.SeriesAdapter

class StoriesDetailsFragment :
    BaseFragment<FragmentStoriesDetailsBinding, StoriesDetailsViewModel>() {

    private val args: StoriesDetailsFragmentArgs by navArgs()
    override val viewModel: StoriesDetailsViewModel by viewModels { ViewModeFactory(args.storyId) }

    override val layoutIdFragment: Int
        get() = R.layout.fragment_stories_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewCreators.adapter = creatorsAdapter

        val comicAdapter = ComicsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComics.adapter = comicAdapter

        val seriesAdapter = SeriesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSeries.adapter = seriesAdapter
    }
}