package com.chocolatecake.marvel.ui.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter
import android.view.View
import androidx.core.view.isVisible
import com.chocolatecake.marvel.data.util.State

@BindingAdapter(value =["app:items"])
fun <T> RecyclerView.setRecyclerItems(items:List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> View.showWhenSuccess(state: State<T>?) {
    this.isVisible = (state is State.Success)
}

@BindingAdapter(value = ["app:showWhenFailure"])
fun <T> View.showWhenFailure(state: State<T>?) {
    this.isVisible = (state is State.Failure)
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> View.showWhenLoading(state: State<T>?) {
    this.isVisible = (state is State.Loading)
}