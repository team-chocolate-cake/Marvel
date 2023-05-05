package com.chocolatecake.marvel.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.ItemBannerListBinding
import com.chocolatecake.marvel.databinding.ItemSeriesListBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.home.model.HomeItem
import com.chocolatecake.marvel.ui.home.view.HomeListener
import com.chocolatecake.marvel.ui.home.view.HomeViewPagerLayoutManager

class HomeAdapter(private var itemsHome: MutableList<HomeItem>, private val listener: HomeListener) :
    BaseAdapter<HomeItem>(itemsHome, listener) {
    override val layoutId: Int
        get() = 0

    fun setItem(item: HomeItem) {
        val newItems = itemsHome.apply {
            item.priority.takeIf { it != 2 }?.run {
                removeAt(item.priority)
            }
            add(item.priority, item)
        }
        setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_banner_list,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_SERIES -> {
                SeriesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_series_list,
                        parent,
                        false
                    )
                )
            }

            else -> throw Exception("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (itemsHome.isEmpty() || position == -1) {
            return
        }
        when (itemsHome[position]) {
            is HomeItem.EventsItem -> bindHeader(holder as HeaderViewHolder, position)
            is HomeItem.SeriesItem -> bindSeries(holder as SeriesViewHolder, position)
            else -> throw Exception("UNKNOWN VIEW TYPE")
        }
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val events = itemsHome[position] as HomeItem.EventsItem
        val adapter = BannerAdapter(events.eventResult, listener)
        holder.binding.recyclerViewBanner.adapter = adapter
        holder.binding.recyclerViewBanner.onFlingListener = null
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(holder.binding.recyclerViewBanner)
        holder.binding.recyclerViewBanner.layoutManager =
            HomeViewPagerLayoutManager(holder.binding.root.context)
    }

    private fun bindSeries(holder: SeriesViewHolder, position: Int) {
        val series = itemsHome[position] as HomeItem.SeriesItem
        val adapter = SeriesAdapter(series.seriesResult, listener)
        holder.binding.recyclerViewSeries.adapter = adapter
        holder.binding.listener = listener
    }

    override fun setItems(newItems: List<HomeItem>) {
        itemsHome = newItems.sortedBy { it.priority }.toMutableList()
        super.setItems(newItems)
    }

    override fun getItemViewType(position: Int): Int {
        if (itemsHome.isNotEmpty()) {
            return (itemsHome[position].priority)
        }
        return -1
    }


    class HeaderViewHolder(val binding: ItemBannerListBinding) : BaseViewHolder(binding)
    class SeriesViewHolder(val binding: ItemSeriesListBinding) : BaseViewHolder(binding)

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_SERIES = 1
    }

}