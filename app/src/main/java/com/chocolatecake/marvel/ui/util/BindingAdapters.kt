package com.chocolatecake.marvel.ui.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter

@BindingAdapter(value =["app:items"])
 fun <T> setRecyclerItems(view:RecyclerView,items:List<T>?) = if(items!=null){
     (view.adapter as BaseAdapter<T>).setItems(items)
 }else (view.adapter as BaseAdapter<T>).setItems(emptyList())

