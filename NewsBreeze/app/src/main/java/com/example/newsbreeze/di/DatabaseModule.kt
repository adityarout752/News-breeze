package com.example.newsbreeze.di

import android.content.Context
import androidx.room.Room
import com.example.newsbreeze.db.NewsDao
import com.example.newsbreeze.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_table"
        ).build()


    @Singleton
    @Provides
    fun providesDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }
}