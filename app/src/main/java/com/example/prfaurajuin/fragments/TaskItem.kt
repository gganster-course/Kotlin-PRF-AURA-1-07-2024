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

class TaskItem : Fragment() {

    private var taskTitle: String? = null

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       arguments?.let {
           taskTitle = it.getString(ARG_TASK_TITLE)
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

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(taskTitle: String) = TaskItem().apply {
            arguments = Bundle().apply {
                putString(ARG_TASK_TITLE, taskTitle)
            }
        }
    }
}