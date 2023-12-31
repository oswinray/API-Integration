package com.example.apicalltask.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.Constants
import com.example.apicalltask.FullScreenImageActivity
import com.example.apicalltask.OnItemClickListener
import com.example.apicalltask.R
import com.example.apicalltask.dao.MovieLists
import com.example.apicalltask.data.Data
import com.example.apicalltask.databinding.ChildItemBinding
import com.example.apicalltask.viewmodel.DownloadViewModel


class ChildItemAdapter(private val childItemList: List<Data>,context: Context) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {
    private val context = context
    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childItemList[position]


        holder.childItemTitle.text = childItem.title

        holder.childItemTitle.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_title)

            val titleTextView = dialog.findViewById<TextView>(R.id.titleTextView)
            titleTextView.text = childItem.title

            dialog.show()
        }
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

        holder.childItemImage.setOnClickListener {
            val imageUrl = childItem.poster_image

            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra(Constants.IMAGE_URl, imageUrl)
            context.startActivity(intent)
        }

        Glide.with(context)
            .load(childItem.poster_image)
            .error(R.drawable.netflix_logo)
            .into(holder.childItemImage)
    }

    override fun getItemCount(): Int {

        return childItemList.size
    }


    class ChildViewHolder(private val binding: ChildItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val childItemTitle = binding.childItemTitle
        val childItemImageSub = binding.imgChildItemSubscribe
        val downloadImg = binding.downloadImg
        val childItemImage = binding.imgChildItem
        val imgThreeDot = binding.imgThreeDot
    }

}
