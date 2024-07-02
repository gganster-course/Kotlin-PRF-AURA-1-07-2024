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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prfaurajuin.R
import com.example.prfaurajuin.Task
import com.example.prfaurajuin.adapter.TaskAdapter
import com.google.android.material.textfield.TextInputEditText


class Todo_List_fragment : Fragment() {
    private lateinit var titleTaskInput : TextInputEditText
    private lateinit var descriptionTaskInput : TextInputEditText
    private lateinit var submitTask : Button
    private lateinit var taskError : TextView
    private lateinit var taskListView : RecyclerView

    private var taskListData = mutableListOf<Task>()
    private var idCounter = 0

    private lateinit var taskListViewAdapter : TaskAdapter

    private fun handleSubmit() {
        val title : String = titleTaskInput.text.toString()
        val description : String = descriptionTaskInput.text.toString()

        taskError.visibility = android.view.View.INVISIBLE

        if (title.isNotEmpty()) {
            val newTask : Task = Task(idCounter++, title, description)

            taskListData.add(newTask)
            taskListViewAdapter.notifyItemChanged(taskListData.size - 1)

            titleTaskInput.setText(null)
            descriptionTaskInput.setText(null)
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
        descriptionTaskInput = view.findViewById(R.id.descriptionTaskInput)
        submitTask = view.findViewById(R.id.submitTask)
        taskError = view.findViewById(R.id.taskError)
        taskListView = view.findViewById(R.id.taskRecyclerView)

        taskListViewAdapter = TaskAdapter(taskListData)
        taskListView.layoutManager = LinearLayoutManager(requireContext())
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