package com.nalldev.mystarterproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nalldev.mystarterproject.data.model.StartedModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StartedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data : List<StartedModel>)

    @Query("SELECT * FROM starter_table")
    fun getAllStarter() : Flow<List<StartedModel>>

    @Query("DELETE FROM starter_table")
    suspend fun deleteAll()
}