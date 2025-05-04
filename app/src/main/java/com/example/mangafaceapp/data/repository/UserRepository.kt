package com.example.mangafaceapp.data.repository

import com.example.mangafaceapp.data.model.User
import com.example.mangafaceapp.data.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getUserByEmail(email: String): User?{
        return userDao.getUserByEmail(email)
    }

    suspend fun insertUser(user: User){
        userDao.insert(user)
    }
}