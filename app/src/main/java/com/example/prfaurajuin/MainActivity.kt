package com.example.prfaurajuin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.example.prfaurajuin.ui.TestPage
import com.example.prfaurajuin.ui.Todo_List_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainerView : FragmentContainerView
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainerView = findViewById(R.id.fragmentContainerView)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.todolist -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, Todo_List_fragment())
                    transaction.commit()
                    return@setOnItemSelectedListener true
                }
                R.id.test -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, TestPage())
                    transaction.commit()
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, Todo_List_fragment())
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}