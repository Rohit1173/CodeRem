package com.example.coderem.database.database2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel

class CheckBoxViewModelFactory(val contest:String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return if (modelClass.isAssignableFrom(CheckBoxViewModel::class.java)) {
            ProfileViewModel(contest) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}