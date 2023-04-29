package com.chocolatecake.marvel.data.model.character

import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("comics")
    val comics: BaseContent<BaseItem>?,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val events: BaseContent<BaseItem>?,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: BaseContent<BaseItem>?,
    @SerializedName("stories")
    val stories: BaseContent<BaseStories>?,
    @SerializedName("thumbnail")
    val thumbnail: BaseImage?,
    @SerializedName("urls")
    val urls: List<BaseUrl>? = listOf(),
)