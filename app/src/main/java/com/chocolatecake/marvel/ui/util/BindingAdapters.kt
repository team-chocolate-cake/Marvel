package com.chocolatecake.marvel.ui.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.marvel.ui.base.BaseAdapter
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.chocolatecake.marvel.data.model.ImageResponse
import com.chocolatecake.marvel.data.util.Status

@BindingAdapter(value =["app:items"])
fun <T> RecyclerView.setRecyclerItems(items:List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}


@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> View.showWhenSuccess(status: Status<T>?) {
    this.isVisible = (status is Status.Success)
}

@BindingAdapter(value = ["app:showWhenFailure"])
fun <T> View.showWhenFailure(status: Status<T>?) {
    this.isVisible = (status is Status.Failure)
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> View.showWhenLoading(status: Status<T>?) {
    this.isVisible = (status is Status.Loading)
}
@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage(imageResponse: ImageResponse?) {
    val url = if (imageResponse?.path?.split("/")?.last() == "image_not_available") {
        "https://fandomwire.com/wp-content/uploads/2018/07/Marvel-Logo-14.jpg"
    } else {
        imageResponse?.toUrl()
    }
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(this)
}