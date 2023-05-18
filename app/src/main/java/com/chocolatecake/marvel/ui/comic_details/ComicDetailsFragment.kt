package com.chocolatecake.marvel.ui.comic_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentComicDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.util.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val viewModel: ComicDetailsViewModel by viewModels()

    override val layoutIdFragment = R.layout.fragment_comic_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBacks()
    }

    private fun setup() {
        binding.recyclerviewCharacters.adapter = CharactersAdapter(mutableListOf(), viewModel)
    }

    private fun addCallBacks() {
        viewModel.toastMessage.observeNonNull(requireActivity()){
            createToast(it)
        }
    }

    private fun createToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

}