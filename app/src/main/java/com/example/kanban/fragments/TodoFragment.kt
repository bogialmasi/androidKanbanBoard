package com.example.kanban.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.AddListItem
import com.example.kanban.R
import com.example.kanban.adapter.KanbanItemAdapter
import com.example.kanban.data.MyDBHelper

class TodoFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_todo, container, false)
        val recView: RecyclerView = contentView.findViewById(R.id.todo_recycler_view)
        val button: View = contentView.findViewById(R.id.fab_button)
        val todoDataSet = MyDBHelper(context).readKanbanItemsByList(2)
        recView.adapter = KanbanItemAdapter(context, todoDataSet)
        button.setOnClickListener {
            // REDIRECTING to AddListItem to create new list item
            val intent = Intent(context, AddListItem::class.java)
            startActivity(intent)
        }
        return contentView
    }
}