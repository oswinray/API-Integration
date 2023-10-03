package com.example.apicalltask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.OnItemClickListener
import com.example.apicalltask.R
import com.example.apicalltask.data.HomeContent
import com.example.apicalltask.databinding.ParentItemBinding

class ParentItemAdapter(private val itemList: List<HomeContent>,context: Context,onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val context = context
    private val onItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
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
        val childItemAdapter = ChildItemAdapter(parentItem.data,context)

        childItemAdapter.setOnItemClickListener(onItemClickListener)
        parentViewHolder.childRecyclerView.layoutManager = layoutManager
        parentViewHolder.childRecyclerView.adapter = childItemAdapter
        parentViewHolder.childRecyclerView.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ParentViewHolder(private val binding: ParentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val parentItemTitle = binding.parentItemTitle
        val childRecyclerView = binding.childRecyclerview
    }
}
