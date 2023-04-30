package com.chocolatecake.marvel.ui.util

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter

fun Disposable.add(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}

fun <T> RecyclerView.setRecyclerItems(items:List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}