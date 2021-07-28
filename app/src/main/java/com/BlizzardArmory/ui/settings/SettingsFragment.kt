package com.BlizzardArmory.ui.settings

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
import com.BlizzardArmory.databinding.SettingsBinding
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.diablo.characterfragments.stats.CharacterStatsFragment
import com.BlizzardArmory.ui.diablo.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.main.MainActivity.Companion.locale
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.ui.overwatch.OWFragment
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.starcraft.leaderboard.SC2LeaderboardFragment
import com.BlizzardArmory.ui.starcraft.profile.SC2Fragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.mythicplusleaderboards.MPlusLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboards.MRaidLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.pvpleaderboards.PvpLeaderboardsFragment
import com.BlizzardArmory.util.FragmentTag
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.greenrobot.eventbus.EventBus

class SettingsFragment : Fragment() {

    private val languageList = arrayOf("Select Language", "English", "Spanish", "Portuguese", "French", "Russian", "German", "Italian", "Korean", "Chinese", "Taiwanese")
    private lateinit var selectedLanguage: String
    private var sharedPreferences: SharedPreferences? = null

    private var _binding: SettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.onBackPressedDispatcher?.addCallback {
            if (requireActivity().supportFragmentManager.backStackEntryCount > 1) {
                when (requireActivity().supportFragmentManager.fragments[requireActivity().supportFragmentManager.backStackEntryCount - 2].tag) {
                    FragmentTag.WOWFAVORITES.name -> WoWFavoritesFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.D3FAVORITES.name -> D3FavoriteFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.OWFAVORITES.name -> OWFavoritesFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.D3NAV.name -> CharacterStatsFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.D3FRAGMENT.name -> D3Fragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.D3LEADERBOARD.name -> D3LeaderboardFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.NAVFRAGMENT.name -> WoWCharacterFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.WOWFRAGMENT.name -> AccountFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.WOWGUILDNAVFRAGMENT.name -> ActivityFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.WOWMPLUSLEADERBOARD.name -> MPlusLeaderboardsFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.WOWPVPLEADERBOARD.name -> PvpLeaderboardsFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.WOWRAIDLEADERBOARD.name -> MRaidLeaderboardsFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.SC2FRAGMENT.name -> SC2Fragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.SC2LEADERBOARD.name -> SC2LeaderboardFragment.addOnBackPressCallback(activity as NavigationActivity)
                    FragmentTag.OVERWATCHFRAGMENT.name -> OWFragment.addOnBackPressCallback(activity as NavigationActivity)
                    else -> NewsPageFragment.addOnBackPressCallback(activity as NavigationActivity)
                }
            } else {
                NewsPageFragment.addOnBackPressCallback(activity as NavigationActivity)
            }
            activity?.supportFragmentManager?.popBackStack()
        }
        _binding = SettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

        val languageAdapter = setAdapater(languageList)
        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.spinnerLanguage.adapter = languageAdapter
        spinnerSelector(binding.spinnerLanguage)

        setSettingsButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAdapater(list: Array<String>): ArrayAdapter<String> {
        return object :
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, list) {
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
                if (position != 0) {
                    Log.i("lang", selectedLanguage)
                    setLocale()
                    EventBus.getDefault().post(LocaleSelectedEvent(locale))
                }
                if (view != null) {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 20f
                    view.gravity = Gravity.CENTER
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
        binding.licenses.setOnClickListener { startActivity(Intent(activity, OssLicensesMenuActivity::class.java)) }
        binding.rate.setOnClickListener { openAppStoreForReview() }
        binding.donation.setOnClickListener { binding.webview.loadUrl(NetworkUtils.PAYPAL_URL) }
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