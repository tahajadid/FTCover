package tahadeta.example.ftcover.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import tahadeta.example.ftcover.R


class FavouriteFragment : Fragment() {

    lateinit var homeImage: View
    lateinit var settingImage: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favourite, container, false)

        homeImage = root.findViewById(R.id.homeBack)
        settingImage = root.findViewById(R.id.settingBack)

        settingImage.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }

        homeImage.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return root
    }

}