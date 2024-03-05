package com.nalldev.mystarterproject.data.repository

import androidx.paging.PagingData
import com.nalldev.mystarterproject.data.model.StartedModel
import kotlinx.coroutines.flow.Flow

interface StarterRepository {
    fun getStarter(page : Int, pageSize: Int) : Flow<PagingData<StartedModel>>
    suspend fun setIsLoggedIn(isLoggedIn: Boolean)
    suspend fun getIsLoggedIn(): Boolean
}