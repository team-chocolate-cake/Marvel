package com.chocolatecake.marvel.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.core.util.Consumer
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocolatecake.marvel.data.model.ImageResponse
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.search.SearchQuery
import com.chocolatecake.marvel.ui.search.SearchViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject

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

object BindingAdapters {
    @BindingAdapter("app:textAttrChanged")
    @JvmStatic
    fun bindEditText(editText: EditText, onTextChanged: Consumer<SearchQuery>) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged.accept(SearchQuery(s?.toString() ?: ""))
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}



