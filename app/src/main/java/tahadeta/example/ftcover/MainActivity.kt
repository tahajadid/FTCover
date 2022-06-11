package tahadeta.example.ftcover

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.api.Context
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.Locales

class MainActivity : LocaleAwareCompatActivity() {

    lateinit var context : Context

    companion object {
        lateinit var activityInstance: MainActivity
        lateinit    var navController: NavController
        var currentDestinationId: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("sohwError", "Inside -- onCreate ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //context = LocaleHelper.setLocale(MainActivity.this, "en");
        //updateLocale(Locales.Arabic)

        activityInstance = this
        navController=
            Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }
}
