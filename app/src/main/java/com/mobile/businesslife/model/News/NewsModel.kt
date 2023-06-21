package com.mobile.businesslife.model.News

import com.mobile.businesslife.model.News.Article

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)