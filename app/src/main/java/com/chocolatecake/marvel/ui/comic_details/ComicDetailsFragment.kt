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
//        viewModel.updateCurrentComicId(1308)
        val adapter = MainRecyclerViewAdapter(mutableListOf(
            ComicDetailsItem.Header(
                ComicsResult(
                    thumbnail = ImageResponse(path = "https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg")
                )
            )
        ), viewModel)
        binding.recyclerview.adapter = adapter
    }

    companion object{
        fun newInstance(){

        }
    }

}