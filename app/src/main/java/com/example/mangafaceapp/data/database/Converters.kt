package com.example.mangafaceapp.data.database

import androidx.room.TypeConverter

class Converters {

    // Convert List<String> to String
    @TypeConverter
    fun fromList(value: List<String>): String {
        return value.joinToString(",")
    }

    // Convert String to List<String>
    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(",")
    }
}
