package com.chocolatecake.marvel.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SeriesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val endYear: Int,
    val startYear: Int,
    val imageURL: String,
    val title: String
)
