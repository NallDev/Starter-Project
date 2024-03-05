package com.nalldev.mystarterproject.data.source.remote

import com.nalldev.mystarterproject.data.model.StartedModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    fun getAllStarter(page : Int, pageSize: Int) : Response<List<StartedModel>>
}