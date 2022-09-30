package com.example.todoer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoer.List.Todo
import com.example.todoer.List.TodoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var btnAdd: Button
    private lateinit var btnDelete: Button
    private lateinit var etTodoText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupButtons()

        todoAdapter = TodoAdapter(mutableListOf(), this)
        rv.adapter = todoAdapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButtons(){

        btnAdd.setOnClickListener {
            val todoTitle = etTodoText.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoText.text.clear()
            }
        }

        btnDelete.setOnClickListener {
            todoAdapter.deleteTodos()
        }

    }

    private fun setupViews() {
        rv = findViewById(R.id.rvTodoList)
        btnAdd = findViewById(R.id.btnAddTodoTitle)
        etTodoText = findViewById(R.id.etTodoTitle)
        btnDelete = findViewById(R.id.btnDeleteTodoTitle)
    }
}