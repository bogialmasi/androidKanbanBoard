package com.example.kanban

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kanban.data.MyDBHelper
import com.example.kanban.fragments.TodoFragment

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

        val addListItemBtn: Button = findViewById(R.id.new_item_button)
        val addListItemContent : EditText = findViewById(R.id.new_item_edittext)
        val intent = Intent(this, MainActivity::class.java)
        addListItemBtn.setOnClickListener{
            // BY DEFAULT this item will get STATE 1 and go into to do list
            MyDBHelper(this).addKanbanItem(addListItemContent.text.toString())
            // after the new item is added, main activity starts again
            startActivity(intent)
        }
    }
}