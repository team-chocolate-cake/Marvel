package com.chocolatecake.marvel.data.model.stories

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class StoriesResult(
    @SerializedName("characters")
    val characters: ContentResponse<ItemResponse>?,
    @SerializedName("comics")
    val comics: ContentResponse<ItemResponse>?,
    @SerializedName("creators")
    val creators: ContentResponse<CreatorsResponse>?,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val events: ContentResponse<ItemResponse>?,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("originalIssue")
    val originalIssue: ItemResponse?,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: ContentResponse<ItemResponse>?,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null
)