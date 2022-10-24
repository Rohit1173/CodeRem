package com.example.coderem.database.database2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckBoxViewModel( application: Application):AndroidViewModel(application) {

    private lateinit var repo: CheckBoxRepository
    lateinit var readCheckBoxData: LiveData<CheckBox>
    var checkBoxDao:CheckBoxDao


    init{
         checkBoxDao= CheckBoxDatabase.getDatabase(application).CheckBoxDao()
    }
    fun addCheckBox(checkBox: CheckBox){
        repo = CheckBoxRepository(checkBoxDao,checkBox.contest)
        readCheckBoxData=repo.readCheckBoxData
        viewModelScope.launch(Dispatchers.IO) {
            repo.addCheckBox(checkBox)
        }
    }
    fun deleteCheckBox(checkBox: CheckBox){
        repo = CheckBoxRepository(checkBoxDao,checkBox.contest)
        readCheckBoxData=repo.readCheckBoxData
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCheckBox(checkBox)
        }
    }
}