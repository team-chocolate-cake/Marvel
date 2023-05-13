package com.chocolatecake.marvel.ui.event_details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterViewBinding
import com.chocolatecake.marvel.databinding.ComicsViewBinding
import com.chocolatecake.marvel.databinding.HeaderViewBinding
import com.chocolatecake.marvel.databinding.SeriesViewBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.event_details.EventDetailsItem
import com.chocolatecake.marvel.ui.event_details.EventDetailsItemType

class EventAdapter(
    private var eventDetailsItems: MutableList<EventDetailsItem>,
    private val eventDetailsListener: EventDetailsListener,
) : BaseAdapter<EventDetailsItem>(eventDetailsItems, eventDetailsListener) {
    override val layoutId = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            EventDetailsItemType.HEADER.ordinal -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.header_view, parent, false
                    )
                )
            }

            EventDetailsItemType.CHARACTER.ordinal -> {
                CharacterViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.character_view, parent, false
                    )
                )
            }

            EventDetailsItemType.SERIES.ordinal -> {
                SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.series_view, parent, false
                    )
                )
            }

            EventDetailsItemType.COMICS.ordinal -> {
                ComicsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.comics_view, parent, false
                    )
                )
            }

            else -> throw Exception("Mimo")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> bindHeader(holder, position)
            is CharacterViewHolder -> bindCharacters(holder, position)
            is SeriesViewHolder -> bindSeries(holder, position)
            is ComicsViewHolder -> bindComics(holder, position)
        }
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val header = eventDetailsItems[position] as EventDetailsItem.Header
        holder.binding.item = header.eventResult
    }

    private fun bindCharacters(holder: CharacterViewHolder, position: Int) {
        val character = eventDetailsItems[position] as EventDetailsItem.Character
        val adapter = CharactersAdapter(character.profileResult, eventDetailsListener)
        holder.binding.recyclerViewCharacters.adapter = adapter
        holder.binding.item = character
    }

    fun setItem(item: EventDetailsItem) {
        val newItems = eventDetailsItems.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<EventDetailsItem>) {
        eventDetailsItems = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val series = eventDetailsItems[position] as EventDetailsItem.Series
        val adapter = SeriesAdapter(series.seriesResult, eventDetailsListener)
        holder.binding.recyclerViewSeries.adapter = adapter
        holder.binding.item = series
    }

    private fun bindComics(holder: ComicsViewHolder, position: Int) {
        val comics = eventDetailsItems[position] as EventDetailsItem.Comics
        val adapter = ComicsAdapter(comics.comicsResult, eventDetailsListener)
        holder.binding.recyclerViewComics.adapter = adapter
        holder.binding.item = comics
    }

    override fun getItemViewType(position: Int): Int = eventDetailsItems[position].type.ordinal

    class HeaderViewHolder(val binding: HeaderViewBinding) : BaseViewHolder(binding)
    class CharacterViewHolder(val binding: CharacterViewBinding) : BaseViewHolder(binding)
    class SeriesViewHolder(val binding: SeriesViewBinding) : BaseViewHolder(binding)
    class ComicsViewHolder(val binding: ComicsViewBinding) : BaseViewHolder(binding)
}