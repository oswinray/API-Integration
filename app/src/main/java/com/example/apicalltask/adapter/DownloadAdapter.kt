import android.app.Dialog
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
import com.example.apicalltask.databinding.ItemRecyclerBinding

class DownloadAdapter(context: Context) :
    RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {
    private val context = context
    private var downloadedItems: List<MovieLists> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val data = downloadedItems[i]

        viewHolder.imgDownload.setOnClickListener {
            val imageUrl = data.thumb_image

            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra(Constants.IMAGE_URl, imageUrl)
            context.startActivity(intent)
        }
        Glide.with(viewHolder.imgDownload.context)
            .load(data.thumb_image)
            .into(viewHolder.imgDownload)

        viewHolder.txtTitle.text = data.title_name
        viewHolder.txtTitle.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_title)

            val titleTextView = dialog.findViewById<TextView>(R.id.titleTextView)
            titleTextView.text = data.title_name

            dialog.show()
        }
    }


    override fun getItemCount(): Int {
        return downloadedItems.size
    }

    fun setData(items: List<MovieLists>) {
        downloadedItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgDownload = binding.imgChildItem
        val txtTitle = binding.childItemTitle
    }
}
