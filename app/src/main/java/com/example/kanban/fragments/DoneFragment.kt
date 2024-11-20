package com.example.kanban.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.adapter.RecyclerAdapter
import com.example.kanban.data.MyDBHelper

class DoneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_done, container, false)
        val recView: RecyclerView = contentView.findViewById(R.id.done_recycler_view)
        val doneDataSet = MyDBHelper(context).readKanbanItemsByList(3)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = RecyclerAdapter(context, doneDataSet)
        return contentView
    }
}