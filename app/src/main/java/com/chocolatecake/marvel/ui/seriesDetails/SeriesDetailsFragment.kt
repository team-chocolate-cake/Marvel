package com.chocolatecake.marvel.ui.seriesDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeriesDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding,SeriesDetailsViewModel>() {
    override val viewModel: SeriesDetailsViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_details

}