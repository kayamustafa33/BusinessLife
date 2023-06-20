package com.mobile.businesslife.model

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)