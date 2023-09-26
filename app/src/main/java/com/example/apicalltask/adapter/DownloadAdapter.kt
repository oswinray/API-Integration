package com.example.apicalltask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.R

class DownloadAdapter() :
    RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {
    var listitem = mutableListOf<com.example.apicalltask.dao.MovieLists>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycler, viewGroup, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var data = listitem.get(i)
        Glide.with(viewHolder.img_download.context)
            .load(data.thumb_image)
            .into(viewHolder.img_download)

        viewHolder.txt_title.text= data.title_name

    }

    override fun getItemCount(): Int {
        return listitem.size
    }

    fun Updatedata(listData:List<com.example.apicalltask.dao.MovieLists>){
        listitem.clear()
        listitem.addAll(listData)
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var img_download: ImageView
        var txt_title: TextView

        init {
            img_download = view.findViewById(R.id.img_child_item_download)
            txt_title = view.findViewById(R.id.download_title)

        }
    }

}