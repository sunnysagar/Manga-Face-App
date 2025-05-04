package com.example.mangafaceapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mangafaceapp.data.User
import com.example.mangafaceapp.data.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun UserDao() : UserDao
}