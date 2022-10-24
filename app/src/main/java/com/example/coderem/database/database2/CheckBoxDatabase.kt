package com.example.coderem.database.database2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserDao
import com.example.coderem.database.database1.UserDatabase

@Database(entities = [CheckBox::class], version = 1, exportSchema = false)
abstract class CheckBoxDatabase :RoomDatabase(){
    abstract fun CheckBoxDao(): CheckBoxDao

    companion object {
        @Volatile
        private var INSTANCE: CheckBoxDatabase? = null

        fun getDatabase(context: Context): CheckBoxDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CheckBoxDatabase::class.java,
                    "checkbox_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}