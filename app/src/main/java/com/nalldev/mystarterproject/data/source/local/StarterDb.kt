package com.nalldev.mystarterproject.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StarterDb::class], version = 1)
abstract class StarterDb : RoomDatabase() {
    abstract fun restaurantDao(): StartedDao
}