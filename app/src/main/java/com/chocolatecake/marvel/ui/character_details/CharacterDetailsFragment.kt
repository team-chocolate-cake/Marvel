package com.chocolatecake.marvel.ui.character_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentCharacterDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailsViewModel>() {

    override val viewModel: CharacterDetailsViewModel by viewModels()

    override val layoutIdFragment = R.layout.fragment_character_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharacterComicsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComics.adapter = adapter
    }
}