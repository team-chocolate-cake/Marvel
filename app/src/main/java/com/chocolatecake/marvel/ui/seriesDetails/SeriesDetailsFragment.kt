package com.chocolatecake.marvel.ui.seriesDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chocolatecake.marvel.R

class SeriesDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesDetailsFragment()
    }

    private lateinit var viewModel: SeriesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_series_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeriesDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}