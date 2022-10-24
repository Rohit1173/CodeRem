package com.example.coderem.database.database2

import androidx.lifecycle.LiveData

class CheckBoxRepository(private val checkBoxDao: CheckBoxDao,contest:String) {

    val readCheckBoxData: LiveData<CheckBox> = checkBoxDao.getCheckBoxData(contest)

    suspend fun addCheckBox(checkBox: CheckBox){
        checkBoxDao.addCheckBox(checkBox)
    }
    suspend fun deleteCheckBox(checkBox: CheckBox){
        checkBoxDao.deleteCheckBox(checkBox)
    }
}