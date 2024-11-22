package com.example.kanban.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.data.MyDBHelper
import com.example.kanban.model.KanbanItem

class RecyclerAdapter(val context: Context?, val dataSet: List<KanbanItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>()

{
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_content)
        val cardView: CardView = view.findViewById(R.id.list_item_layout)
        val progressBtn: Button = view.findViewById(R.id.list_item_progress_btn)
        val deleteButton: Button = view.findViewById(R.id.list_item_delete_btn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    // not refreshing view after item is deleted/upgraded, but appears the right way after closing and opening the app
    override fun onBindViewHolder(holder: RecyclerAdapter.ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.textView.text = item.content
        holder.progressBtn.setOnClickListener {
            MyDBHelper(context).updateKanbanItem(item.content, item.state, item.id)
        }
        holder.deleteButton.setOnClickListener {
            if (dataSet.size > position) {
                MyDBHelper(context).deleteKanbanItem(item.id)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}