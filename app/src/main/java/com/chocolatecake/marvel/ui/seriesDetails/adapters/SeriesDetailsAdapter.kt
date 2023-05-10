package com.chocolatecake.marvel.ui.seriesDetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.SeriesDetailsCharacterViewBinding
import com.chocolatecake.marvel.databinding.SeriesDetailsComicsViewBinding
import com.chocolatecake.marvel.databinding.SeriesDetailsEventsViewBinding
import com.chocolatecake.marvel.databinding.SeriesDetailsHeaderViewBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.seriesDetails.SeriesDetailsItem
import com.chocolatecake.marvel.ui.seriesDetails.SeriesDetailsItemType
import com.chocolatecake.marvel.ui.seriesDetails.view.SeriesDetailsListener

class SeriesDetailsAdapter(
    private var itemsSeriesDetails: MutableList<SeriesDetailsItem>,
    private val listener: SeriesDetailsListener
) : BaseAdapter<SeriesDetailsItem>(itemsSeriesDetails, listener) {

    override val layoutId: Int = 1

    fun setItem(item: SeriesDetailsItem) {
        val newItems = itemsSeriesDetails.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<SeriesDetailsItem>) {
        itemsSeriesDetails = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SeriesDetailsItemType.HEADER.ordinal -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_details_header_view, parent, false
                    )
                )
            }

           SeriesDetailsItemType.CHARACTERS.ordinal -> {
                CharactersViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_details_character_view, parent, false
                    )
                )
            }

            SeriesDetailsItemType.EVENTS.ordinal -> {
                EventViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_details_events_view, parent, false
                    )
                )
            }

            SeriesDetailsItemType.COMICS.ordinal-> {
                ComicViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.series_details_comics_view, parent, false
                    )
                )
            }

            else -> throw Exception("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (itemsSeriesDetails.isEmpty() || position == -1) {
            return
        }

        when (itemsSeriesDetails[position]) {
            is SeriesDetailsItem.EventsItem -> bindEvents(holder as EventViewHolder, position)
            is SeriesDetailsItem.SeriesItem -> bindHeader(holder as HeaderViewHolder, position)
            is SeriesDetailsItem.ComicsItem -> bindComic(holder as ComicViewHolder, position)
            is SeriesDetailsItem.CharactersItem -> bindCharacter(
                holder as CharactersViewHolder, position
            )

            else -> {}
        }
    }

    private fun bindCharacter(charactersViewHolder: CharactersViewHolder, position: Int) {
        val currentCharacter = itemsSeriesDetails[position] as SeriesDetailsItem.CharactersItem

        val adapter = CharactersAdapter(currentCharacter.charactersResult, listener)
        charactersViewHolder.binding.recyclerViewCharacters.adapter = adapter

    }

    private fun bindComic(comicViewHolder: ComicViewHolder, position: Int) {
        val currentComic = itemsSeriesDetails[position] as SeriesDetailsItem.ComicsItem

        val adapter = ComicsAdapter(currentComic.comicsResult, listener)
        comicViewHolder.binding.recyclerViewComics.adapter = adapter
    }

    private fun bindEvents(eventViewHolder: EventViewHolder, position: Int) {
        val currentEvent = itemsSeriesDetails[position] as SeriesDetailsItem.EventsItem

        val adapter = EventsAdapter(currentEvent.eventResult, listener)
        eventViewHolder.binding.recyclerViewEvents.adapter = adapter
    }

    private fun bindHeader(headerViewHolder: HeaderViewHolder, position: Int) {
        val currentItem = itemsSeriesDetails[position] as SeriesDetailsItem.SeriesItem

        headerViewHolder.binding.item = currentItem.seriesResult
    }

    override fun getItemViewType(position: Int): Int = itemsSeriesDetails[position].type.ordinal

    class HeaderViewHolder(val binding: SeriesDetailsHeaderViewBinding) : BaseViewHolder(binding)
    class CharactersViewHolder(val binding: SeriesDetailsCharacterViewBinding) : BaseViewHolder(binding)
    class ComicViewHolder(val binding: SeriesDetailsComicsViewBinding) : BaseViewHolder(binding)
    class EventViewHolder(val binding: SeriesDetailsEventsViewBinding) : BaseViewHolder(binding)

}