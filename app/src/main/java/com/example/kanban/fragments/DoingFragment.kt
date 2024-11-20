package com.example.kanban.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.adapter.KanbanItemAdapter
import com.example.kanban.data.MyDBHelper

class DoingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_doing, container, false)
        val recView: RecyclerView = contentView.findViewById(R.id.doing_recycler_view)
        val doingDataSet = MyDBHelper(context).readKanbanItemsByList(2)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = KanbanItemAdapter(context, doingDataSet)
        return contentView
    }
}