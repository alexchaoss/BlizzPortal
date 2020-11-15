package com.BlizzardArmory.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.ui.MainActivity.Companion.locale
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import kotlinx.android.synthetic.main.settings.*
import org.greenrobot.eventbus.EventBus

class SettingsFragment : Fragment() {

    private val languageList = arrayOf("Select Language", "English", "Spanish", "Portuguese", "French", "Russian", "German", "Italian", "Korean", "Chinese", "Taiwanese")
    private lateinit var selectedLanguage: String
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.onBackPressedDispatcher?.addCallback {
            activity?.supportFragmentManager?.popBackStack()
        }
        return inflater.inflate(R.layout.settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

        val languageAdapter = setAdapater(languageList)
        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_language.adapter = languageAdapter
        spinnerSelector(spinner_language)

        setSettingsButtons()
    }

    private fun setAdapater(list: Array<String>): ArrayAdapter<String> {
        return object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, list) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.setBackgroundColor(Color.BLACK)
                tv.textSize = 20f
                tv.gravity = Gravity.CENTER
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
    }

    private fun spinnerSelector(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedLanguage = parent.getItemAtPosition(position) as String
                Log.i("lang", selectedLanguage)
                setLocale()
                if (position != 0) {
                    EventBus.getDefault().postSticky(LocaleSelectedEvent(locale))
                }
                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 20f
                    view.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setSettingsButtons() {
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.custom_license_title))
        licenses.setOnClickListener { startActivity(Intent(activity, OssLicensesMenuActivity::class.java)) }
        rate.setOnClickListener { openAppStoreForReview() }
        donation.setOnClickListener { webview?.loadUrl(URLConstants.PAYPAL_URL) }
    }

    private fun openAppStoreForReview() {
        val uri = Uri.parse("market://details?id=" + activity?.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity?.packageName)))
        }
    }

    private fun setLocale() {
        locale = when (selectedLanguage) {
            "English" -> "en_US"
            "Spanish" -> "es_ES"
            "French" -> "fr_FR"
            "Russian" -> "ru_RU"
            "German" -> "de_DE"
            "Portuguese" -> "pt_BR"
            "Italian" -> "it_IT"
            "Korean" -> "ko_KR"
            "Chinese" -> "zh_CN"
            "Taiwanese" -> "zh_TW"
            else -> "en_US"
        }
        sharedPreferences!!.edit().putString("locale", locale).apply()
    }

}