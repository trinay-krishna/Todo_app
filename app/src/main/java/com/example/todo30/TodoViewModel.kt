package com.example.todo30

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo30.db.Todo
import com.example.todo30.db.TodoDao
import kotlinx.coroutines.launch

class TodoViewModel(
    private val Dao: TodoDao
): ViewModel() {

    val todos=Dao.getList()

    fun addTodo(todo: Todo)=viewModelScope.launch {
        Dao.addTodo(todo)
    }

    fun deleteTodo(todo: Todo)=viewModelScope.launch {
        Dao.deleteTodo(todo)
    }


}