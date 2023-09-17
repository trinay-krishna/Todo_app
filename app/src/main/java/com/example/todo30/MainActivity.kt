package com.example.todo30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo30.db.Todo
import com.example.todo30.db.TodoDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var etTodo: EditText
    private lateinit var btnadd: Button
    private lateinit var viewModel: TodoViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RecyclerviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etTodo=findViewById(R.id.etTodo)
        btnadd=findViewById(R.id.btnadd)
        recyclerview=findViewById(R.id.rvTodos)
        val dao=TodoDatabase.getInstance(application).TodoDao()
        val factory=TodoViewModelFactory(dao)
        viewModel=ViewModelProvider(this,factory).get(TodoViewModel::class.java)

        btnadd.setOnClickListener{
            if (validate()){
                addTodo()
                Clear()
            }
        }
        initRecyclerView()

    }
    private fun addTodo(){
        val task=etTodo.text.toString()
        viewModel.addTodo(
            Todo(
                0,
                task
            )
        )
    }
    private fun validate(): Boolean{
        if(etTodo.text.toString().isNullOrEmpty()){
            Toast.makeText(
                this,
                "Enter Task!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun Clear(){
        etTodo.text.clear()
    }

    private fun initRecyclerView(){
        recyclerview.layoutManager=LinearLayoutManager(this)
        adapter= RecyclerviewAdapter{
            selecteditem:Todo->onChecked(selecteditem)
        }
        recyclerview.adapter=adapter
        displayTodos()
    }
    private fun displayTodos(){
        viewModel.todos.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun onChecked(todo: Todo){
        showDialog(todo)
    }

    private fun showDialog(todo: Todo){
        val dialog=AlertDialog.Builder(this)
            .setTitle("Task Finished!")
            .setMessage("Do you want to delete the finished task?")
            .setIcon(R.drawable.baseline_check)
            .setPositiveButton("Yes"){_,_->
                viewModel.deleteTodo(
                    Todo(
                    todo.id,
                    todo.task,
                    todo.isChecked
                    )
                )
                Toast.makeText(
                    this,
                    "Task Finished!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("No"){_,_->

            }.create()
        dialog.show()
    }
}