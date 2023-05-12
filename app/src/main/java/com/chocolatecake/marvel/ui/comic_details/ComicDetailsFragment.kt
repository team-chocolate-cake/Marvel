package com.chocolatecake.marvel.ui.comic_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentComicDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.comic_details.recycler_adapters.CharactersAdapter
import com.chocolatecake.marvel.ui.core.factory.ViewModeFactory


class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val viewModel: ComicDetailsViewModel by viewModels { ViewModeFactory(args.comicId)}

    override val layoutIdFragment = R.layout.fragment_comic_details
    private val args: ComicDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBacks()
    }

    private fun setup() {
        binding.recyclerview.adapter = CharactersAdapter(mutableListOf(), viewModel)
    }

    private fun addCallBacks() {
        viewModel.toastMessage.observe(requireActivity()){
            createToast(it.toString())
        }
    }

    private fun createToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

}