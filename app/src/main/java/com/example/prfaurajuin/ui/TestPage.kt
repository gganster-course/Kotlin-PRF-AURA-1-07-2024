package com.example.prfaurajuin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.prfaurajuin.R
import com.example.prfaurajuin.RetrofitClient
import com.example.prfaurajuin.Task
import com.example.prfaurajuin.services.TaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TestPage : Fragment() {
    private lateinit var  callButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun fetchTasks() {
        val taskService = RetrofitClient.retrofit.create(TaskService::class.java)
        val call = taskService.getTasks()

        call.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                val tasks = response.body()

                tasks?.forEach {
                    Log.i("Guilian", it.title)
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.w("Guilian", "Erreur d'appel API")
            }

        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_test_page, container, false)

        callButton = view.findViewById(R.id.call)

        callButton.setOnClickListener {
            fetchTasks()
        }
        return view
    }
}