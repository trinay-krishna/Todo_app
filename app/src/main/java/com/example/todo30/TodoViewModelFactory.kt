package com.example.todo30

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo30.db.TodoDao
import java.lang.IllegalArgumentException

class TodoViewModelFactory(private val Dao: TodoDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(Dao) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}