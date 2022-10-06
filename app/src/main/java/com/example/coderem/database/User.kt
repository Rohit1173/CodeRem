package com.example.coderem.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val site:String,
    val profile:String
)