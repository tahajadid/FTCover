package tahadeta.example.ftcover.ui.wallpaper

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import tahadeta.example.ftcover.R
import tahadeta.example.ftcover.data.Categorie
import tahadeta.example.ftcover.data.Wallpaper
import tahadeta.example.ftcover.ui.home.CategorieAdapter
import tahadeta.example.ftcover.util.WallpaperHelper
import java.io.File

class ListWallpaperFragment : Fragment() {

    private val args by navArgs<ListWallpaperFragmentArgs>()
    var listWallpapers: MutableList<Wallpaper> = ArrayList()
    private lateinit var wallpaperAdapter: WallpaperAdapter
    private lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_list_wallpaper, container, false)

        recyclerView = root.findViewById(R.id.listWallpapers)

        getWallpapers()

        return root
    }

    fun getWallpapers() {

        var database = FirebaseFirestore.getInstance()
        database.collection("wallpaper")
            .whereEqualTo("idCategorie","fesCategorie").get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for(result in it.result){
                        listWallpapers.add(result.toObject(Wallpaper::class.java))
                        Log.d("FirestoreData2", "Suss = " + result.toString())
                    }

                    wallpaperAdapter = WallpaperAdapter(this.context, listWallpapers)

                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this.context, 2)
                        adapter = wallpaperAdapter
                    }

                }
            }.addOnFailureListener {
                Log.d("FirestoreData2", "Error = " + it.toString())
            }
    }

}