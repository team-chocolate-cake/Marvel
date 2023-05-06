package com.chocolatecake.marvel.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.ui.base.BaseAdapter


//class SearchAdapter(var items: List<Any>, listener: SearchInteractionListener) :
//    BaseAdapter<Any>(items, listener) {
//
//    fun updateList(newItems: List<Any>) {
//        val oldItems = items
//        items = newItems
//        val diffResult = DiffUtil.calculateDiff(DiffUtils(oldItems, newItems))
//        diffResult.dispatchUpdatesTo(this)
//    }

//    override fun getItemCount() = items.size
//
//    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
//        holder.bind(items[position])
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
//        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
//        return ItemViewHolder(itemView, listener)
//    }
//
//    override val layoutId: Int
//        get() = R.layout.item_series
//
//    private class ItemViewHolder(itemView: View, listener: SearchInteractionListener) :
//        BaseViewHolder<Any>(itemView, listener) {
//        override fun bind(item: Any) {
//            // Bind data to views in the ViewHolder
//        }
//    }
//}


import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.base.SearchResult
import com.chocolatecake.marvel.databinding.ItemCharacterBinding
import com.chocolatecake.marvel.databinding.ItemComicBinding


import com.chocolatecake.marvel.databinding.ItemSeriesBinding


class SearchAdapter(
    private var itemsList: MutableList<SearchItems>,
    listener: SearchInteractionListener
) : BaseAdapter<SearchItems>(itemsList, listener) {

    override val layoutId: Int
        get() = R.layout.item_series

    fun updateList(item: SearchItems) {
        val newItems = itemsList.apply {
//            removeAt(item.priority)
            add(item.priority, item)
        }
        setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_SERIES -> {
                return SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_series,
                        parent, false
                    )
                )
            }

            VIEW_TYPE_CHARACTER -> {
                CharactersViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_character,
                        parent, false
                    )
                )
            }

            VIEW_TYPE_COMIC -> {
                ComicsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_comic,
                        parent, false
                    )
                )
            }

            else -> throw Exception("Banan Banan")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (itemsList.isNotEmpty()) {
            return (itemsList[position].priority)
        }
        return -1
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (itemsList.isEmpty() || position == -1) {
            Log.d("banan", position.toString())
            return
        }
        when (holder) {
            is SeriesViewHolder -> bindSeries(holder, position)
            is CharactersViewHolder -> bindCharacters(holder, position)
            is ComicsViewHolder -> bindComics(holder, position)
        }
    }

    class SeriesViewHolder(val binding: ItemSeriesBinding) : BaseViewHolder(binding)

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val series = itemsList[position] as SearchItems.SeriesItem
//        series.series.forEach { holder.binding.item = it }
        holder.binding.item = series.series
    }

    class CharactersViewHolder(val binding: ItemCharacterBinding) : BaseViewHolder(binding)

    private fun bindCharacters(holder: CharactersViewHolder, position: Int) {
        val character = itemsList[position] as SearchItems.CharacterItem
//        character.character.forEach { holder.binding.item = it }
        holder.binding.item = character.character
    }

    class ComicsViewHolder(val binding: ItemComicBinding) : BaseViewHolder(binding)

    private fun bindComics(holder: ComicsViewHolder, position: Int) {
        val comics = itemsList[position] as SearchItems.ComicsItem
//        comics.comics.forEach { holder.binding.item = it }
        holder.binding.item = comics.comics
    }

    companion object {
        const val VIEW_TYPE_SERIES = 1
        const val VIEW_TYPE_COMIC = 2
        const val VIEW_TYPE_CHARACTER = 3
    }
}