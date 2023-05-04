package com.chocolatecake.marvel.ui.event_details.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.chocolatecake.marvel.MainActivity
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterViewBinding
import com.chocolatecake.marvel.databinding.ComicsViewBinding
import com.chocolatecake.marvel.databinding.HeaderViewBinding
import com.chocolatecake.marvel.databinding.SeriesViewBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.event_details.EventDetailsListener
import com.chocolatecake.marvel.ui.event_details.data.EventDetailsItem
import com.chocolatecake.marvel.ui.event_details.data.EventDetailsItemType

class EventAdapter(
    private val list: List<EventDetailsItem>,
    private val eventDetailsListener: EventDetailsListener,
) :
    BaseAdapter<EventDetailsItem>(list, eventDetailsListener) {
    override val layoutId: Int
        get() = R.layout.fragment_event_details

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            EventDetailsItemType.HEADER.ordinal -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.header_view, parent, false
                    )
                )
            }

            EventDetailsItemType.CHARACTER.ordinal -> {
                CharacterViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.character_view, parent, false
                    )
                )
            }

            EventDetailsItemType.SERIES.ordinal -> {
                SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_view, parent, false
                    )
                )
            }

            EventDetailsItemType.COMICS.ordinal -> {
                ComicsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.comics_view, parent, false
                    )
                )
            }

            else -> throw Exception("Mimo")
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> bindHeader(holder, position)
            is CharacterViewHolder -> {
                bindCharacters(holder, position)
            }

            is SeriesViewHolder -> {
                bindSeries(holder, position)
            }

            is ComicsViewHolder -> {
                bindComics(holder, position)
            }
        }
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val header = list[position] as EventDetailsItem.Header
        holder.binding.item = header.eventResult
    }

    private fun bindCharacters(holder: CharacterViewHolder, position: Int) {
        val viewModel = list[position] as EventDetailsItem.Character
        val adapter = viewModel.viewModel.characters.value?.toData()
            ?.let { CharactersAdapter(it, eventDetailsListener) }
        Log.d("Tag",viewModel.viewModel.characters.value?.toData().toString())
        holder.binding.recyclerView.adapter = adapter
        holder.binding.viewModel = viewModel.viewModel

    }

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val viewModel = list[position] as EventDetailsItem.Series
        val adapter =
            viewModel.viewModel.series.value?.toData()
                ?.let { SeriesAdapter(it, eventDetailsListener) }
        holder.binding.recyclerView2.adapter = adapter
        holder.binding.viewModel = viewModel.viewModel
    }

    private fun bindComics(holder: ComicsViewHolder, position: Int) {
        val viewModel = list[position] as EventDetailsItem.Comics
        val adapter =
            viewModel.viewModel.comics.value?.toData()
                ?.let { ComicsAdapter(it, eventDetailsListener) }
        holder.binding.recyclerView.adapter = adapter
        holder.binding.viewModel = viewModel.viewModel
    }

    override fun getItemViewType(position: Int) = list[position].type.ordinal

    class HeaderViewHolder(val binding: HeaderViewBinding) : BaseViewHolder(binding)
    class CharacterViewHolder(val binding: CharacterViewBinding) : BaseViewHolder(binding)
    class SeriesViewHolder(val binding: SeriesViewBinding) : BaseViewHolder(binding)
    class ComicsViewHolder(val binding: ComicsViewBinding) : BaseViewHolder(binding)
}