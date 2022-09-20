package com.example.newsbreeze.utils

import com.example.newsbreeze.db.NewsEntity
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.model.NewsResponse

interface onItemClicked {

    fun readData(article: Article)

    fun saveData(article: Article)


}