package com.chocolatecake.marvel.ui.comic_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ImageResponse
import com.chocolatecake.marvel.databinding.FragmentComicDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem
import com.chocolatecake.marvel.ui.comic_details.recycler_adapters.MainRecyclerViewAdapter


class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val viewModel: ComicDetailsViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_comic_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainRecyclerViewAdapter(viewModel.itemsList, viewModel)
        binding.recyclerview.adapter = adapter
    }

    companion object{
        fun newInstance(){

        }
    }

}