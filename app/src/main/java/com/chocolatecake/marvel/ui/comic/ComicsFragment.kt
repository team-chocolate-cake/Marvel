package com.chocolatecake.marvel.ui.comic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentComicsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : BaseFragment<FragmentComicsBinding, ComicsViewModel>() {

    override val viewModel: ComicsViewModel by viewModels()
    override val layoutIdFragment = R.layout.fragment_comics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ComicsAdapter(mutableListOf(), viewModel)
        binding.recyclerView.adapter = adapter
    }
}