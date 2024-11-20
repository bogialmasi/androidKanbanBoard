package com.example.kanban.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.model.KanbanItem

class RecyclerAdapter(val context: Context?, val dataSet: List<KanbanItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>(), OnCreateContextMenuListener

{
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_content)
        val cardView: CardView = view.findViewById(R.id.list_item_layout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.textView.text = item.content
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        TODO("Not yet implemented")
    }

}