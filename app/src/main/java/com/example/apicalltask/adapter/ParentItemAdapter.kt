package com.example.apicalltask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.R
import com.example.apicalltask.data.HomeContent

class ParentItemAdapter(private val itemList: List<HomeContent>) :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_item, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(parentViewHolder: ParentViewHolder, position: Int) {
        val parentItem = itemList[position]

        parentViewHolder.parentItemTitle.text = parentItem.category_name

        val layoutManager = LinearLayoutManager(
            parentViewHolder.childRecyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        layoutManager.initialPrefetchItemCount = itemList.size-1

        // Create and set the child adapter
        val childItemAdapter = ChildItemAdapter(parentItem.data)
        parentViewHolder.childRecyclerView.layoutManager = layoutManager
        parentViewHolder.childRecyclerView.adapter = childItemAdapter
        parentViewHolder.childRecyclerView.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentItemTitle: TextView = itemView.findViewById(R.id.parent_item_title)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.child_recyclerview)
    }
}
