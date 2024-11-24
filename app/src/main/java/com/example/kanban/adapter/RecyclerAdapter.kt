package com.example.kanban.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
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
import com.google.android.material.animation.AnimationUtils
import kotlin.concurrent.thread

class RecyclerAdapter(val context: Context?, val dataSet: MutableList<KanbanItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>()

{
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_content)
        val progressBtn: Button = view.findViewById(R.id.list_item_progress_btn)
        val deleteButton: Button = view.findViewById(R.id.list_item_delete_btn)
        val cardView: View = view.findViewById(R.id.list_item_layout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    // This is where progress and delete button functions are handled
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerAdapter.ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.textView.text = item.content
        holder.progressBtn.setOnClickListener {
            MyDBHelper(context).updateKanbanItem(item.content, item.state, item.id)
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size - position)
            notifyDataSetChanged()
            if(item.state == 3 ){
                MyDBHelper(context).deleteKanbanItem(item.id)
                Toast.makeText(context, context!!.getString(R.string.toast_progress_over3), Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(context, context!!.getString(R.string.toast_progressed), Toast.LENGTH_SHORT).show()}
        }
        holder.deleteButton.setOnClickListener {
            if (dataSet.size > position) {
                MyDBHelper(context).deleteKanbanItem(item.id)
                dataSet.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, dataSet.size - position)
                notifyDataSetChanged()
                Toast.makeText(context, context!!.getString(R.string.toast_deleted), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}