package com.example.coderem.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

     private val repo: UserRepository
      val readccdata:LiveData<User>
      val readcfdata:LiveData<User>
      val readlcdata:LiveData<User>


    init{
        val userDao= UserDatabase.getDatabase(application).userDao()
        repo = UserRepository(userDao)
        readccdata=repo.readccdata
        readcfdata=repo.readcfdata
        readlcdata=repo.readlcdata
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUser(user)
        }
    }

}