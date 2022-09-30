package com.example.todoer.List

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoer.R

class TodoAdapter(
    private val todos: MutableList<Todo>,
    private val context: Context
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addTodo(todo: Todo) {
        todos.add(todo)
       notifyItemInserted(todos.size - 1)
    }

    fun deleteTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false,
            )
        )
    }

    private fun toggleStrikeThrough(tvTodo: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodo.paintFlags = tvTodo.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodo.paintFlags = tvTodo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        val tvTodo = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val checked = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        tvTodo.text = currentTodo.title
        checked.isChecked = currentTodo.isChecked

        toggleStrikeThrough(tvTodo, currentTodo.isChecked)
        checked.setOnCheckedChangeListener { _, checked ->
            toggleStrikeThrough(tvTodo, checked)
            currentTodo.isChecked = !currentTodo.isChecked
        }
        tvTodo.setOnClickListener {
            Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()

        }
    }

    override fun getItemCount(): Int {

        return todos.size
    }
}