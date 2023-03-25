import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestgame.baba3dscanner.R
import java.io.File

class VideoListAdapter(
    private val context: Context,
    private val videos: List<File>,
    private val onVideoClickListener: (File) -> Unit
) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoNameTextView: TextView = itemView.findViewById(R.id.video_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onVideoClickListener(videos[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.video_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videos[position]
        holder.videoNameTextView.text = video.name
    }

    override fun getItemCount(): Int {
        return videos.size
    }
}
