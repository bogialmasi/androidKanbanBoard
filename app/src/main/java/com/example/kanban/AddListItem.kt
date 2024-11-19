package com.example.kanban

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddListItem : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_list_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_list_item_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO create list of todo items + adapter
        // LIST FRAGMENT

        val addListItemBtn: Button = findViewById(R.id.new_item_button)
        addListItemBtn.setOnClickListener({
            // TODO save new item into list + SQLite DB
        })

        // TODO if item is saved, redirect back to todo

    }
}