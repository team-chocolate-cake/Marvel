package com.chocolatecake.marvel.ui.home.model

import com.chocolatecake.marvel.data.model.EventResult

sealed class HomeItem(val priority: Int){
    data class EventsItem(val eventResult: List<EventResult?>): HomeItem(0)

}