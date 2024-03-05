package com.nalldev.mystarterproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starter_table")
data class StartedModel(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)