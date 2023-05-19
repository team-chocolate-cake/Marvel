package com.chocolatecake.marvel.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "STORY_TABLE")
data class StoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val title: String,
    val date: String
)