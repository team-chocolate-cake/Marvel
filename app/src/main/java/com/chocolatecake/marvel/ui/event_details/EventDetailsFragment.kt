package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentEventDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.event_details.adapters.EventAdapter
import com.chocolatecake.marvel.ui.event_details.data.EventDetailsItem

class EventDetailsFragment :
    BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {

    private lateinit var adapter: EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter(
            mutableListOf(
                EventDetailsItem.Header(null),
                EventDetailsItem.Character(emptyList()),
                EventDetailsItem.Series(emptyList()),
                EventDetailsItem.Comics(emptyList())
            ), viewModel
        )
        binding.recyclerViewEventDetails.adapter = adapter
        updateItems()
        handleNavigation()
    }

    private fun updateItems() {
        viewModel.event.observe(viewLifecycleOwner) { eventResult ->
            eventResult.toData()?.let { adapter.setItem(EventDetailsItem.Header(it)) }
        }
        viewModel.characters.observe(viewLifecycleOwner) { charactersResult ->
            charactersResult.toData()?.let { adapter.setItem(EventDetailsItem.Character(it)) }
        }
        viewModel.series.observe(viewLifecycleOwner) { seriesResult ->
            seriesResult.toData()?.let { adapter.setItem(EventDetailsItem.Series(it)) }
        }
        viewModel.comics.observe(viewLifecycleOwner) { comicsResult ->
            comicsResult.toData()?.let { adapter.setItem(EventDetailsItem.Comics(it)) }
        }
    }

    private fun handleNavigation() {
        viewModel.characterId.observe(viewLifecycleOwner) { characterId ->
            characterId?.let {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.seriesId.observe(viewLifecycleOwner) { seriesId ->
            seriesId?.let {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.comicsId.observe(viewLifecycleOwner) { comicsId ->
            comicsId?.let {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override val viewModel: EventDetailsViewModel by viewModels()

    override val layoutIdFragment: Int = R.layout.fragment_event_details
}
