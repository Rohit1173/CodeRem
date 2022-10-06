package com.example.coderem.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)



    @Query("SELECT * FROM myTable WHERE site='CodeChef' ")
    fun getcodechef():LiveData<User>

    @Query("SELECT * FROM myTable WHERE site='CodeForces' ")
    fun getcodeforces():LiveData<User>

    @Query("SELECT * FROM myTable WHERE site='LeetCode' ")
    fun getleetcode():LiveData<User>
}