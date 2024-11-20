package com.example.kanban

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class TodoFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_todo, container, false)
        val listView: ListView = contentView.findViewById(R.id.todo_list)
        val button: View = contentView.findViewById(R.id.fab_button)
        button.setOnClickListener {
            // REDIRECTING to AddListItem to create new list item
            val intent = Intent(context, AddListItem::class.java)
            startActivity(intent)
        }
        return contentView
    }
}