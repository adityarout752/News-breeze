package com.example.newsbreeze.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsbreeze.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(article: Article)

    @Query("SELECT * FROM articles")
    fun readNews(): Flow<List<Article>>


}