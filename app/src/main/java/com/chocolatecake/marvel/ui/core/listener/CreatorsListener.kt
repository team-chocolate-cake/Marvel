package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface CreatorsListener : BaseAdapter.BaseInteractionListener {
    fun onClickCreator(id: Int)
}