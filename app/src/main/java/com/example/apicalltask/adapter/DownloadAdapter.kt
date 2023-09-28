import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalltask.Constants
import com.example.apicalltask.FullScreenImageActivity
import com.example.apicalltask.R
import com.example.apicalltask.dao.MovieLists

class DownloadAdapter(context: Context) :
    RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {
    private val context = context
    private var downloadedItems: List<MovieLists> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycler, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val data = downloadedItems[i]

        viewHolder.img_download.setOnClickListener {
            val imageUrl = data.thumb_image

            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra(Constants.IMAGE_URl, imageUrl)
            context.startActivity(intent)
        }
        Glide.with(viewHolder.img_download.context)
            .load(data.thumb_image)
            .into(viewHolder.img_download)

        viewHolder.txt_title.text = data.title_name
    }

    override fun getItemCount(): Int {
        return downloadedItems.size
    }

    fun setData(items: List<MovieLists>) {
        downloadedItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img_download: ImageView = view.findViewById(R.id.img_child_item)
        var txt_title: TextView = view.findViewById(R.id.child_item_title)
    }
}
