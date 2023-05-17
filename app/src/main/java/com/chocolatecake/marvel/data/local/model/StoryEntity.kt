package com.chocolatecake.marvel.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val title: String,
    val date: String
)