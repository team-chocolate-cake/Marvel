package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.event_details.adapters.EventAdapter
import com.chocolatecake.marvel.ui.event_details.data.EventDetailsItem

class EventDetailsFragment :
    BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventAdapter(viewModel.itemList, viewModel)
        Log.d("data", viewModel.itemList.toString())
        binding.recyclerViewEventDetails.adapter = adapter

    }

    override val viewModel: EventDetailsViewModel by viewModels()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event_details
}