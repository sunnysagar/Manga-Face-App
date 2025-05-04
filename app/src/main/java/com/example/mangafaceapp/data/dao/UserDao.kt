package com.example.mangafaceapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mangafaceapp.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE email= :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}