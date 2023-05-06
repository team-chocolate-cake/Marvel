package com.chocolatecake.marvel.data.model

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class EventResult(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
)