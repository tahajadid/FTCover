package tahadeta.example.ftcover

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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

    //Clear focus of all EditText views
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }

}
