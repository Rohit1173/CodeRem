package com.example.coderem.database.database2

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "checkBoxTable")
data class CheckBox(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val contest:String,
    val check:Boolean
)
