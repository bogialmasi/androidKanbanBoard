package com.example.kanban.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.model.KanbanItem

class KanbanItemAdapter(private val context: Context,
                  private val dataSet: List<KanbanItem>) :
    RecyclerView.Adapter<KanbanItemAdapter.ItemViewHolder>()

{
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_content)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KanbanItemAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: KanbanItemAdapter.ItemViewHolder, position: Int) {
        val item = dataSet[position]
        //holder.textView.text = context.resources.getStringArray(R.array.)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}