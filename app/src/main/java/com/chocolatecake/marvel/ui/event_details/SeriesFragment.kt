package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterViewBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.event_details.adapters.CharactersAdapter

class SeriesFragment:BaseFragment<CharacterViewBinding,EventDetailsViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharactersAdapter(mutableListOf(),viewModel)
        binding.recyclerView.adapter = adapter
    }

    override val viewModel: EventDetailsViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.character_view
}