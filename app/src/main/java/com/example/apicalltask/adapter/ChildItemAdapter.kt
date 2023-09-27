package com.example.apicalltask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.OnItemClickListener
import com.example.apicalltask.R
import com.example.apicalltask.dao.MovieLists
import com.example.apicalltask.data.Data
import com.example.apicalltask.viewmodel.DownloadViewModel


class ChildItemAdapter(private val childItemList: List<Data>,context: Context) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {
    private val context = context
    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childItemList[position]


        holder.childItemTitle.text = childItem.title
        if (childItem.is_premium == 1) {
            holder.childItemImageSub.visibility = View.VISIBLE
        } else {
            holder.childItemImageSub.visibility = View.GONE
        }
        holder.imgThreeDot.setOnClickListener { view ->
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.nav_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.download_item -> {


                        onItemClickListener?.onItemClick(childItem.title, childItem.thumbnail_image)
                        true
                    }
                    else -> { false}
                }
            }
            popup.show()
        }
/*        holder.imgThreeDot.setOnClickListener {
            if (holder.downloadImg.isVisible) {
                holder.downloadImg.visibility = View.GONE
            } else {
                holder.downloadImg.visibility = View.VISIBLE
            }
        }*/
        holder.downloadImg.setOnClickListener {
            val task = MovieLists(childItem.title, childItem.thumbnail_image)
            Log.i("oswin2233", "onBindViewHolder: 53 " + childItem.title)
            Log.i("oswin2233", "onBindViewHolder: 54 " + childItem.thumbnail_image)
            val builder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialog_item_downloaded, null)

            builder.setView(dialogView)

            val alertDialog = builder.create()

// Set a click listener for the "OK" button (optional)
            val buttonOK = dialogView.findViewById<Button>(R.id.buttonOK)
            buttonOK.setOnClickListener {
                alertDialog.dismiss() // Close the dialog when "OK" is clicked
            }

            alertDialog.show()


            onItemClickListener?.onItemClick(childItem.title, childItem.thumbnail_image)
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
