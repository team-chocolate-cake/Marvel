package com.chocolatecake.marvel.ui.search

import android.view.LayoutInflater
import android.view.View
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
import com.chocolatecake.marvel.databinding.ItemCharacterBinding
import com.chocolatecake.marvel.databinding.ItemComicBinding


import com.chocolatecake.marvel.databinding.ItemSeriesBinding


class SearchAdapter(
   var itemsList:List<SearchItems>,
    listener:SearchInteractionListener
    ):BaseAdapter<SearchItems>(itemsList,listener) {
    override val layoutId: Int
        get() = R.layout.item_series
    fun updateList(newItems: List<SearchItems>) {
        val oldItems = itemsList
        itemsList = newItems
        val diffResult = DiffUtil.calculateDiff(DiffUtils(oldItems, newItems))
        diffResult.dispatchUpdatesTo(this)
    }

    class SeriesViewHolder(var itemSeriesBinding:ItemSeriesBinding ):BaseViewHolder(itemSeriesBinding)
    private fun onBindSeries(searchHeaderViewHolder: SeriesViewHolder, position:Int){
        val header = itemsList[position] as SearchItems.SeriesItem
        searchHeaderViewHolder.itemSeriesBinding.item=header.series
    }
    class ComicsViewHolder(var ItemComicBinding:ItemComicBinding ):BaseViewHolder(com.chocolatecake.marvel.databinding.ItemComicBinding)
    private fun onBindComics(searchHeaderViewHolder: ComicsViewHolder, position:Int){
        val header = itemsList[position] as SearchItems.ComicsItem
        searchHeaderViewHolder.ItemComicBinding.item=header.comics
    }
    class CharactersViewHolder(var itemSeriesBinding:ItemSeriesBinding ):BaseViewHolder(itemSeriesBinding)
    private fun onBindCharacters(searchHeaderViewHolder: SeriesViewHolder, position:Int){
        val header = itemsList[position] as SearchItems.SeriesItem
        searchHeaderViewHolder.itemSeriesBinding.item=header.series
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
             VIEW_SERIES->{
                 val binding=DataBindingUtil.inflate<ItemSeriesBinding>(
                     LayoutInflater.from(parent.context),
                     R.layout.item_series,
                     parent,
                     false)
                 SeriesViewHolder(binding)
             }
            VIEW_CHARACTERS ->
                DataBindingUtil.inflate<ItemCharacterBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false)
            SeriesViewHolder(binding)
            else -> throw Exception("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is SeriesViewHolder -> onBindSeries(holder,position)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position].searchItemType){
            SearchItemType.TYPE_SERIES -> VIEW_SERIES
            SearchItemType.TYPE_Comics -> VIEW_COMICS
            SearchItemType.TYPE_Character -> VIEW_CHARACTERS
        }
    }
   private companion object{
       const val VIEW_SERIES=0
       const val VIEW_COMICS=1
       const val VIEW_CHARACTERS=2

   }

}


