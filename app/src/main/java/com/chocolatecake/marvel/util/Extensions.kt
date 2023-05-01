package com.chocolatecake.marvel.util

import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter

fun <T> RecyclerView.setRecyclerItems(items:List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}
