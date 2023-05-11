package com.chocolatecake.marvel.ui.storiesDetails.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.StoriesDetailsFragmentBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.storiesDetails.adapter.ComicsAdapter
import com.chocolatecake.marvel.ui.storiesDetails.adapter.CreatorsAdapter
import com.chocolatecake.marvel.ui.storiesDetails.adapter.SeriesAdapter
import com.chocolatecake.marvel.ui.storiesDetails.view_model.StoriesDetailsViewModel

class StoriesDetailsFragment  :BaseFragment<StoriesDetailsFragmentBinding, StoriesDetailsViewModel>(){
    override val viewModel: StoriesDetailsViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.stories_details_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val creatorsAdapter=CreatorsAdapter(mutableListOf(),viewModel)
        binding.recyclerViewCreators.adapter=creatorsAdapter

        val comicAdapter=ComicsAdapter(mutableListOf(),viewModel)
        binding.recyclerViewComics.adapter=comicAdapter

        val seriesAdapter= SeriesAdapter(mutableListOf(),viewModel)
        binding.recyclerViewSeries.adapter=seriesAdapter


    }



}