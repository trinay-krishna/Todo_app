package com.example.todo30.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun TodoDao(): TodoDao
    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase?=null

        fun getInstance(context: Context): TodoDatabase{
            synchronized(this){
                var Instance= INSTANCE
                if(Instance==null){
                    Instance= Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "Todo_Database"
                    ).build()
                }
                return Instance

            }
        }
    }
}