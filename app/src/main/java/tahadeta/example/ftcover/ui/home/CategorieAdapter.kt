package tahadeta.example.ftcover.ui.home

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tahadeta.example.ftcover.R
import tahadeta.example.ftcover.data.Categorie
import java.io.InputStream


class CategorieAdapter( private val context: Context?,
private val list: List<Categorie>
) : RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {
    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleCategorieHome: TextView = itemView.findViewById(R.id.titleCategorieHome)
        private val imageCategorieHome: ImageView = itemView.findViewById(R.id.imageCategorieHome)

        fun bindView(item: Categorie, position: Int, context: Context?) {
            titleCategorieHome.text = item.title

            when(item.title){
                "Fatal Tigers" -> imageCategorieHome.setImageResource(R.drawable.fatal_cover)
                "fes" -> imageCategorieHome.setImageResource(R.drawable.fes_cover)
                "Mas" -> imageCategorieHome.setImageResource(R.drawable.mas_cover)
                "Football" -> imageCategorieHome.setImageResource(R.drawable.footbal_cover)
                else -> imageCategorieHome.setImageResource(R.drawable.accueil)

            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item, position, context)


        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToListWallpaperFragment(item.title.toString())
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.categorie_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size
}
