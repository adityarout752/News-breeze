package com.example.newsbreeze.utils

import androidx.room.TypeConverter
import com.example.newsbreeze.model.Source

class NewsTypeConverter {


    @TypeConverter
    fun fromSource(source: Source): String? {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }


}