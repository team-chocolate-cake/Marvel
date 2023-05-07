package com.chocolatecake.marvel.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.ItemCharacterBinding
import com.chocolatecake.marvel.databinding.ItemComicBinding
import com.chocolatecake.marvel.databinding.ItemSeriesBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SearchListAdapter(private val listener: SearchInteractionListener) :
    ListAdapter<SearchItems, BaseAdapter.BaseViewHolder>(SearchDiffUtil()) {
    class SearchDiffUtil : DiffUtil.ItemCallback<SearchItems>() {
        override fun areItemsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).searchItemType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.BaseViewHolder {
        return when (SearchItemType.values()[viewType]) {
            SearchItemType.TYPE_SERIES -> {
                SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_series,
                        parent, false
                    )
                )
            }

            SearchItemType.TYPE_COMICS -> {
                ComicsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_comic,
                        parent, false
                    )
                )
            }

            SearchItemType.TYPE_CHARACTER -> {
                CharactersViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_character,
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseAdapter.BaseViewHolder, position: Int) {
        when (holder) {
            is SeriesViewHolder -> bindSeries(holder, position)
            is ComicsViewHolder -> bindComics(holder, position)
            is CharactersViewHolder -> bindCharacters(holder, position)
        }
    }

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position) as SearchItems.SeriesItem
        holder.binding.item = series.series
        holder.binding.listener = listener
    }

    private fun bindComics(holder: ComicsViewHolder, position: Int) {
        val comics = getItem(position) as SearchItems.ComicsItem
        holder.binding.item = comics.comics
        holder.binding.listener = listener
    }

    private fun bindCharacters(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position) as SearchItems.CharacterItem
        holder.binding.item = character.character
        holder.binding.listener = listener
    }

    class SeriesViewHolder(val binding: ItemSeriesBinding) : BaseAdapter.BaseViewHolder(binding)

    class ComicsViewHolder(val binding: ItemComicBinding) : BaseAdapter.BaseViewHolder(binding)

    class CharactersViewHolder(val binding: ItemCharacterBinding) :
        BaseAdapter.BaseViewHolder(binding)

}