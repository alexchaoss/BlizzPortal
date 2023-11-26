package com.BlizzardArmory.ui.warcraft.character.armory

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowCharacterFragmentBinding
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.equipment.EquippedItem
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacter
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacters
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.guild.navigation.GuildNavFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.WoWClassColor
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.state.FavoriteState
import com.BlizzardArmory.util.state.FragmentTag
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import java.util.*

class WoWCharacterFragment : Fragment() {
    private var dialog: DialogPrompt? = null
    lateinit var errorMessages: ErrorMessages

    //Character information
    private var nameView: TextView? = null
    private var statsView: TextView? = null
    private val gearImageView = HashMap<String, ImageView>()

    private var _binding: WowCharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WoWCharacterViewModel by activityViewModels()

    private lateinit var navigationActivity: NavigationActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowCharacterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigationActivity = (requireActivity() as NavigationActivity)
        NetworkUtils.loading = true
        errorMessages = ErrorMessages(this.resources)
        val bundle = requireArguments()
        viewModel.realm = bundle.getString("realm")!!
        viewModel.character = bundle.getString("character")!!
        if (Gson().fromJson(bundle.getString("media"), Media::class.java) != null) {
            viewModel.getMedia().value = Gson().fromJson(bundle.getString("media"), Media::class.java)
        } else {
            viewModel.getMedia().value = null
        }
        viewModel.region = bundle.getString("region")!!

        val linearLayoutItemStats = LinearLayout(view.context)
        linearLayoutItemStats.orientation = LinearLayout.VERTICAL
        linearLayoutItemStats.gravity = Gravity.CENTER
        binding.itemStats.addView(linearLayoutItemStats)
        statsView = TextView(view.context)
        nameView = TextView(view.context)
        linearLayoutItemStats.addView(nameView)
        linearLayoutItemStats.addView(statsView)

        navigationActivity.toggleFavoriteButton(FavoriteState.Shown)

        activateCloseButton()

        val layoutParamsName =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        val layoutParamsStats =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        layoutParamsName.setMargins(20, 20, 20, -5)
        nameView?.layoutParams = layoutParamsName
        layoutParamsStats.setMargins(20, 0, 20, 0)
        statsView?.layoutParams = layoutParamsStats

