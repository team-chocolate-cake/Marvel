package com.chocolatecake.marvel.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T : Any> Single<T>.observeOnMainThread(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(
        owner
    ) {
        it?.let(observer)
    }
}

fun MutableList<ComicDetailsItem>.addSorted(comicDetailsItem: ComicDetailsItem): List<ComicDetailsItem> {
    this.add(comicDetailsItem)
   return this.sortedBy {
        it.sortCondition
    }
}
