package com.chocolatecake.marvel.data.model.creators

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class CreatorsResult(
    @SerializedName("comics")
    val comics: ContentResponse<ItemResponse>?,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val events: ContentResponse<ItemResponse>?,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: ContentResponse<ItemResponse>?,
    @SerializedName("stories")
    val stories: ContentResponse<StoriesResponse>?,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("urls")
    val urls: List<UrlResponse>? = listOf(),
)