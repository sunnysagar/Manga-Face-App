package com.example.mangafaceapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mangafaceapp.data.dao.MangaDao
import com.example.mangafaceapp.data.dao.UserDao
import com.example.mangafaceapp.data.model.Manga
import com.example.mangafaceapp.data.model.User

@Database(entities = [Manga::class], version = 1)
@TypeConverters(Converters::class) // Add the TypeConverter here
abstract class MangaDatabase: RoomDatabase() {
    abstract fun mangaDao() : MangaDao
}