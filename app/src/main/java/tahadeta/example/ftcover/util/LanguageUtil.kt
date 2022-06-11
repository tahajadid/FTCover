package tahadeta.example.ftcover.util

import android.app.Activity
import android.util.Log
import com.zeugmasolutions.localehelper.Locales
import tahadeta.example.ftcover.MainActivity
import java.util.*

object LanguageUtil {
    /**
     * function that change the application language
     */

    fun setLang(activity: Activity, languageCode: String): Boolean {

        isFrench = (languageCode == "fr").toString()
        (activity as MainActivity).updateLocale(
            if (languageCode == "ar") Locale(
                "ar",
                "MA"
            ) else Locales.French
        )

        Log.d("sohwError","Inside -- setLang ")
        return languageCode.isNotEmpty() && activity is MainActivity
    }
}
