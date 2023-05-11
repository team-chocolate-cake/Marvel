package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface StoryListener : BaseAdapter.BaseInteractionListener {
    fun onClickStory(id: Int)
}