package com.example.coderem.database.database2

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coderem.database.database1.User

@Dao
interface CheckBoxDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckBox(checkBox: CheckBox)

    @Delete
    suspend fun deleteCheckBox(checkBox: CheckBox)


    @Query("SELECT * FROM checkBoxTable WHERE contest=:contest ")
    fun getCheckBoxData(contest:String): LiveData<CheckBox>
}