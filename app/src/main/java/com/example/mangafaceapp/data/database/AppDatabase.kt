package com.example.mangafaceapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mangafaceapp.data.dao.MangaDao
import com.example.mangafaceapp.data.model.User
import com.example.mangafaceapp.data.dao.UserDao
import com.example.mangafaceapp.data.model.Manga

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao

}