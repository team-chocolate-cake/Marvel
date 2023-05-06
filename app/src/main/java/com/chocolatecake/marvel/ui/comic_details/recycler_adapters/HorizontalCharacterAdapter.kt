package com.chocolatecake.marvel.ui.comic_details.recycler_adapters

import com.chocolatecake.marvel.BR
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.comic_details.ComicInteractionListener

class HorizontalCharacterAdapter(
    items: List<SeriesResult?>,
    listener: ComicInteractionListener
) : BaseAdapter<SeriesResult?>(items,listener) {

    override val layoutId: Int = R.layout.character_item
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val currentItem = getItems()[position]
        if (holder is ItemViewHolder){
            holder.binding.setVariable(BR.myItem, currentItem)
        }
    }

}