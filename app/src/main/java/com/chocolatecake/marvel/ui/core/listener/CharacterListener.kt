package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface CharacterListener : BaseAdapter.BaseInteractionListener{
    fun onClickCharacter(id:Int)
}