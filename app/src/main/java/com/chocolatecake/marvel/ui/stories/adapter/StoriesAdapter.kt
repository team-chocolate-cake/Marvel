package com.chocolatecake.marvel.ui.stories.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.StoryResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.StoryListener

class StoriesAdapter(
    storiesList: List<StoryResult>,
    listener : StoryListener,
) :BaseAdapter<StoryResult>(storiesList,listener) {

    override val layoutId: Int = R.layout.item_story
}