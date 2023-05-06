package com.chocolatecake.marvel.ui.event_details

import android.os.Bundle
import android.util.Log
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
    lateinit var adapter: EventAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventAdapter(viewModel.itemList, viewModel)
        Log.d("data", viewModel.itemList.toString())
        binding.recyclerViewEventDetails.adapter = adapter
        setAdapter()
        updateItems()
        handleNavigation()
    }

    private fun setAdapter() {
        adapter = EventAdapter(
            mutableListOf(
                EventDetailsItem.Header(null),
                EventDetailsItem.Character(emptyList()),
                EventDetailsItem.Series(emptyList()),
                EventDetailsItem.Comics(emptyList()),
            ), viewModel
        )
        binding.recyclerViewEventDetails.adapter = adapter
    }

    private fun updateItems() {
        viewModel.event.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(EventDetailsItem.Header(it))
            }
        }
        viewModel.characters.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(EventDetailsItem.Character(it))
            }
        }
        viewModel.series.observe(viewLifecycleOwner) {
            it.toData()?.let {
                adapter.setItem(EventDetailsItem.Series(it))
            }

        }
        viewModel.comics.observe(viewLifecycleOwner) { status ->
            status.toData()?.let {
                adapter.setItem(EventDetailsItem.Comics(it))
            }
        }
    }

    private fun handleNavigation() {
        viewModel._characterId.observe(viewLifecycleOwner) { characterId ->
            if (characterId != null) {
                Toast.makeText(requireContext(), characterId.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel._seriesId.observe(viewLifecycleOwner) { seriesId ->
            if (seriesId != null) {
                Toast.makeText(requireContext(), seriesId.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel._comicsId.observe(viewLifecycleOwner) { comicsId ->
            if (comicsId != null) {
                Toast.makeText(requireContext(), comicsId.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override val viewModel: EventDetailsViewModel by viewModels()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event_details
}