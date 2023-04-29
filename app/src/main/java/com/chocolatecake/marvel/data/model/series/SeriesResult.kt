package com.chocolatecake.marvel.data.model.series

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class SeriesResult(
    @SerializedName("characters")
    val characters: ContentResponse<ItemResponse>?,
    @SerializedName("comics")
    val comics: ContentResponse<ItemResponse>?,
    @SerializedName("creators")
    val creators: ContentResponse<CreatorsResponse>?,
    @SerializedName("description")
    val description: Any? = Any(),
    @SerializedName("endYear")
    val endYear: Int? = null,
    @SerializedName("events")
    val events: ContentResponse<ItemResponse>?,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("next")
    val next: Any? = Any(),
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("startYear")
    val startYear: Int? = null,
    @SerializedName("stories")
    val stories: ContentResponse<StoriesResponse>?,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("urls")
    val urls: List<UrlResponse>? = listOf(),
)