        gearImageView["HEAD"] = binding.head
        gearImageView["NECK"] = binding.neck
        gearImageView["SHOULDER"] = binding.shoulder
        gearImageView["BACK"] = binding.back
        gearImageView["CHEST"] = binding.chest
        gearImageView["SHIRT"] = binding.shirt
        gearImageView["TABARD"] = binding.tabard
        gearImageView["WRIST"] = binding.wrist
        gearImageView["HANDS"] = binding.hands
        gearImageView["WAIST"] = binding.waist
        gearImageView["LEGS"] = binding.legs
        gearImageView["FEET"] = binding.feet
        gearImageView["FINGER_1"] = binding.finger1
        gearImageView["FINGER_2"] = binding.finger2
        gearImageView["TRINKET_1"] = binding.trinket1
        gearImageView["TRINKET_2"] = binding.trinket2
        gearImageView["MAIN_HAND"] = binding.mainHand
        gearImageView["OFF_HAND"] = binding.offHand
        setObservers()
        startDownloads()
    }

    fun setObservers() {
        viewModel.getShowErrorDialog().observe(viewLifecycleOwner) {
            if (dialog == null) {
                callErrorAlertDialog(viewModel.errorCode.value!!)
            }
        }

        viewModel.getCharacterSummary().observe(viewLifecycleOwner) {
            binding.characterName.text = it.name
            binding.characterName.setTextColor(Color.parseColor(WoWClassColor.getClassColor(it.characterClass.id)))
            if (it.guild == null) {
                binding.guild.visibility = View.GONE
            } else {
                val guild = "&lt;<u>${it.guild?.name}</u>&gt;"
                binding.guild.text = HtmlCompat.fromHtml(guild, HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.guild.setOnClickListener { _ ->
                    navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
                    val fragment: Fragment = GuildNavFragment()
                    val bundle = Bundle()
                    bundle.putString("realm", it.realm.slug)
                    bundle.putString("guildName", it.guild?.name)
                    bundle.putString("region", viewModel.region.lowercase(Locale.getDefault()))
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(R.id.fragment, fragment, FragmentTag.WOWGUILDNAVFRAGMENT.name)
                        .addToBackStack("wow_guild")
                        .commit()
                    requireActivity().supportFragmentManager.executePendingTransactions()
                }
            }
            manageFavorite(it)
            EventBus.getDefault().postSticky(FactionEvent(it.faction.type.lowercase(Locale.getDefault())))
            EventBus.getDefault().postSticky(ClassEvent(it.characterClass.id))
            WoWClassName.setBackground(binding.itemFragment, binding.background, requireContext(), it.characterClass.id)
            setTopCharacterStrings(it)
            viewModel.downloadEquipment()
        }

        viewModel.getMedia().observe(viewLifecycleOwner) { media ->
            if (NetworkUtils.classic == null && NetworkUtils.classic1x == null) {
                val mediaAsset = media?.assets?.firstOrNull { it.key.contains("main") }?.value
                if (mediaAsset != null) {
                    Glide.with(this).load(mediaAsset)
                        .placeholder(R.color.colorPrimaryDark)
                        .override(1080, 1440)
                        .into(binding.characterAsset)
                } else {
                    viewModel.downloadBackground()
                }
            }
        }

        viewModel.getCharacterStats().observe(viewLifecycleOwner) {
            setCharacterInformationTextviews(it)
        }

        viewModel.getEquipment().observe(viewLifecycleOwner) {
            for (i in it.equippedItems.indices) {
                Log.i("equipitemsmedia", "test")
                viewModel.downloadIcons(it.equippedItems[i])
                viewModel.setCharacterItemsInformation(it.equippedItems[i])
                setOnPressItemInformation(it.equippedItems[i])
            }
        }

        viewModel.getIconURLs().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                return@observe
            }
            val equippedItems = viewModel.getEquipment().value!!.equippedItems
            binding.loadingCircle.visibility = View.GONE
            val errorIcon = ResourcesCompat.getDrawable(resources, R.drawable.error_icon, context?.theme)
            for (item in equippedItems) {
                gearImageView[item.slot.type].let { imgView ->
                    if (imgView != null) {
                        Glide.with(this).load(it[item.slot.type])
                            .placeholder(R.drawable.loading_placeholder)
                            .into(imgView)
                    }
                    if (it[item.slot.type] == "empty") {
                        setIcon(item, errorIcon)
                    } else {
                        setIcon(item, null)
                    }
                }
            }
        }
    }

    private fun startDownloads() {
        dialog = null
        binding.loadingCircle.visibility = View.VISIBLE
        viewModel.downloadCharacterSummary()
        viewModel.downloadStats()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        navigationActivity.toggleFavoriteButton(FavoriteState.Hidden)
        requireActivity().viewModelStore.clear()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun activateCloseButton() {
        binding.closeButton.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.performClick()
                binding.itemScrollView.visibility = View.GONE
            }
            false
        }
    }

    private fun setTopCharacterStrings(characterSummary: CharacterSummary) {
        binding.itemLvl.text = String.format("Item Level : %s", characterSummary.equippedItemLevel)
        val levelRaceClassString =
            characterSummary.level.toString() + " " + characterSummary.race.name + " " + characterSummary.characterClass.name
        binding.levelRaceClass.text = levelRaceClassString
    }

    private fun manageFavorite(characterSummary: CharacterSummary) {
        var favoriteCharacters = FavoriteCharacters()
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("wow-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "{\"characters\":[]}" && favoriteCharactersString != "DEFAULT") {
            favoriteCharacters =
                gson.fromJson(favoriteCharactersString, FavoriteCharacters::class.java)
            var indexOfCharacter = -1
            var indexTemp = 0
            for (favoriteCharacter in favoriteCharacters.characters) {
                if (hasCharacter(favoriteCharacter, characterSummary)) {
                    indexOfCharacter = indexTemp
                    navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                    favoriteCharacters.characters[indexOfCharacter] =
                        FavoriteCharacter(characterSummary, viewModel.region)
                    prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                    break
                } else {
                    addToFavorite(favoriteCharacters, characterSummary, gson, prefs)
                }
                indexTemp++
            }
            deleteFavorite(favoriteCharacters, characterSummary, indexOfCharacter, gson, prefs)
        } else {
            addToFavorite(favoriteCharacters, characterSummary, gson, prefs)
        }
    }

    private fun hasCharacter(
        favoriteCharacter: FavoriteCharacter,
        characterSummary: CharacterSummary
    ): Boolean {
        return (characterSummary.name.equals(
            favoriteCharacter.characterSummary?.name,
            ignoreCase = true
        )
                && characterSummary.realm.slug == favoriteCharacter.characterSummary?.realm?.slug
                && viewModel.region.equals(favoriteCharacter.region, ignoreCase = true))
    }

    private fun deleteFavorite(
        favoriteCharacters: FavoriteCharacters,
        characterSummary: CharacterSummary,
        indexOfCharacter: Int,
        gson: Gson,
        prefs: SharedPreferences
    ) {
        if (navigationActivity.favorite!!.tag == R.drawable.ic_star_black_24dp && indexOfCharacter != -1) {
            navigationActivity.favorite!!.setOnClickListener {
                navigationActivity.toggleFavoriteButton(FavoriteState.Shown)
                favoriteCharacters.characters.removeAt(indexOfCharacter)
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Snackbar.make(
                    binding.root,
                    "Character removed from favorites",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                addToFavorite(favoriteCharacters, characterSummary, gson, prefs)

            }
        }
    }

    private fun addToFavorite(
        favoriteCharacters: FavoriteCharacters,
        characterSummary: CharacterSummary,
        gson: Gson,
        prefs: SharedPreferences
    ) {
        navigationActivity.favorite!!.setOnClickListener {
            var containsCharacter = false
            var indexOfCharacter = 0
            for (characters in favoriteCharacters.characters) {
                if (hasCharacter(characters, characterSummary)) {
                    containsCharacter = true
                    break
                }
                indexOfCharacter++
            }

            if (!containsCharacter) {
                navigationActivity.toggleFavoriteButton(FavoriteState.Full)
                favoriteCharacters.characters.add(
                    FavoriteCharacter(
                        characterSummary,
                        viewModel.region
                    )
                )
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Snackbar.make(binding.root, "Character added to favorites", Snackbar.LENGTH_SHORT)
                    .show()
                deleteFavorite(favoriteCharacters, characterSummary, indexOfCharacter, gson, prefs)
            }
        }
    }

    private fun setIcon(equippedItem: EquippedItem, item: Drawable?) {
        if (item != null) {
            gearImageView[equippedItem.slot.type]?.setImageDrawable(item)
        }
        gearImageView[equippedItem.slot.type]?.clipToOutline = true
        val backgroundStroke = itemColor(equippedItem, GradientDrawable())
        if (equippedItem.azeriteDetails != null && equippedItem.item.id != 158075L) {
            when (equippedItem.slot.type) {
                "HEAD" -> {
                    binding.headAzerite.setImageResource(R.drawable.azerite_gear_border)
                    binding.headAzerite.visibility = View.VISIBLE
                }
                "SHOULDER" -> {
                    binding.shoulderAzerite.setImageResource(R.drawable.azerite_gear_border)
                    binding.shoulderAzerite.visibility = View.VISIBLE
                }
                "CHEST" -> {
                    binding.chestAzerite.setImageResource(R.drawable.azerite_gear_border)
                    binding.chestAzerite.visibility = View.VISIBLE
                }
            }
        } else {
            when (equippedItem.slot.type) {
                "HEAD" -> binding.headAzerite.visibility = View.INVISIBLE
                "SHOULDER" -> binding.shoulderAzerite.visibility = View.INVISIBLE
                "CHEST" -> binding.chestAzerite.visibility = View.INVISIBLE
            }
        }
        gearImageView[equippedItem.slot.type]?.background = backgroundStroke
    }

    private fun itemColor(equippedItem: EquippedItem, drawable: GradientDrawable): Drawable {
        drawable.cornerRadius = 3f
        drawable.setColor(Color.parseColor("#000000"))
        drawable.setStroke(3, viewModel.getItemColor(equippedItem))
        return drawable
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(equippedItem: EquippedItem) {
        val imageView = gearImageView[equippedItem.slot.type]
        imageView?.setOnTouchListener { _: View?, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                nameView?.text = viewModel.nameList[equippedItem.slot.type]
                nameView?.setTextColor(viewModel.getItemColor(equippedItem))
                nameView?.textSize = 20f
                statsView?.text =
                    HtmlCompat.fromHtml(
                        viewModel.stats[equippedItem.slot.type]!!,
                        HtmlCompat.FROM_HTML_MODE_LEGACY,
                        { source: String? ->
                            val resourceId =
                                resources.getIdentifier(
                                    source,
                                    "drawable",
                                    BuildConfig.APPLICATION_ID
                                )
                            val drawable =
                                ResourcesCompat.getDrawable(resources, resourceId, context?.theme)
                            drawable?.setBounds(
                                0,
                                0,
                                drawable.intrinsicWidth,
                                drawable.intrinsicHeight
                            )
                            drawable
                        },
                        null
                    )
                statsView?.setTextColor(Color.WHITE)
                statsView?.textSize = 13f
                binding.itemScrollView.setPadding(10, 10, 10, 10)
                binding.itemScrollView.background = imageView.background
                binding.itemScrollView.visibility = View.VISIBLE
                binding.itemScrollView.isVerticalScrollBarEnabled = true
                return@setOnTouchListener true
            } else if (binding.itemScrollView.visibility == View.VISIBLE && event.action == MotionEvent.ACTION_DOWN) {
                binding.itemScrollView.visibility = View.GONE
            }
            return@setOnTouchListener true
        }
        val currentY = binding.scrollView3.scrollY
        binding.scrollView3.viewTreeObserver?.addOnScrollChangedListener {

            if (_binding != null) {
                if (currentY != binding.scrollView3.scrollY) {
                    binding.itemScrollView.visibility = View.GONE
                }
            }
        }
    }

    private fun setCharacterInformationTextviews(statistic: Statistic) {
        binding.health.text =
            String.format("%s: %s", resources.getString(R.string.health), statistic.health)
        binding.power.text = String.format("%s: %s", statistic.powerType.name, statistic.power)
        binding.strength.text =
            String.format(
                "%s %s",
                resources.getString(R.string.strength),
                statistic.strength.effective
            )
        binding.agility.text =
            String.format(
                "%s: %s",
                resources.getString(R.string.agility),
                statistic.agility.effective
            )
        binding.intellect.text =
            String.format(
                "%s: %s",
                resources.getString(R.string.intellect),
                statistic.intellect.effective
            )
        binding.stamina.text =
            String.format(
                "%s: %s",
                resources.getString(R.string.stamina),
                statistic.stamina.effective
            )
        binding.crit.text =
            String.format(
                Locale.ENGLISH,
                "%s: %.2f%%",
                resources.getString(R.string.crit),
                statistic.meleeCrit.value
            )
        binding.haste.text =
            String.format(
                Locale.ENGLISH,
                "%s: %.2f%%",
                resources.getString(R.string.haste),
                statistic.meleeHaste.value.toDouble()
            )
        binding.mastery.text =
            String.format(
                Locale.ENGLISH,
                "%s: %.2f%%",
                resources.getString(R.string.mastery),
                statistic.mastery.value.toDouble()
            )
        binding.versatility.text =
        String.format(
            Locale.ENGLISH,
            "%s: %.2f%%",
            resources.getString(R.string.vers),
            statistic.versatilityDamageDoneBonus.toDouble()
        )
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            in 400..499 -> {
                errorMessages.LOGIN_TO_UPDATE
            }
            500 -> {
                errorMessages.BLIZZ_SERVERS_DOWN
            }
            else -> {
                errorMessages.TURN_ON_CONNECTION_MESSAGE
            }
        }

    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            in 400..499 -> {
                errorMessages.INFORMATION_OUTDATED
            }
            500 -> {
                errorMessages.SERVERS_ERROR
            }
            else -> {
                errorMessages.NO_INTERNET
            }
        }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        binding.loadingCircle.visibility = View.GONE
        NetworkUtils.loading = false
        if (dialog == null) {
            if (responseCode == 404) {
                    dialog = DialogPrompt(requireActivity())
                    dialog!!.setCancellable(false)
                    dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                        .addMessage(getErrorMessage(responseCode), 18f, "message")
                        .addButtons(
                            dialog!!.Button(errorMessages.OK, 18f, {
                                dialog!!.dismiss()
                                dialog = null
                            }, "retry"), dialog!!.Button(
                                errorMessages.BACK, 18f,

                                {
                                    dialog!!.dismiss()
                                    dialog = null
                                    EventBus.getDefault().post(NetworkEvent(true))
                                },
                                "back"
                            )
                        ).show()

            } else {
                dialog = DialogPrompt(requireActivity())
                dialog!!.setCancellable(false)
                dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                    .addMessage(getErrorMessage(responseCode), 18f, "message")
                    .addButtons(
                        dialog!!.Button(errorMessages.RETRY, 18f, {
                            dialog!!.dismiss()
                            dialog = null
                            startDownloads()
                            EventBus.getDefault().post(RetryEvent(true))
                            binding.loadingCircle.visibility = View.VISIBLE
                            NetworkUtils.loading = true
                        }, "retry"), dialog!!.Button(
                            errorMessages.BACK, 18f,
                            {
                                dialog!!.dismiss()
                                dialog = null
                                EventBus.getDefault().post(NetworkEvent(true))
                            }, "back"
                        )
                    ).show()
            }
        }
    }

    companion object {
        var faction: String? = null
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                when {
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWFRAGMENTCLASSIC.name) != null -> {
                        AccountFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }

                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWFRAGMENTCLASSICERA.name) != null -> {
                        AccountFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }

                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWFRAGMENT.name) != null -> {
                        AccountFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }

                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWFAVORITES.name) != null -> {
                        WoWFavoritesFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }

                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWGUILDNAVFRAGMENT.name) != null -> {
                        ActivityFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWMPLUSLEADERBOARD.name) != null -> {
                        ActivityFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWPVPLEADERBOARD.name) != null -> {
                        ActivityFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    else -> {
                        NewsListFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack(
                            null,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                    }
                }
            }
        }
    }
}