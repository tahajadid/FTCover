package tahadeta.example.ftcover.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import tahadeta.example.ftcover.R
import tahadeta.example.ftcover.data.Categorie

class HomeFragment : Fragment() {

    var listCategories: MutableList<Categorie> = ArrayList()

    private lateinit var categorieAdapter: CategorieAdapter
    private lateinit var recyclerView :RecyclerView
    private lateinit var homeBack : View
    private lateinit var favouriteBack : View
    private lateinit var settingBack : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = root.findViewById(R.id.listCategories)
        homeBack = root.findViewById(R.id.homeBack)
        favouriteBack = root.findViewById(R.id.favouriteBack)
        settingBack = root.findViewById(R.id.settingBack)

        initComponent()

        return root
    }

    private fun initComponent() {
        listCategories = ArrayList()
        getCtegories()

        settingBack.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingFragment())
        }

        favouriteBack.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavouriteFragment())
        }

    }

    fun getCtegories() {

        var database = FirebaseFirestore.getInstance()
        database.collection("categorie").get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for(result in it.result){
                        listCategories.add(result.toObject(Categorie::class.java))
                        Log.d("FirestoreData", "Suss = " + result.toString())
                    }

                    categorieAdapter = CategorieAdapter(this.context, listCategories)
                    listCategories.sortBy { it.weight }
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this.context, 1)
                        adapter = categorieAdapter
                    }

                }
            }.addOnFailureListener {
                Log.d("FirestoreData", "Error = " + it.toString())
            }
    }
}
