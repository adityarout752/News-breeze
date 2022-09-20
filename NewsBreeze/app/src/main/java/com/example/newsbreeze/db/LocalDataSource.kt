package com.example.newsbreeze.db

import com.example.newsbreeze.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    fun readNews(): Flow<List<Article>> {
        return newsDao.readNews()
    }

    suspend fun insertNews(article: Article) {
        return newsDao.insertNews(article)
    }


}