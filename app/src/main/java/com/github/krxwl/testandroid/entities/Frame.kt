package com.github.krxwl.testandroid.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frames")
data class Frame(@PrimaryKey val id: Int,
    val bitmap: Int,
    val firstDescription: Int,
    val secondDescription: Int)