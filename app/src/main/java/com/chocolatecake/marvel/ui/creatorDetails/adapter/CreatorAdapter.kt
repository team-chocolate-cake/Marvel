package com.chocolatecake.marvel.ui.creatorDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.ComicViewBinding
import com.chocolatecake.marvel.databinding.FragmentCreatorDetailsBinding
import com.chocolatecake.marvel.databinding.SeriesViewBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsItem
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsListener

class CreatorAdapter(
    private var creatorDetailsItem: MutableList<CreatorDetailsItem>,
    private val listener: CreatorDetailsListener
) :
    BaseAdapter<CreatorDetailsItem>(creatorDetailsItem, listener) {
    override val layoutId: Int
        get() = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_COMICS -> {
                ComicViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.comic_view, parent, false
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
            else -> throw Exception("Unknown View Type")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (creatorDetailsItem.isEmpty() || position == -1) {
            return
        }
        when (creatorDetailsItem[position]) {
            is CreatorDetailsItem.SeriesItem -> bindSeries(holder as SeriesViewHolder, position)
            is CreatorDetailsItem.ComicItem -> bindComic(holder as ComicViewHolder, position)
            is CreatorDetailsItem.CreatorItem -> bindCreator(holder as CreatorViewHolder, position)
        }

    }

    private fun bindCreator(creatorViewHolder: CreatorViewHolder, position: Int) {
        val currentItem = creatorDetailsItem[position] as CreatorDetailsItem.CreatorItem
        creatorViewHolder.binding.item = currentItem.creatorResult


    }

    private fun bindSeries(seriesViewHolder: SeriesViewHolder, position: Int) {
        val currentSeries = creatorDetailsItem[position] as CreatorDetailsItem.SeriesItem

        val adapter = SeriesAdapter(currentSeries.seriesResult, listener)
        seriesViewHolder.binding.recyclerViewSeries.adapter = adapter
    }

    private fun bindComic(comicViewHolder: ComicViewHolder, position: Int) {
        val currentComic = creatorDetailsItem[position] as CreatorDetailsItem.ComicItem

        val adapter = ComicAdapter(currentComic.comicResult, listener)
        comicViewHolder.binding.recyclerViewComic.adapter = adapter
    }
    override fun getItemViewType(position: Int): Int {
        if (creatorDetailsItem.isNotEmpty()) {
            return when(creatorDetailsItem[position]) {

                is CreatorDetailsItem.SeriesItem -> VIEW_TYPE_SERIES
                is CreatorDetailsItem.ComicItem -> VIEW_TYPE_COMICS
                is CreatorDetailsItem.CreatorItem -> VIEW_TYPE_CREATOR
            }
        }
        return -1
    }

    override fun setItems(newItems: List<CreatorDetailsItem>) {
        creatorDetailsItem = newItems.sortedBy { it.Priority }.toMutableList()
        super.setItems(newItems)
    }

    class CreatorViewHolder(val binding: FragmentCreatorDetailsBinding) : BaseViewHolder(binding)

    class ComicViewHolder(val binding: ComicViewBinding) : BaseViewHolder(binding)
    class SeriesViewHolder(val binding: SeriesViewBinding) : BaseViewHolder(binding)


    companion object {
        const val VIEW_TYPE_COMICS = 1
        const val VIEW_TYPE_SERIES = 2
        const val VIEW_TYPE_CREATOR= 3

    }
}
