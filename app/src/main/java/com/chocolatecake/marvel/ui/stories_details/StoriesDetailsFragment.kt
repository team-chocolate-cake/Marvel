package com.chocolatecake.marvel.ui.stories_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentStoriesDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.stories_details.adapter.ComicsAdapter
import com.chocolatecake.marvel.ui.stories_details.adapter.CreatorsAdapter
import com.chocolatecake.marvel.ui.stories_details.adapter.SeriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoriesDetailsFragment :
    BaseFragment<FragmentStoriesDetailsBinding, StoriesDetailsViewModel>() {

    override val viewModel: StoriesDetailsViewModel by viewModels()

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