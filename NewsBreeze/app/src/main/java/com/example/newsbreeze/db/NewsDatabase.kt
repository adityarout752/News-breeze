package com.example.newsbreeze.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.utils.NewsTypeConverter

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}