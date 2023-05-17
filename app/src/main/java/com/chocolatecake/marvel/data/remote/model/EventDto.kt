package com.chocolatecake.marvel.data.remote.model

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?= null,
    @SerializedName("title")
    val title: String? = null,
)
