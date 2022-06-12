package tahadeta.example.ftcover.ui.wallpaper

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tahadeta.example.ftcover.R
import tahadeta.example.ftcover.data.Categorie
import tahadeta.example.ftcover.data.Wallpaper
import tahadeta.example.ftcover.util.WallpaperHelper

class WallpaperAdapter(private val context: Context?,
                       private val list: List<Wallpaper>
) : RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {
    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageWallpaper: ImageView = itemView.findViewById(R.id.imageWallpaper)
        private val imageAddFavourite: ImageView = itemView.findViewById(R.id.addFavouriteImage)

        fun bindView(item: Wallpaper, position: Int, context: Context?) {
            WallpaperHelper.setImage(imageWallpaper,item.pathPoster.toString())
            Log.d("FERETJJD","enter ==== ")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item, position, context)


        /*
        holder.itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToListWallpaperFragment(item.title.toString())
            holder.itemView.findNavController().navigate(action)
        }

         */

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.wallpaper_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size
}
