package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.event_details.adapters.EventAdapter
import com.chocolatecake.marvel.util.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {

    private lateinit var adapter: EventAdapter
    private val args: EventDetailsFragmentArgs by navArgs()
    override val viewModel: EventDetailsViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_event_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventId = args.eventId
        viewModel.reLoadData()

        adapter = EventAdapter(
            mutableListOf(
                EventDetailsItem.Header(EventDto()),
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
