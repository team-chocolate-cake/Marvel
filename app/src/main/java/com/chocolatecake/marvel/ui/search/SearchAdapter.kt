package com.chocolatecake.marvel.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.ui.base.BaseAdapter


class SearchAdapter( items: List<Any>, listener: SearchInteractionListener) :
    BaseAdapter<Any>(items, listener) {
    var myItems=items
    fun updateList(newItems: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtils(myItems, newItems))
        myItems = newItems
        diffResult.dispatchUpdatesTo(this)
    }
    override val layoutId: Int
        get() = R.layout.item_series
}




//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import com.chocolatecake.marvel.R
//
//import com.chocolatecake.marvel.databinding.ItemSeriesBinding
//import com.chocolatecake.marvel.ui.base.BaseAdapter
//
//class SearchAdapter(
//   val itemsList:List<SearchItems>,
//    listener:SearchInteractionListener
//    ):BaseAdapter<SearchItems>(itemsList,listener) {
//    override val layoutId: Int
//        get() = R.layout.item_series
//
//
//    class SeriesViewHolder(var itemSeriesBinding:ItemSeriesBinding ):BaseViewHolder(itemSeriesBinding)
//    private fun onBindSeries(searchHeaderViewHolder: SeriesViewHolder, position:Int){
//        val header = itemsList[position] as SearchItems.SeriesItem
//        searchHeaderViewHolder.itemSeriesBinding.item=header.series
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        return when(viewType){
//             VIEW_SERIES_HEADER->{
//                 val binding=DataBindingUtil.inflate<ItemSeriesBinding>(
//                     LayoutInflater.from(parent.context),
//                     R.layout.item_series,
//                     parent,
//                     false)
//                 SeriesViewHolder(binding)
//             }
//            else -> throw Exception("Invalid ViewType")
//        }
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        when(holder){
//            is SeriesViewHolder -> onBindSeries(holder,position)
//        }
//    }
//    override fun getItemViewType(position: Int): Int {
//        return when(itemsList[position].searchItemType){
//            SearchItemType.TYPE_SERIES -> VIEW_SERIES_HEADER
//        }
//    }
//   private companion object{
//       const val VIEW_SERIES_HEADER=1
//   }
//
//}
//
//
