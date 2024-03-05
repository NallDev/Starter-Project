package com.nalldev.mystarterproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starter_table")
data class StartedModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)