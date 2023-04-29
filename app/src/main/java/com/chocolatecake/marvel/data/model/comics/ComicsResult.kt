package com.chocolatecake.marvel.data.model.comics

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class ComicsResult(
    @SerializedName("characters")
    val characters: ContentResponse<ItemResponse>?,
    @SerializedName("collectedIssues")
    val collectedIssues: List<ItemResponse>? = listOf(),
    @SerializedName("collections")
    val collections: List<Any>? = listOf(),
    @SerializedName("creators")
    val creators: ContentResponse<CreatorsResponse>,
    @SerializedName("dates")
    val dates: List<Date>? = listOf(),
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("diamondCode")
    val diamondCode: String? = null,
    @SerializedName("digitalId")
    val digitalId: Int? = null,
    @SerializedName("ean")
    val ean: String? = null,
    @SerializedName("events")
    val events: ContentResponse<ItemResponse>,
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<ImageResponse>? = listOf(),
    @SerializedName("isbn")
    val isbn: String? = null,
    @SerializedName("issn")
    val issn: String? = null,
    @SerializedName("issueNumber")
    val issueNumber: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("pageCount")
    val pageCount: Int? = null,
    @SerializedName("prices")
    val prices: List<Price>? = listOf(),
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: ItemResponse? = null,
    @SerializedName("stories")
    val stories: ContentResponse<StoriesResponse>,
    @SerializedName("textObjects")
    val textObjects: List<TextObject>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("upc")
    val upc: String? = null,
    @SerializedName("urls")
    val urls: List<UrlResponse>? = listOf(),
    @SerializedName("variantDescription")
    val variantDescription: String? = null,
    @SerializedName("variants")
    val variants: List<ItemResponse>? = listOf(),
)