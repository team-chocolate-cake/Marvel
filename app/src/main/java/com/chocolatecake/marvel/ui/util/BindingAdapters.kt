package com.chocolatecake.marvel.ui.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.bumptech.glide.Glide
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.ImageResponse
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.search.model.SearchItemType
import com.google.android.material.chip.Chip

//region recycler
@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}
//endregion

//region visibility
@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> View.showWhenSuccess(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Success) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenFailure"])
fun <T> View.showWhenFailure(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Failure) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> View.showWhenLoading(status: Status<T>?) {
    val transition = Fade()
    TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
    visibility = if (status is Status.Loading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("app:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    visibility = if (isVisible == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
//endregion

//region image
@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage(imageResponse: ImageResponse?) {
    Glide.with(context)
        .load(imageResponse?.toUrl())
        .thumbnail(Glide.with(context).load(R.raw.loading))
        .fitCenter()
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["app:imageUrlWithUrl"])
fun ImageView.loadImageWithUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .thumbnail(Glide.with(context).load(R.raw.loading))
        .fitCenter()
        .centerCrop()
        .into(this)
}
//endregion

//region custom 2 way data binding
@BindingAdapter("selectedType")
fun Chip.setSelectedEnum(selectedEnum: SearchItemType) {
    isChecked = ("TYPE_" + text.toString().uppercase() == selectedEnum.toString())
}

@BindingAdapter("selectedTypeAttrChanged")
fun Chip.setSelectedEnumListener(listener: InverseBindingListener?) {
    setOnCheckedChangeListener { _, _ -> listener?.onChange() }
}

@InverseBindingAdapter(attribute = "selectedType")
fun Chip.getSelectedEnum(): SearchItemType {
    return enumValueOf("TYPE_" + text.toString().uppercase())
}
//endregion