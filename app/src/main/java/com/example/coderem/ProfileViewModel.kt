package com.example.coderem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coderem.api.User_retrofitInstance
import kotlinx.coroutines.launch

class ProfileViewModel(val profile:String): ViewModel() {

     val myResponse:MutableLiveData<CCdata> = MutableLiveData()
        fun getPost(profile: String){
            viewModelScope.launch {
              myResponse.value=User_retrofitInstance.api.getuserCcData(profile)
            }
        }
}