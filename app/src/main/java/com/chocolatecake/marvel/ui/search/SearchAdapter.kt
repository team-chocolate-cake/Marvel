package com.chocolatecake.marvel.ui.search

import android.view.ViewGroup
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SearchAdapter(
    itemsList: MutableList<SearchType>,
    listener:SearchInteractionListener
    ):BaseAdapter<SearchType>(itemsList,listener) {
    override val layoutId: Int
        get() = R.layout.item_comic





    class item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
             VIEW_SEARCH_TYPE->{

             }
             VIEW_SERIES_TYPE->{}
             VIEW_COMIC_TYPE->{}
             VIEW_CHARACTER_TYPE->{}
            else ->throw Exception("Illegal")

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
   private companion object{
       const val VIEW_SEARCH_TYPE=0
       const val VIEW_SERIES_TYPE=1
       const val VIEW_COMIC_TYPE=2
       const val VIEW_CHARACTER_TYPE=3
   }

}


