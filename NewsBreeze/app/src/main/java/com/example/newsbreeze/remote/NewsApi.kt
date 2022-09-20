package com.example.newsbreeze.remote

import com.example.newsbreeze.model.NewsResponse
import com.example.newsbreeze.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country:String="us",
        @Query("apiKey")apikey:String=Constant.API_KEY
    ): Response<NewsResponse>
}