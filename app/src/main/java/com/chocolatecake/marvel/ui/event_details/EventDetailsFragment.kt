package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.event_details.adapters.EventAdapter

class EventDetailsFragment :
    BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val adapter = EventAdapter(mutableListOf(), viewModel)
     //   binding.recyclerView.adapter = adapter
    }

    override val viewModel: EventDetailsViewModel by viewModels()

    override val layoutIdFragment: Int
        get() = R.layout.header_view
}