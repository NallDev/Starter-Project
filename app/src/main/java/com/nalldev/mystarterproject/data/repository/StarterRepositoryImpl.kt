package com.nalldev.mystarterproject.data.repository

import android.content.SharedPreferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nalldev.mystarterproject.data.model.StartedModel
import com.nalldev.mystarterproject.data.source.paging.StarterPaging
import com.nalldev.mystarterproject.data.source.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : StarterRepository {
    override fun getStarter(page: Int, pageSize: Int): Flow<PagingData<StartedModel>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {StarterPaging(apiService)}
        ).flow
    }

    override suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLogin", isLoggedIn).apply()
    }

    override suspend fun getIsLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLogin", false)
    }
}