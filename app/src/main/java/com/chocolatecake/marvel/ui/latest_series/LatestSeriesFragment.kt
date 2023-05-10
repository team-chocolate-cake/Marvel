package com.chocolatecake.marvel.ui.latest_series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentLatestSeriesBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class LatestSeriesFragment() : BaseFragment<FragmentLatestSeriesBinding, LatestSeriesViewModel>() {

    override val viewModelClass: Class<LatestSeriesViewModel>
        get() = LatestSeriesViewModel::class.java

    override val layoutIdFragment = R.layout.fragment_latest_series

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LatestSeriesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSeries.adapter = adapter
    }
}
