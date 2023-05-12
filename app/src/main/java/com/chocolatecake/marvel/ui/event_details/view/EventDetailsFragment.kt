package com.chocolatecake.marvel.ui.event_details.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.core.factory.ViewModeFactory
import com.chocolatecake.marvel.ui.event_details.adapters.EventAdapter
import com.chocolatecake.marvel.ui.event_details.model.EventDetailsItem
import com.chocolatecake.marvel.ui.event_details.view_model.EventDetailsViewModel
import com.chocolatecake.marvel.util.observeNonNull

class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {

    private lateinit var adapter: EventAdapter
    private val args: EventDetailsFragmentArgs by navArgs()
    override val viewModel: EventDetailsViewModel by viewModels{ ViewModeFactory(args.eventId)}
    override val layoutIdFragment: Int = R.layout.fragment_event_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter(
            mutableListOf(
                EventDetailsItem.Header(EventResult()),
                EventDetailsItem.Character(emptyList()),
                EventDetailsItem.Series(emptyList()),
                EventDetailsItem.Comics(emptyList())
            ), viewModel
        )
        binding.recyclerViewEventDetails.adapter = adapter
        updateItems()
    }

    private fun updateItems() {
        viewModel.event.observeNonNull(viewLifecycleOwner) { eventResult ->
            eventResult.toData()?.let { adapter.setItem(EventDetailsItem.Header(it)) }
        }
        viewModel.characters.observeNonNull(viewLifecycleOwner) { charactersResult ->
            charactersResult.toData()?.let { adapter.setItem(EventDetailsItem.Character(it)) }
        }
        viewModel.series.observeNonNull(viewLifecycleOwner) { seriesResult ->
            seriesResult.toData()?.let { adapter.setItem(EventDetailsItem.Series(it)) }
        }
        viewModel.comics.observeNonNull(viewLifecycleOwner) { comicsResult ->
            comicsResult.toData()?.let { adapter.setItem(EventDetailsItem.Comics(it)) }
        }
    }
}
