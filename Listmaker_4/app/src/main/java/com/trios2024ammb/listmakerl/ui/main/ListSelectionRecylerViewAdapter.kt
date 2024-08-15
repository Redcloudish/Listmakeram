package com.trios2024ammb.listmakerl.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trios2024ammb.listmakerl.databinding.ListSelectionViewHolderBinding
import com.trios2024ammb.listmakerl.models.TaskList

class ListSelectionRecylerViewAdapter(private val lists : MutableList<TaskList>,
    val clickListener: ListSelectionRecylerViewClickListener) :
RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf( "Shopping List ", "Chores", "Android Tutorials")

    interface ListSelectionRecylerViewClickListener {
        fun ListItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding =
            ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return ListSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
       holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.ListItemClicked(lists[position])}
    }

    fun listsupdated() {
        notifyItemInserted(lists.size -1)
    }

}