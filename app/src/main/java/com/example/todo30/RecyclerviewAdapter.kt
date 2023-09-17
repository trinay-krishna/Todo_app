package com.example.todo30

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo30.db.Todo

class RecyclerviewAdapter(
    private val onCheck: (Todo)->Unit
): RecyclerView.Adapter<viewHolder>() {

    private val todos=ArrayList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(todos[position],onCheck)
    }

    fun setList(todoList:List<Todo>){
       todos.clear()
       todos.addAll(todoList)
    }

}

class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(todo: Todo,onCheck:(Todo)->Unit){
        val task=itemView.findViewById<TextView>(R.id.tvTask)
        val cb=itemView.findViewById<CheckBox>(R.id.cbDone)
        task.text=todo.task
        cb.isChecked=todo.isChecked
        cb.isChecked=todo.isChecked
        cb.setOnCheckedChangeListener{_,isChecked->
            if(isChecked){
                onCheck(todo)
            }
        }
    }
}