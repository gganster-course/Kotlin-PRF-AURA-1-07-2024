package com.example.prfaurajuin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Task(val id: Int, val title: String)

class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView
    private var listItems = mutableListOf<Task>(Task(1, "hello"), Task(2, "world"))
    private lateinit var taskAdapter: ArrayAdapter<String>

    private lateinit var countText : TextView
    private lateinit var clockText : TextView
    private lateinit var countButton : Button
    private lateinit var pausePlayButton : Button

    private var isPlaying = true
    private var count = 0

    private val handler = Handler(Looper.getMainLooper())

    private val updateTimeRunnable: Runnable = object : Runnable {
        override fun run() {
            val currentTime = Calendar.getInstance().time
            val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            clockText.text = formatter.format(currentTime)
            handler.postDelayed(this, 1000)
        }
    }
    private fun onIncrement(it: View) {
        count++
        countText.text = "$count"
    }
    private fun togglePlayPause(it: View) {
        if (isPlaying) {
            //stop
            handler.removeCallbacks(updateTimeRunnable)
        } else {
            //play
            handler.post(updateTimeRunnable)
        }
        isPlaying = !isPlaying
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //---------------------------------------------//
        listView = findViewById<ListView>(R.id.myList)
        taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems.map { it.title })
        listView.adapter = taskAdapter


        listItems.add(Task(3, "everyone"))
        taskAdapter.clear()
        taskAdapter.addAll(listItems.map { it.title })
        taskAdapter.notifyDataSetChanged()

        //---------------------------------------------//

        countText = findViewById<TextView>(R.id.countText)
        clockText = findViewById<TextView>(R.id.clockText)

        handler.post(updateTimeRunnable)

        countText.text = "count: $count"

        countButton = findViewById<Button>(R.id.countButton)
        countButton.setOnClickListener { onIncrement(it) }

        pausePlayButton = findViewById(R.id.pausePlayButton)
        pausePlayButton.setOnClickListener { togglePlayPause(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimeRunnable)
    }
}