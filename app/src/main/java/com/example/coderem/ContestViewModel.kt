package com.example.coderem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coderem.api.RetrofitInstance
import kotlinx.coroutines.launch

open class ContestViewModel(application: Application):AndroidViewModel(application) {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _myevent = MutableLiveData<MutableList<CodeData>>()
    val myevent: LiveData<MutableList<CodeData>> = _myevent


    init {
        getevent()
    }

    fun getevent() {

        viewModelScope.launch {
            try {
                _myevent.value =
                    RetrofitInstance.api.getCodeData()
                _status.value = "SUCCESS"


            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }

    }

}