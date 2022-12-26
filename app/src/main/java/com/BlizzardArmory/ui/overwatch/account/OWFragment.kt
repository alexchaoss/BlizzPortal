package com.BlizzardArmory.ui.overwatch.account

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.OwFragmentBinding
import com.BlizzardArmory.model.overwatch.account.favorite.FavoriteProfile
import com.BlizzardArmory.model.overwatch.account.favorite.FavoriteProfiles
import com.BlizzardArmory.model.overwatch.account.heroes.Hero
import com.BlizzardArmory.model.overwatch.account.topheroes.TopHero
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.NetworkUtils.getOWPortraitImage
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.FragmentTag
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean


/**
 * The type Ow activity.
 */
class OWFragment : Fragment() {
    private var errorMessages: ErrorMessages? = null
    private var username: String? = null
    private var platform: String? = null
    private var switchCompQuickRadius: GradientDrawable? = null

    private val privateProfile = "This profile is private and the information unavailable"

    private var _binding: OwFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OWViewModel by activityViewModels()

    private lateinit var navigationActivity: NavigationActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = OwFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
        requireActivity().viewModelStore.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationActivity = (requireActivity() as NavigationActivity)
        errorMessages = ErrorMessages(this.resources)
        setObservers()
        binding.loadingCircle.visibility = View.VISIBLE
        navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
        val switchCompQuickBorder = GradientDrawable()
        switchCompQuickBorder.cornerRadius = 5f
        switchCompQuickBorder.setStroke(3, Color.parseColor("#FFFFFF"))
        binding.quickComp.background = switchCompQuickBorder
        switchCompQuickRadius = GradientDrawable()
        switchCompQuickRadius!!.cornerRadius = 5f
        switchCompQuickRadius!!.setColor(Color.parseColor("#FFFFFF"))
        binding.quickplay.background = switchCompQuickRadius
        username = this.arguments?.getString("username")
        platform = this.arguments?.getString("platform")
        viewModel.downloadAccountInformation(username!!, platform!!)
    }

    private fun setObservers() {
        viewModel.getProfile().observe(viewLifecycleOwner) {
            manageFavorite()
            downloadAvatar()
            setName()
            downloadLevelIcon()
            setGamesWon()
            setRatingInformation()
            setTopButtons()
            binding.loadingCircle.visibility = View.GONE
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            binding.gamesWon.text = privateProfile
            binding.gamesWon.textSize = 18f
            binding.loadingCircle.visibility = View.GONE
            NetworkUtils.loading = false
            navigationActivity.favorite!!.setOnClickListener {
                Snackbar.make(
                    binding.root,
                    "Can't add private profile to favorites",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            showNoConnectionMessage(it)
        }

        viewModel.getCompToggle().observe(viewLifecycleOwner) {
            if (!it) {
                binding.quickplay.background = switchCompQuickRadius
                binding.quickplay.setTextColor(Color.parseColor("#000000"))
                binding.competitive.setTextColor(Color.parseColor("#FFFFFF"))
                binding.competitive.setBackgroundColor(0)
                updateList(
                    viewModel.getTopHeroesQuickPlay().value!!,
                    viewModel.getCareerQuickPlay().value!!
                )
            } else {
                binding.competitive.background = switchCompQuickRadius
                binding.competitive.setTextColor(Color.parseColor("#000000"))
                binding.quickplay.setBackgroundColor(0)
                binding.quickplay.setTextColor(Color.parseColor("#FFFFFF"))
                updateList(
                    viewModel.getTopHeroesCompetitive().value!!,
                    viewModel.getCareerCompetitive().value!!
                )
            }
        }
    }

    private fun updateList(heroList: ArrayList<TopHero>, careerList: ArrayList<Hero>) {
        viewModel.sortList(heroList, viewModel.getSortList()[0])
        if (heroList.size > 0) {
            setTopCharacterImage(heroList[0].getName())
        }
        setSpinnerTopHeroes(binding.spinner)
        viewModel.setSpinnerCareerList(careerList)
        setSpinnerCareer(binding.spinnerCareer)
    }

    private fun setTopButtons() {
        binding.quickplay.setOnClickListener {
            if (viewModel.getCompToggle().value!!) {
                viewModel.getCompToggle().value = false
            }
        }
        binding.competitive.setOnClickListener {
            if (!viewModel.getCompToggle().value!!) {
                viewModel.getCompToggle().value = true
            }
        }
    }

    private fun manageFavorite() {
        var profiles = FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val gson = GsonBuilder().create()
        var index = -1
        var indexTemp = 0
        val favorites = prefs.getString("ow-favorites", "DEFAULT")!!
        if (favorites != "DEFAULT" && favorites != "{\"profile_list\":[]}") {
            profiles = gson.fromJson(favorites, FavoriteProfiles::class.java)
            for (profile in profiles.profiles) {
                if (profile.username == username && profile.platform == platform) {
                    navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                    index = indexTemp
                    profiles.profiles[index] =
                        FavoriteProfile(platform!!, username!!, viewModel.getProfile().value!!)
                    prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                } else {
                    addToFavorites(profiles, prefs, gson, index)
                }
                indexTemp++
            }
            removeFavorite(profiles, prefs, gson, index)
        } else {
            addToFavorites(profiles, prefs, gson, index)
        }
    }

    private fun removeFavorite(
        profiles: FavoriteProfiles,
        prefs: SharedPreferences,
        gson: Gson,
        index: Int
    ) {
        if (navigationActivity.favorite!!.tag as Int == R.drawable.ic_star_black_24dp && index != -1) {
            navigationActivity.favorite!!.setOnClickListener {
                navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
                profiles.profiles.removeAt(index)
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                addToFavorites(profiles, prefs, gson, index)
                Snackbar.make(binding.root, "Profile removed from favorites", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun addToFavorites(
        profiles: FavoriteProfiles,
        prefs: SharedPreferences,
        gson: Gson,
        index: Int
    ) {
        val containsProfiles = AtomicBoolean(false)
        navigationActivity.favorite!!.setOnClickListener {
            for (profile in profiles.profiles) {
                if (profile.username == username && profile.platform == platform) {
                    containsProfiles.set(true)
                    break
                }
            }
            if (!containsProfiles.get()) {
                navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                profiles.profiles.add(
                    FavoriteProfile(
                        platform!!,
                        username!!,
                        viewModel.getProfile().value!!
                    )
                )
                prefs.edit().putString("ow-favorites", gson.toJson(profiles)).apply()
                Snackbar.make(binding.root, "Profile added to favorites", Snackbar.LENGTH_SHORT)
                    .show()
            }
            removeFavorite(profiles, prefs, gson, index)
        }
    }

    private fun setSpinnerTopHeroes(spinner: Spinner?) {
        val arrayAdapter: ArrayAdapter<String> = object :
            ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                viewModel.getSortList()
            ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                Log.i("TEST", "$position, $convertView, $parent")
                try {
                    val view = super.getDropDownView(position, convertView, parent)
                    val tv = view as TextView
                    tv.isAllCaps = true
                    tv.setBackgroundColor(Color.WHITE)
                    tv.textSize = 15f
                    tv.setTextColor(Color.BLACK)
                    tv.gravity = Gravity.CENTER
                    return view
                } catch (e: Exception) {
                    Log.e("Dropdown", "error", e)
                }
                return View(requireContext())
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner!!.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    (view as TextView).setTextColor(Color.parseColor("#CCCCCC"))
                    view.textSize = 15f
                    view.gravity = Gravity.CENTER_VERTICAL
                }
                if (viewModel.getCompToggle().value!!) {
                    viewModel.sortList(
                        viewModel.getTopHeroesCompetitive().value,
                        viewModel.getSortList()[position]
                    )
                    binding.topHeroRecycler.adapter =
                        OWProgressAdapter(
                            viewModel.getTopHeroesCompetitive().value!!,
                            requireActivity(),
                            viewModel.getSortList()[position]
                        )
                } else {
                    viewModel.sortList(
                        viewModel.getTopHeroesQuickPlay().value,
                        viewModel.getSortList()[position]
                    )
                    binding.topHeroRecycler.adapter =
                        OWProgressAdapter(
                            viewModel.getTopHeroesQuickPlay().value!!,
                            requireActivity(),
                            viewModel.getSortList()[position]
                        )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setSpinnerCareer(spinner: Spinner?) {
        val arrayAdapter: ArrayAdapter<String> = object :
            ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                viewModel.getCareerSortList()
            ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                try {
                    val view = super.getDropDownView(position, convertView, parent)
                    val tv = view as TextView
                    tv.isAllCaps = true
                    tv.setBackgroundColor(Color.WHITE)
                    tv.textSize = 15f
                    tv.setTextColor(Color.BLACK)
                    tv.gravity = Gravity.CENTER
                    return view
                } catch (e: Exception) {
                    Log.e("Dropdown", "error", e)
                }
                return View(requireContext())
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner!!.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    (view as TextView).setTextColor(Color.parseColor("#CCCCCC"))
                    view.textSize = 15f
                    view.gravity = Gravity.CENTER_VERTICAL
                }
                if (viewModel.getCompToggle().value!!) {
                    setCareerStats(position, viewModel.getCareerCompetitive().value!!)
                } else {
                    setCareerStats(position, viewModel.getCareerQuickPlay().value!!)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun setCareerStats(position: Int, career: ArrayList<Hero>) {
        binding.best.removeViews(1, binding.best.childCount - 1)
        binding.assist.removeViews(1, binding.assist.childCount - 1)
        binding.game.removeViews(1, binding.game.childCount - 1)
        binding.average.removeViews(1, binding.average.childCount - 1)
        binding.combat.removeViews(1, binding.combat.childCount - 1)
        binding.misc.removeViews(1, binding.misc.childCount - 1)
        binding.match.removeViews(1, binding.match.childCount - 1)
        try {
            setSpecificCareerList(career[position].best?.bestList!!, binding.best, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].assists?.assists!!, binding.assist, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].average?.average!!, binding.average, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].game?.game!!, binding.game, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].miscellaneous!!.misc, binding.misc, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].matchAwards!!.match, binding.match, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            setSpecificCareerList(career[position].combat?.combat!!, binding.combat, 5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setSpecificCareerList(
        list: HashMap<String, String>,
        parentLayout: LinearLayout?,
        marginStart: Int
    ) {
        val scale = requireActivity().resources.displayMetrics.density
        for ((i, key) in list.keys.withIndex()) {
            val linearLayout = LinearLayout(requireActivity())
            linearLayout.orientation = LinearLayout.HORIZONTAL
            val layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            layoutParams.setMargins((marginStart * scale + 0.5f).toInt(), 0, 0, 0)
            linearLayout.layoutParams = layoutParams
            val value = TextView(requireActivity())
            value.text = list[key]
            value.setPadding(10, 10, 10, 10)
            value.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            value.gravity = Gravity.END
            value.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val text = TextView(requireActivity())
            text.text = key
            text.setPadding(10, 10, 10, 10)
            if (i % 2 == 0) {
                text.setBackgroundColor(Color.parseColor("#e5e7ed"))
                value.setBackgroundColor(Color.parseColor("#e5e7ed"))
            } else {
                text.setBackgroundColor(Color.parseColor("#f6f6f6"))
                value.setBackgroundColor(Color.parseColor("#f6f6f6"))
            }
            text.setTextColor(Color.parseColor("#807f8c"))
            value.setTextColor(Color.parseColor("#807f8c"))
            linearLayout.addView(text)
            linearLayout.addView(value)
            parentLayout!!.addView(linearLayout)
        }
    }

    private fun setTopCharacterImage(topCharacterName: String) {
        Glide.with(this).load(getOWPortraitImage(topCharacterName.lowercase(Locale.getDefault())))
            .into(binding.topCharacter)
    }

    private fun setRatingInformation() {
        if (viewModel.getProfile().value!!.rating > 0) {
            for (i in viewModel.getProfile().value!!.ratings.indices) {
                if (viewModel.getProfile().value!!.ratings[i].role == "tank" && viewModel.getProfile().value!!.ratings[i].level > 0) {
                    binding.ratingTank.text =
                        viewModel.getProfile().value!!.ratings[i].level.toString()
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].roleIcon,
                        binding.ratingIconTank
                    )
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].rankIcon,
                        binding.ratingIconRankTank
                    )
                } else {
                    if (viewModel.getProfile().value!!.ratings[i].level == 0) {
                        binding.ratingIconTank.visibility = View.GONE
                        binding.ratingIconRankTank.visibility = View.GONE
                        binding.ratingTank.visibility = View.GONE
                    }
                }
                if (viewModel.getProfile().value!!.ratings[i].role == "damage" && viewModel.getProfile().value!!.ratings[i].level > 0) {
                    binding.ratingDamage.text =
                        viewModel.getProfile().value!!.ratings[i].level.toString()
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].roleIcon,
                        binding.ratingIconDamage
                    )
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].rankIcon,
                        binding.ratingIconRankDamage
                    )
                } else {
                    if (viewModel.getProfile().value!!.ratings[i].level == 0) {
                        binding.ratingIconDamage.visibility = View.GONE
                        binding.ratingIconRankDamage.visibility = View.GONE
                        binding.ratingDamage.visibility = View.GONE
                    }
                }
                if (viewModel.getProfile().value!!.ratings[i].role == "support" && viewModel.getProfile().value!!.ratings[i].level > 0) {
                    binding.ratingSupport.text =
                        viewModel.getProfile().value!!.ratings[i].level.toString()
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].roleIcon,
                        binding.ratingIconSupport
                    )
                    downloadRatingIcon(
                        viewModel.getProfile().value!!.ratings[i].rankIcon,
                        binding.ratingIconRankSupport
                    )
                } else {
                    if (viewModel.getProfile().value!!.ratings[i].level == 0) {
                        binding.ratingIconSupport.visibility = View.GONE
                        binding.ratingIconRankSupport.visibility = View.GONE
                        binding.ratingSupport.visibility = View.GONE
                    }
                }
            }
        } else {
            binding.ratingIconSupport.visibility = View.GONE
            binding.ratingIconRankSupport.visibility = View.GONE
            binding.ratingSupport.visibility = View.GONE
            binding.ratingIconDamage.visibility = View.GONE
            binding.ratingIconRankDamage.visibility = View.GONE
            binding.ratingDamage.visibility = View.GONE
            binding.ratingIconTank.visibility = View.GONE
            binding.ratingIconRankTank.visibility = View.GONE
            binding.ratingTank.visibility = View.GONE
            val view = requireActivity().findViewById<View>(R.id.view2)
            view.visibility = View.GONE
        }
    }

    private fun setGamesWon() {
        val tempGames = viewModel.getProfile().value!!.gamesWon.toString() + " games won"
        binding.gamesWon.text = tempGames
    }

    private fun setName() {
        if (viewModel.getProfile().value!!.name.contains("#")) {
            val hashtag = viewModel.getProfile().value!!.name.indexOf("#")
            val tempName = viewModel.getProfile().value!!.name.substring(0, hashtag) + " "
            binding.name.text = tempName
        } else {
            val accountInfo = viewModel.getProfile().value!!.name + " "
            binding.name.text = accountInfo
        }
        binding.level.text = viewModel.getProfile().value!!.level.toString()
    }

    private fun downloadAvatar() {
        Glide.with(this).load(viewModel.getProfile().value!!.icon).into(binding.avatar)
    }

    private fun downloadRatingIcon(url: String, imageView: ImageView?) {
        Glide.with(this).load(url).into(imageView!!)
    }

    private fun downloadLevelIcon() {
        Glide.with(this).load(viewModel.getProfile().value!!.levelIcon).into(binding.levelIcon)
        Glide.with(this).load(viewModel.getProfile().value!!.prestigeIcon)
            .into(binding.prestigeIcon)
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages!!.OW_ACCOUNT_NOT_FOUND
            }
            else -> {
                errorMessages!!.TURN_ON_CONNECTION_MESSAGE
            }
        }

    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages!!.ACCOUNT_NOT_FOUND
            }
            else -> {
                errorMessages!!.NO_INTERNET
            }
        }
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        binding.loadingCircle.visibility = View.GONE
        NetworkUtils.loading = false

        val dialog = DialogPrompt(requireActivity())
        dialog.setCancellable(false)

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
            .addMessage(getErrorMessage(responseCode), 18f, "message")
            .addButtons(
                dialog.Button(errorMessages!!.RETRY, 18f, {
                    dialog.dismiss()
                    viewModel.downloadAccountInformation(username!!, platform!!)
                    binding.loadingCircle.visibility = View.VISIBLE
                    NetworkUtils.loading = true
                }, "retry"), dialog.Button(
                    errorMessages!!.BACK, 18f,

                    {
                        dialog.dismiss()
                        parentFragmentManager.beginTransaction().remove(this).commit()
                        NewsPageFragment.addOnBackPressCallback(activity as NavigationActivity)
                    }, "back"
                )
            ).show()
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (activity.supportFragmentManager.findFragmentByTag(FragmentTag.OWFAVORITES.name) != null) {
                    OWFavoritesFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack()
                } else {
                    NewsPageFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack()
                }
            }
        }
    }
}