package com.mobile.businesslife.service

import com.mobile.businesslife.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface EverythingAPI {

    @GET("/v2/everything?domains=wsj.com&apiKey=4dddd76a8883407eabfa37a58c38465d")
    suspend fun getEverything() : Response<NewsModel>

}