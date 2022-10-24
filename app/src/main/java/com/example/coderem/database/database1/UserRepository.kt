package com.example.coderem.database.database1

import androidx.lifecycle.LiveData

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