package com.example.newsbreeze.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsbreeze.model.NewsResponse

@Entity(tableName = "news_table")
class NewsEntity(
    var newsResponse: NewsResponse
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}