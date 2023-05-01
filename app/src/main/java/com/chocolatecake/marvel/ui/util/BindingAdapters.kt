package com.chocolatecake.marvel.ui.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter

@BindingAdapter(value =["app:items"])
fun <T> RecyclerView.setRecyclerItems(items:List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}
