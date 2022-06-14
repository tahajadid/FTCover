package tahadeta.example.ftcover.ui.wallpaper.detailWallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import tahadeta.example.ftcover.R
import tahadeta.example.ftcover.util.WallpaperHelper
import java.io.File
import java.io.IOException


class DetailWallpaperFragment : Fragment() {

    private val args by navArgs<DetailWallpaperFragmentArgs>()
    lateinit var setWallpaperView: View
    lateinit var likeImage: ImageView
    lateinit var dislikeImage: ImageView
    lateinit var downloadVew: View

    lateinit var imageWallpaper: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail_wallpaper, container, false)

        imageWallpaper = root.findViewById(R.id.imageWallpaper)
        setWallpaperView = root.findViewById(R.id.viewSetWallpapers)
        likeImage = root.findViewById(R.id.likeImage)
        dislikeImage = root.findViewById(R.id.dislikeImage)
        downloadVew = root.findViewById(R.id.downloadView)

        WallpaperHelper.setImage(imageWallpaper,args.idWallpaper)

        initComponents()

        return root
    }

    private fun initComponents() {
        likeImage.setOnClickListener {
            likeImage.setImageResource(R.drawable.like_fill)
            dislikeImage.setImageResource(R.drawable.dislike)

        }

        dislikeImage.setOnClickListener {
            likeImage.setImageResource(R.drawable.like)
            dislikeImage.setImageResource(R.drawable.dislike_fill)
        }

        downloadVew.setOnClickListener {
            downloadImage(args.idWallpaper)
        }

        setWallpaperImage()
    }

    private fun downloadImage(pathImage: String) {


        val ref = Firebase.storage.reference.child(pathImage)

        val localFile = File.createTempFile("tempImag","jpg" )
        ref.getFile(localFile).addOnCompleteListener{

            val bitmapLast = BitmapFactory.decodeFile(localFile.absolutePath)

            MediaStore.Images.Media.insertImage(
                requireActivity().getContentResolver(),
                bitmapLast,
                "title",
                "Image of titl"
            )


        }

    }

    @SuppressLint("ResourceType")
    private fun setWallpaperImage() {

        val wallpaperManager =
            WallpaperManager.getInstance(context)

        setWallpaperView.setOnClickListener {

            try {
                // set the wallpaper by calling the setResource function and
                // passing the drawable file
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setResource(R.drawable.fes_cover,WallpaperManager.FLAG_SYSTEM)
                    // wallpaperManager.setResource(R.drawable.fes_cover,WallpaperManager.FLAG_LOCK)

                }

            } catch (e: IOException) {
                // here the errors can be logged instead of printStackTrace
                e.printStackTrace()
            }
        }

    }

}