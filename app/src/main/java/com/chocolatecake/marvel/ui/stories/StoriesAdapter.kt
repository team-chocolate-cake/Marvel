package com.chocolatecake.marvel.ui.stories

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.StoryListener

class StoriesAdapter(
    storiesList: List<StoryDto>,
    listener : StoryListener,
) :BaseAdapter<StoryDto>(storiesList,listener) {

    override val layoutId: Int = R.layout.item_story
}