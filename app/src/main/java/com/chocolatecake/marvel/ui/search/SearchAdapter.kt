package com.chocolatecake.marvel.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.ItemCharacterBinding
import com.chocolatecake.marvel.databinding.ItemComicBinding
import com.chocolatecake.marvel.databinding.ItemSearchHeaderBinding
import com.chocolatecake.marvel.databinding.ItemSeriesBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SearchAdapter(
   val itemsList:List<SearchItems>,
    listener:SearchInteractionListener
    ):BaseAdapter<SearchItems>(itemsList,listener) {
    override val layoutId: Int
        get() = R.layout.fragment_seacrh

    class SearchHeaderViewHolder(var itemSearchHeaderBinding:ItemSearchHeaderBinding ):BaseViewHolder(itemSearchHeaderBinding)
    private fun onBindHeader(searchHeaderViewHolder: SearchHeaderViewHolder, position:Int){
        val header =itemsList[position] as SearchItems.HeaderItem
        searchHeaderViewHolder.itemSearchHeaderBinding.viewModel=header.viewModel
    }
    class CharacterViewHolder(var itemCharacterBinding:ItemCharacterBinding ):BaseViewHolder(itemCharacterBinding)
    private fun onBindCharacter(CharacterViewHolder: CharacterViewHolder, position:Int){
        val header = itemsList[position] as SearchItems.CharacterItem
        CharacterViewHolder.itemCharacterBinding.item=header.character
    }
    class SeriesViewHolder(var itemSeriesBinding:ItemSeriesBinding ):BaseViewHolder(itemSeriesBinding)
    private fun onBindSeries(searchHeaderViewHolder: SeriesViewHolder, position:Int){
        val header = itemsList[position] as SearchItems.SeriesItem
        searchHeaderViewHolder.itemSeriesBinding.item=header.series
    }
    class ComicsViewHolder(var itemComicBinding: ItemComicBinding):BaseViewHolder(itemComicBinding)
    private fun onBindComics(searchHeaderViewHolder: ComicsViewHolder, position:Int){
        val header =itemsList[position] as SearchItems.ComicItem
        searchHeaderViewHolder.itemComicBinding.item=header.comic
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
             VIEW_SEARCH_TYPE->{
                 val binding=DataBindingUtil.inflate<ItemSearchHeaderBinding>(
                     LayoutInflater.from(parent.context),
                     R.layout.item_search_header ,
                     parent,
                     false)
                 SearchHeaderViewHolder(binding)
             }
             VIEW_SERIES_TYPE->{
                 val binding=DataBindingUtil.inflate<ItemSeriesBinding>(
                     LayoutInflater.from(parent.context),
                     R.layout.item_series,
                     parent,
                     false)
                 SeriesViewHolder(binding)
             }
             VIEW_COMIC_TYPE->{
                 val binding=DataBindingUtil.inflate<ItemComicBinding>(
                     LayoutInflater.from(parent.context),
                     R.layout.item_comic,
                     parent,
                     false)
                 ComicsViewHolder(binding)
             }
             VIEW_CHARACTER_TYPE->{
                 val binding=DataBindingUtil.inflate<ItemCharacterBinding>(
                     LayoutInflater.from(parent.context),
                     R.layout.item_character,
                     parent,
                     false)
                 CharacterViewHolder(binding)
             }
            else ->throw Exception("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is SearchHeaderViewHolder -> onBindHeader(holder,position)
            is SeriesViewHolder -> onBindSeries(holder,position)
            is CharacterViewHolder ->onBindCharacter(holder,position)
            is ComicsViewHolder -> onBindComics(holder,position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position].searchItemType){
            SearchItemType.TYPE_COMICS -> VIEW_COMIC_TYPE
            SearchItemType.TYPE_CHARACTER -> VIEW_CHARACTER_TYPE
            SearchItemType.TYPE_SERIES -> VIEW_SERIES_TYPE
            SearchItemType.TYPE_Header -> VIEW_SEARCH_TYPE
        }
    }
   private companion object{
       const val VIEW_SEARCH_TYPE=0
       const val VIEW_SERIES_TYPE=1
       const val VIEW_COMIC_TYPE=2
       const val VIEW_CHARACTER_TYPE=3
   }

}


