package com.chocolatecake.marvel.ui.stories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentStoriesBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class StoriesFragment : BaseFragment<FragmentStoriesBinding, StoriesViewModel>() {

    override val viewModel: StoriesViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_stories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storiesAdapter = StoriesAdapter(mutableListOf(),viewModel)
        binding.recyclerViewStories.adapter = storiesAdapter
    }
}