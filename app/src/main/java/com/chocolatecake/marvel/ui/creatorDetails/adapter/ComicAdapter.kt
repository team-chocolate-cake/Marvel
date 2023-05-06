package com.chocolatecake.marvel.ui.creatorDetails.adapter

import android.telecom.Call.Details
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsItem
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsListener

class ComicAdapter( comicList: List<ComicsResult?>,listener: CreatorDetailsListener):
    BaseAdapter<ComicsResult?>(comicList,listener) {
    override val layoutId: Int
        get()= R.layout.comic_item

}