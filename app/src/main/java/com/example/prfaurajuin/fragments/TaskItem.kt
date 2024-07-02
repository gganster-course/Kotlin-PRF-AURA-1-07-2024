package com.example.prfaurajuin.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.prfaurajuin.R

private const val ARG_TASK_TITLE = "task_title"
private const val ARG_TASK_DESCRIPTION = "task_description"

class TaskItem : Fragment() {

    private var taskTitle: String? = null
    private var taskDescription: String? = null

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       arguments?.let {
           taskTitle = it.getString(ARG_TASK_TITLE)
           taskDescription = it.getString(ARG_TASK_DESCRIPTION)
       }
   }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_item, container, false)

        val textviewTitle: TextView = view.findViewById<TextView>(R.id.taskTitle)
        textviewTitle.text = taskTitle

        val textviewDescription: TextView = view.findViewById<TextView>(R.id.taskDescription)
        textviewDescription.text = taskDescription

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(taskTitle: String) = TaskItem().apply {
            arguments = Bundle().apply {
                putString(ARG_TASK_TITLE, taskTitle)
                putString(ARG_TASK_DESCRIPTION, taskDescription)
            }
        }
    }
}