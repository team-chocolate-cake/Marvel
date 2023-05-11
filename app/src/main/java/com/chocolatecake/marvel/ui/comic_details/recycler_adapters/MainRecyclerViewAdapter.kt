package com.chocolatecake.marvel.ui.comic_details.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterHorizentalRecyclerViewBinding
import com.chocolatecake.marvel.databinding.EventItemBinding
import com.chocolatecake.marvel.databinding.HeaderRecyclerItemComicDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.comic_details.ComicInteractionListener
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem

class MainRecyclerViewAdapter(
    private val list: List<ComicDetailsItem>,
    private val listener: ComicInteractionListener
) : BaseAdapter<ComicDetailsItem>(list, listener) {


    override val layoutId: Int
        get() = -1

    class HeaderViewHolder(val binding: HeaderRecyclerItemComicDetailsBinding) :
        BaseViewHolder(binding)

    class CharacterViewHolder(val binding: CharacterHorizentalRecyclerViewBinding) :
        BaseViewHolder(binding)

    class EventViewHolder(val binding: EventItemBinding) :
        BaseViewHolder(binding)


    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is ComicDetailsItem.Header -> HEADER
            is ComicDetailsItem.Characters -> CHARACTERS
            is ComicDetailsItem.Events -> EVENTS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.header_recycler_item_comic_details,
                        parent,
                        false
                    )
                )
            }

            CHARACTERS -> CharacterViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.character_horizental_recycler_view,
                    parent,
                    false
                )
            )

            EVENTS -> EventViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.event_item,
                    parent,
                    false
                )
            )

            else -> throw Exception("view not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> bindHeader(holder, position)
            is CharacterViewHolder -> bindCharacter(holder, position)
            is EventViewHolder -> bindEvent(holder, position)
        }
    }

    private fun bindEvent(holder: EventViewHolder, position: Int) {
        val eventItem = list[position] as ComicDetailsItem.Events
        holder.binding.eventResult = eventItem.eventResult
    }

    private fun bindCharacter(holder: CharacterViewHolder, position: Int) {
        val characterItem = list[position] as ComicDetailsItem.Characters
        val adapter =
            HorizontalCharacterAdapter(characterItem.list, listener)
        holder.binding.recyclerview.adapter = adapter
        holder.binding.item = characterItem
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val headerItem = list[position] as ComicDetailsItem.Header
        holder.binding.item = headerItem.comic
    }

    companion object {
        private const val HEADER = 121
        private const val CHARACTERS = 122
        private const val EVENTS = 123
    }

}