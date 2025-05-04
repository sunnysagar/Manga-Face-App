package com.example.mangafaceapp.data.repository

import com.example.mangafaceapp.data.User
import com.example.mangafaceapp.data.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun getUserByEmail(email: String): User?{
        return userDao.getUserByEmail(email)
    }

    suspend fun insertuser(user: User){
        userDao.insert(user)
    }
}