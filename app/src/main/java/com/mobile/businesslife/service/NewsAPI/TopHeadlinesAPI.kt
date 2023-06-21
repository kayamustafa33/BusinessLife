package com.mobile.businesslife.service.NewsAPI

import com.mobile.businesslife.model.News.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface TopHeadlinesAPI {

    @GET("/v2/top-headlines?country=us&category=business&apiKey=4dddd76a8883407eabfa37a58c38465d")
    suspend fun getHeadlines() : Response<NewsModel>
}