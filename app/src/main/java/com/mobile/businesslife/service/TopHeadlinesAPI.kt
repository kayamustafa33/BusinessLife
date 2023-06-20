package com.mobile.businesslife.service

import com.mobile.businesslife.model.Article
import com.mobile.businesslife.model.NewsModel
import com.mobile.businesslife.model.NewsTopHeadlines
import retrofit2.Response
import retrofit2.http.GET

interface TopHeadlinesAPI {

    @GET("/v2/top-headlines?country=us&category=business&apiKey=4dddd76a8883407eabfa37a58c38465d")
    suspend fun getHeadlines() : Response<NewsModel>
}