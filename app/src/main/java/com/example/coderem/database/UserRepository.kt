package com.example.coderem.database

import androidx.lifecycle.LiveData
import com.example.coderem.database.User
import com.example.coderem.database.UserDao

class UserRepository(private val userDao: UserDao){

    val readccdata:LiveData<User> = userDao.getcodechef()
    val readcfdata:LiveData<User> = userDao.getcodeforces()
    val readlcdata:LiveData<User> = userDao.getleetcode()
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}