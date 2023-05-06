package com.chocolatecake.marvel.ui.search

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
import com.chocolatecake.marvel.data.model.base.SearchResult
import com.chocolatecake.marvel.databinding.ItemCharacterBinding
import com.chocolatecake.marvel.databinding.ItemComicBinding


import com.chocolatecake.marvel.databinding.ItemSeriesBinding


class SearchAdapter(
    private var itemsList: List<SearchResult>,
    listener: SearchInteractionListener
) : BaseAdapter<SearchResult>(itemsList, listener) {
    override val layoutId: Int
        get() = R.layout.item_character

    fun updateList(newItems: List<SearchResult>) {
        itemsList = newItems
        notifyDataSetChanged()
    }
}