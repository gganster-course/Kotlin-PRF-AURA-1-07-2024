package com.example.prfaurajuin.services
import com.example.prfaurajuin.Task
import retrofit2.Call
import retrofit2.http.GET

interface TaskService {
    @GET("todos")
    fun getTasks(): Call<List<Task>>

}