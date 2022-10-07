package com.example.coderem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coderem.api.User_retrofitInstance
import kotlinx.coroutines.launch

class ProfileViewModel(val profile:String): ViewModel() {

     val ccResponse:MutableLiveData<ProfileStatus> = MutableLiveData()
     val cfResponse:MutableLiveData<ProfileStatus> = MutableLiveData()
     val lcResponse:MutableLiveData<ProfileStatus> = MutableLiveData()
    val ccdataResponse:MutableLiveData<CCdata> = MutableLiveData()
    val cfdataResponse:MutableLiveData<CFdata> = MutableLiveData()
    val lcdataResponse:MutableLiveData<LCdata> = MutableLiveData()

        fun getCcStatus(profile: String){
            viewModelScope.launch {
              ccResponse.value=User_retrofitInstance.api.getuserCcStatus(profile)
            }
        }
    fun getCfStatus(profile: String){
        viewModelScope.launch {
            cfResponse.value=User_retrofitInstance.api.getuserCfStatus(profile)
        }
    }
    fun getLcStatus(profile: String){
        viewModelScope.launch {
            lcResponse.value=User_retrofitInstance.api.getuserLcStatus(profile)
        }
    }

    fun getCcData(profile: String){
        viewModelScope.launch {
            ccdataResponse.value=User_retrofitInstance.api.getuserCcData(profile)
        }
    }
    fun getCfData(profile: String){
        viewModelScope.launch {
            cfdataResponse.value=User_retrofitInstance.api.getuserCfData(profile)
        }
    }
    fun getLcData(profile: String){
        viewModelScope.launch {
            lcdataResponse.value=User_retrofitInstance.api.getuserLcData(profile)
        }
    }

}