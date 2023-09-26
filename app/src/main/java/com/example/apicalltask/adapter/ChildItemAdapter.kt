package com.example.apicalltask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.R
import com.example.apicalltask.data.Data


class ChildItemAdapter(private val childItemList: List<Data>,context: Context) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {

    private val context = context


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
        if (childItem.is_premium == 1) {
            holder.childItemImageSub.visibility = View.VISIBLE
        } else {
            holder.childItemImageSub.visibility = View.GONE
        }
        holder.imgThreeDot.setOnClickListener {
            if (holder.downloadImg.isVisible) {
                holder.downloadImg.visibility = View.GONE
            } else {
                holder.downloadImg.visibility = View.VISIBLE
            }
        }
        holder.downloadImg.setOnClickListener {
            holder.downloadImg.visibility = View.GONE
        }
        Glide.with(context)
            .load(childItem.poster_image)
            .error(R.drawable.netflix_logo)
            .into(holder.childItemImage)
    }

    override fun getItemCount(): Int {

        return childItemList.size
    }


    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val childItemTitle: TextView = itemView.findViewById(R.id.child_item_title)
        val childItemImageSub: TextView = itemView.findViewById(R.id.img_child_item_subscribe)
        val downloadImg: TextView = itemView.findViewById(R.id.download_img)
        val childItemImage: ImageView = itemView.findViewById(R.id.img_child_item)
        val imgThreeDot: ImageView = itemView.findViewById(R.id.img_three_dot)
    }
}
