package com.example.todo30.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo_Table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "Task")
    val task: String,

    @ColumnInfo(name = "Checkbox_State")
    val isChecked: Boolean =false
)
