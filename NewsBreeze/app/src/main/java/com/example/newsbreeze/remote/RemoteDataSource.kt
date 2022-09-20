package com.example.newsbreeze.remote

import com.example.newsbreeze.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
    //hilt will search for this specific type in Network Module,and search for function
    //which return same type
) {
    suspend fun getBreakingNews(country:String): Response<NewsResponse> {
        return newsApi.getBreakingNews()
    }


}