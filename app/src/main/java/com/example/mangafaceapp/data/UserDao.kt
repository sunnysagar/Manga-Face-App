package com.example.mangafaceapp.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE email= :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}