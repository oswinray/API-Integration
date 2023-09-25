package com.example.apicalltask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.R
import com.example.apicalltask.data.Data


class ChildItemAdapter(private val childItemList: List<Data>) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childItemList[position]


        holder.childItemTitle.text = childItem.title
        Glide.with(holder.itemView.getContext())
            .load(childItem.thumbnail_image)
            .into(holder.childItemImage)
    }

    override fun getItemCount(): Int {

        return childItemList.size
    }


    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val childItemTitle: TextView = itemView.findViewById(R.id.child_item_title)
        val childItemImage: ImageView = itemView.findViewById(R.id.img_child_item)
    }
}
