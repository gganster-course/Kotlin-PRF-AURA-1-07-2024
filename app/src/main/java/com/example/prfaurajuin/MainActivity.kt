package com.example.prfaurajuin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var titleTaskInput : TextInputEditText
    private lateinit var submitTask : Button
    private lateinit var taskError : TextView
    private lateinit var taskListView : ListView

    private var taskListData = mutableListOf<Task>()
    private var idCounter = 0

    private lateinit var taskListViewAdapter : ArrayAdapter<String>

    private fun handleSubmit() {
        val title : String = titleTaskInput.text.toString()

        taskError.visibility = android.view.View.INVISIBLE

        if (title.isNotEmpty()) {
            val newTask : Task = Task(idCounter++, title)

            taskListData.add(newTask)
            taskListViewAdapter.clear()
            taskListViewAdapter.addAll(taskListData.map { it.title })
            taskListViewAdapter.notifyDataSetChanged()

            titleTaskInput.setText(null)
        } else {
            taskError.visibility = android.view.View.VISIBLE
            taskError.text = "Entrez un titre svp"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTaskInput = findViewById(R.id.titleTaskInput)
        submitTask = findViewById(R.id.submitTask)
        taskError = findViewById(R.id.taskError)
        taskListView = findViewById(R.id.taskListView)

        taskListViewAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            taskListData.map { it.title }
        )
        taskListView.adapter = taskListViewAdapter

        taskError.visibility = android.view.View.INVISIBLE

        submitTask.setOnClickListener { handleSubmit() }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}