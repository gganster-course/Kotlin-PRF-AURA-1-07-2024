package com.example.prfaurajuin.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.prfaurajuin.R
import com.example.prfaurajuin.Task
import com.google.android.material.textfield.TextInputEditText


class Todo_List_fragment : Fragment() {
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo__list_fragment, container, false)

        titleTaskInput = view.findViewById(R.id.titleTaskInput)
        submitTask = view.findViewById(R.id.submitTask)
        taskError = view.findViewById(R.id.taskError)
        taskListView = view.findViewById(R.id.taskListView)

        taskListViewAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            taskListData.map { it.title }
        )
        taskListView.adapter = taskListViewAdapter

        taskError.visibility = android.view.View.INVISIBLE

        submitTask.setOnClickListener { handleSubmit() }
        titleTaskInput.setOnKeyListener { v, keyCode, event ->
            Log.i("guilian", keyCode.toString())
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                handleSubmit()
                true
            } else {
                false
            }
        }

        return view
    }

}