package com.nalldev.mystarterproject.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nalldev.mystarterproject.data.model.StartedModel
import com.nalldev.mystarterproject.data.source.remote.ApiService

class StarterPaging (
    private val apiService: ApiService
) : PagingSource<Int, StartedModel>() {
    override fun getRefreshKey(state: PagingState<Int, StartedModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StartedModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllStarter(page, params.loadSize)
            if (response.isSuccessful) {
                val starter = response.body() ?: emptyList()
                LoadResult.Page(
                    data = starter,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (starter.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (exception : Exception) {
            LoadResult.Error(exception)
        }
    }
}