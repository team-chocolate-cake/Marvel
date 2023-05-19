package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.EventDetails
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
                EventDetailsItem.HeaderDetails(EventDetails(
                    id = 0,
                    imageURL = "",
                    title = "",
                    description = "",
                )),
                EventDetailsItem.CharacterDetails(emptyList()),
                EventDetailsItem.SeriesDetails(emptyList()),
                EventDetailsItem.ComicsDetails(emptyList())
            ), viewModel
        )
        binding.recyclerViewEventDetails.adapter = adapter
        updateItems()
    }

    private fun updateItems() {
        viewModel.event.observeNonNull(viewLifecycleOwner) { eventResult ->
            eventResult.toData()?.let { adapter.setItem(EventDetailsItem.HeaderDetails(it)) }
        }
        viewModel.characters.observeNonNull(viewLifecycleOwner) { charactersResult ->
            charactersResult.toData()?.let { adapter.setItem(EventDetailsItem.CharacterDetails(it)) }
        }
        viewModel.series.observeNonNull(viewLifecycleOwner) { seriesResult ->
            seriesResult.toData()?.let { adapter.setItem(EventDetailsItem.SeriesDetails(it)) }
        }
        viewModel.comics.observeNonNull(viewLifecycleOwner) { comicsResult ->
            comicsResult.toData()?.let { adapter.setItem(EventDetailsItem.ComicsDetails(it)) }
        }
    }
}
