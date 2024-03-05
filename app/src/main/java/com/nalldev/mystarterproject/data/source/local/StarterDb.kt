package com.nalldev.mystarterproject.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nalldev.mystarterproject.data.model.StartedModel

@Database(entities = [StartedModel::class], version = 1, exportSchema = false)
abstract class StarterDb : RoomDatabase() {
    abstract fun restaurantDao(): StartedDao
}