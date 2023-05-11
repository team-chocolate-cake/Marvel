package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface EventListener : BaseAdapter.BaseInteractionListener {
    fun onClickEvent(id: Int)
}