package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class EventDetailsFragment :
    BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel.characters.observe(requireActivity()) {
                val seriesList = it
                Log.d("Mimo",seriesList.toString())
            }
    }
    override val viewModel: EventDetailsViewModel by viewModels()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event_details

}