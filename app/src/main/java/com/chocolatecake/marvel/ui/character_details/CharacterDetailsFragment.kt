package com.chocolatecake.marvel.ui.character_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class CharacterDetailsFragment :
    BaseFragment<CharacterDetailsBinding, CharacterDetailsViewModel>() {
    override val viewModel: CharacterDetailsViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.character_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharacterDetailsAdapter(viewModel.itemList, viewModel)
        binding.recyclerViewCharacterDetails.adapter = adapter
    }
}