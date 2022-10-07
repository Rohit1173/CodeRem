package com.example.coderem.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "myTable", indices = [Index(value = arrayOf("site"), unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val site:String,
    val profile:String
)