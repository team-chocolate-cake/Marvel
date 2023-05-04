package com.chocolatecake.marvel.ui.event_details.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
    private val list: MutableList<EventDetailsItem>,
    private val eventDetailsListener: EventDetailsListener,
) :
    BaseAdapter<EventDetailsItem?>(list, eventDetailsListener) {
    override val layoutId: Int
        get() = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.header_view, parent, false
                    )
                )
            }

            VIEW_TYPE_CHARACTER -> {
                CharacterViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.character_view, parent, false
                    )
                )
            }

            VIEW_TYPE_SERIES -> {
                SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_view, parent, false
                    )
                )
            }

            VIEW_TYPE_COMIC -> {
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
            is CharacterViewHolder -> bindCharacters(holder, position)
            is SeriesViewHolder -> bindSeries(holder, position)
            is ComicsViewHolder -> bindComics(holder, position)
        }
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val header = list[position] as EventDetailsItem.Header
        holder.binding.item = header.eventResult
    }

    private fun bindCharacters(holder: CharacterViewHolder, position: Int) {
        val character = list[position] as EventDetailsItem.Character
        val adapter = CharactersAdapter(character.profileResult, eventDetailsListener)
        holder.binding.recyclerView.adapter = adapter
        holder.binding.item = character
    }

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val series = list[position] as EventDetailsItem.Series
        val adapter = SeriesAdapter(series.seriesResult, eventDetailsListener)
        holder.binding.recyclerView2.adapter = adapter
        holder.binding.item = series
    }

    private fun bindComics(holder: ComicsViewHolder, position: Int) {
        val comics = list[position] as EventDetailsItem.Comics
        val adapter = ComicsAdapter(comics.comicsResult, eventDetailsListener)
        holder.binding.recyclerViewComics.adapter = adapter
        holder.binding.item = comics
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            EventDetailsItemType.HEADER -> VIEW_TYPE_HEADER
            EventDetailsItemType.CHARACTER -> VIEW_TYPE_CHARACTER
            EventDetailsItemType.SERIES -> VIEW_TYPE_SERIES
            EventDetailsItemType.COMICS -> VIEW_TYPE_COMIC
            else -> throw Exception("Mimo")
        }
    }

    class HeaderViewHolder(val binding: HeaderViewBinding) : BaseViewHolder(binding)
    class CharacterViewHolder(val binding: CharacterViewBinding) : BaseViewHolder(binding)
    class SeriesViewHolder(val binding: SeriesViewBinding) : BaseViewHolder(binding)
    class ComicsViewHolder(val binding: ComicsViewBinding) : BaseViewHolder(binding)

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_CHARACTER = 1
        private const val VIEW_TYPE_SERIES = 2
        private const val VIEW_TYPE_COMIC = 3
    }
}