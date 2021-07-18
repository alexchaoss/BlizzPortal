package com.BlizzardArmory.ui.warcraft.character.armory

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.*
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
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
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.guild.navigation.GuildNavFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.WoWClassColor
import com.BlizzardArmory.util.events.*
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
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
    private val viewModel: WoWCharacterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowCharacterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NetworkUtils.loading = true
        errorMessages = ErrorMessages(this.resources)
        val bundle = requireArguments()
        viewModel.realm = bundle.getString("realm")!!
        viewModel.character = bundle.getString("character")!!
        if (Gson().fromJson(bundle.getString("media"), Media::class.java) != null) {
            viewModel.getMedia().value =
                Gson().fromJson(bundle.getString("media"), Media::class.java)
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

        NavigationActivity.favorite!!.visibility = View.VISIBLE
        NavigationActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
        NavigationActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp

        activateCloseButton()

        val layoutParamsName =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsStats =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
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
        viewModel.getBnetParams().value =
            activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
    }

    fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            startDownloads()
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            callErrorAlertDialog(it)
        })

        viewModel.getTalentsInfo().observe(viewLifecycleOwner, {
            viewModel.downloadTalentIconsInfo()
        })

        viewModel.getTalentsIcons().observe(viewLifecycleOwner, {
            binding.tabLayout.clearOnTabSelectedListeners()
            setTalentInformation()
        })

        viewModel.getCharacterSummary().observe(viewLifecycleOwner, {
            viewModel.downloadTalents()
            setBackgroundColor()
            binding.characterName.text = it.name
            binding.characterName.setTextColor(Color.parseColor(WoWClassColor.getClassColor(it.characterClass.id)))
            if (it.guild == null) {
                binding.guild.visibility = View.GONE
            } else {
                val guild = "&lt;<u>${it.guild.name}</u>&gt;"
                binding.guild.text = HtmlCompat.fromHtml(guild, HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.guild.setOnClickListener { view ->
                    NavigationActivity.favorite?.visibility = View.GONE
                    val fragment: Fragment = GuildNavFragment()
                    val bundle = Bundle()
                    bundle.putString("realm", it.realm.slug)
                    bundle.putString("guildName", it.guild.name)
                    bundle.putString("region", viewModel.region.lowercase(Locale.getDefault()))
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(R.id.fragment, fragment, "guild_nav_fragment")
                        .addToBackStack("wow_guild")
                        .commit()
                    requireActivity().supportFragmentManager.executePendingTransactions()
                }
            }
            manageFavorite(it)
            EventBus.getDefault().post(FactionEvent(it.faction.type.lowercase(Locale.getDefault())))
            EventBus.getDefault().post(ClassEvent(it.characterClass.id))
            setTopCharacterStrings(it)
            viewModel.downloadEquipment()
        })

        viewModel.getMedia().observe(viewLifecycleOwner, { media ->
            if (media != null) {
                Glide.with(this).load(media.assets?.first { it.key == "main" }?.value)
                    .placeholder(R.color.colorPrimaryDark).override(1080, 1440).centerCrop()
                    .into(binding.background)
            } else {
                viewModel.downloadBackground()
            }
        })

        viewModel.getCharacterStats().observe(viewLifecycleOwner, {
            setCharacterInformationTextviews(it)
        })

        viewModel.getEquipment().observe(viewLifecycleOwner, {
            for (i in it.equippedItems.indices) {
                viewModel.downloadIcons(it.equippedItems[i])
                viewModel.setCharacterItemsInformation(it.equippedItems[i])
                setOnPressItemInformation(it.equippedItems[i])
            }
        })

        viewModel.getIconURLs().observe(viewLifecycleOwner, {
            val equippedItems = viewModel.getEquipment().value!!.equippedItems
            binding.loadingCircle.visibility = View.GONE
            val errorIcon =
                ResourcesCompat.getDrawable(resources, R.drawable.error_icon, context?.theme)
            for (item in equippedItems) {
                Glide.with(this).load(it[item.slot.type])
                    .placeholder(R.drawable.loading_placeholder)
                    .into(gearImageView[item.slot.type]!!)
                if (it[item.slot.type] == "empty") {
                    setIcon(item, errorIcon)
                } else {
                    setIcon(item, null)
                }
            }
        })
    }

    private fun startDownloads() {
        dialog = null
        binding.loadingCircle.visibility = View.VISIBLE
        viewModel.downloadCharacterSummary()
        viewModel.downloadStats()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun talentClickedReceived(talentClickedEvent: TalentClickedEvent) {
        if (talentClickedEvent.touch) {
            binding.talentTooltip.visibility = View.VISIBLE
            if (talentClickedEvent.powerCost == null || talentClickedEvent.powerCost == "") {
                binding.spellCost.visibility = View.GONE
            } else {
                binding.spellCost.visibility = View.VISIBLE
            }
            binding.spellName.text = talentClickedEvent.name
            binding.spellCost.text = talentClickedEvent.powerCost
            binding.spellCast.text = talentClickedEvent.castTime
            binding.spellCd.text = talentClickedEvent.cooldown
            binding.spellDescription.text = talentClickedEvent.description
        } else {
            binding.talentTooltip.visibility = View.GONE
        }
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
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
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
                    NavigationActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                    NavigationActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
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

    private fun hasCharacter(favoriteCharacter: FavoriteCharacter, characterSummary: CharacterSummary): Boolean {
        return (characterSummary.name.equals(favoriteCharacter.characterSummary?.name, ignoreCase = true)
                && characterSummary.realm.slug == favoriteCharacter.characterSummary?.realm?.slug
                && viewModel.region.equals(favoriteCharacter.region, ignoreCase = true))
    }

    private fun deleteFavorite(favoriteCharacters: FavoriteCharacters, characterSummary: CharacterSummary, indexOfCharacter: Int, gson: Gson, prefs: SharedPreferences) {
        if (NavigationActivity.favorite!!.tag == R.drawable.ic_star_black_24dp && indexOfCharacter != -1) {
            NavigationActivity.favorite!!.setOnClickListener {
                NavigationActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                NavigationActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp
                favoriteCharacters.characters.removeAt(indexOfCharacter)
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Toast.makeText(requireActivity(), "Character removed from favorites", Toast.LENGTH_SHORT)
                    .show()
                addToFavorite(favoriteCharacters, characterSummary, gson, prefs)

            }
        }
    }

    private fun addToFavorite(favoriteCharacters: FavoriteCharacters, characterSummary: CharacterSummary, gson: Gson, prefs: SharedPreferences) {
        NavigationActivity.favorite!!.setOnClickListener {
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
                NavigationActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                NavigationActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                favoriteCharacters.characters.add(FavoriteCharacter(characterSummary, viewModel.region))
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Toast.makeText(requireActivity(), "Character added to favorites", Toast.LENGTH_SHORT)
                    .show()
                deleteFavorite(favoriteCharacters, characterSummary, indexOfCharacter, gson, prefs)
            }
        }
    }

    private fun setBackgroundColor() {
        when (viewModel.getCharacterSummary().value!!.characterClass.id) {
            6 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#080812"))
            }
            12 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#000900"))
            }
            11 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#04100a"))
            }
            3 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#0f091b"))
            }
            8 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#110617"))
            }
            10 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#040b17"))
            }
            2 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#13040a"))
            }
            5 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#15060e"))
            }
            4 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#160720"))
            }
            7 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#050414"))
            }
            9 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#080516"))
            }
            1 -> {
                binding.itemFragment.setBackgroundColor(Color.parseColor("#1a0407"))
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
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    statsView?.text =
                        Html.fromHtml(viewModel.stats[equippedItem.slot.type], Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId =
                                resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable =
                                ResourcesCompat.getDrawable(resources, resourceId, context?.theme)
                            drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                } else {
                    statsView?.text =
                        Html.fromHtml(viewModel.stats[equippedItem.slot.type], { source: String? ->
                            val resourceId =
                                resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable =
                                ResourcesCompat.getDrawable(resources, resourceId, context?.theme)
                            drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                }
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
                    binding.talentTooltip.visibility = View.GONE
                }
            }
        }
    }

    private fun setTalentInformation() {
        val talents = viewModel.getTalentsInfo().value!!
        val talentsIcons = viewModel.getTalentsIcons().value!!
        when (talents.specializations.size) {
            4 -> {
                binding.tabLayout.addTab(binding.tabLayout.newTab())
                binding.tabLayout.getTabAt(3)?.text = talents.specializations[3].specialization.name
            }
            2 -> {
                binding.tabLayout.getTabAt(2)?.let { binding.tabLayout.removeTab(it) }
            }
            1 -> {
                binding.tabLayout.getTabAt(2)?.let { binding.tabLayout.removeTab(it) }
                binding.tabLayout.getTabAt(1)?.let { binding.tabLayout.removeTab(it) }
            }
        }

        for (i in talents.specializations.indices) {
            val tab = binding.tabLayout.getTabAt(i)
            tab?.text = talents.specializations[i].specialization.name
        }

        try {
            binding.talentRecycler.apply {
                adapter = TalentAdapter(talents.specializations[0].talents, talentsIcons, context)
                adapter!!.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            binding.noTalent.visibility = View.VISIBLE
        }


        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                try {
                    binding.talentRecycler.apply {
                        adapter =
                            TalentAdapter(talents.specializations[tab.position].talents, talentsIcons, context)
                        adapter!!.notifyDataSetChanged()
                    }
                    binding.noTalent.visibility = View.GONE
                } catch (e: Exception) {
                    binding.talentRecycler.apply {
                        adapter = null
                    }
                    binding.noTalent.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun setCharacterInformationTextviews(statistic: Statistic) {
        binding.health.text =
            String.format("%s: %s", resources.getString(R.string.health), statistic.health)
        binding.power.text = String.format("%s: %s", statistic.powerType.name, statistic.power)
        binding.strength.text =
            String.format("%s %s", resources.getString(R.string.strength), statistic.strength.effective)
        binding.agility.text =
            String.format("%s: %s", resources.getString(R.string.agility), statistic.agility.effective)
        binding.intellect.text =
            String.format("%s: %s", resources.getString(R.string.intellect), statistic.intellect.effective)
        binding.stamina.text =
            String.format("%s: %s", resources.getString(R.string.stamina), statistic.stamina.effective)
        binding.crit.text =
            String.format(Locale.ENGLISH, "%s: %.2f%%", resources.getString(R.string.crit), statistic.meleeCrit.value)
        binding.haste.text =
            String.format(Locale.ENGLISH, "%s: %.2f%%", resources.getString(R.string.haste), statistic.meleeHaste.value.toDouble())
        binding.mastery.text =
            String.format(Locale.ENGLISH, "%s: %.2f%%", resources.getString(R.string.mastery), statistic.mastery.value.toDouble())
        binding.versatility.text = ""
            String.format(Locale.ENGLISH, "%s: %.2f%%", resources.getString(R.string.vers), statistic.versatilityDamageDoneBonus.toDouble())
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
        if (NetworkUtils.loading) {
            binding.loadingCircle.visibility = View.GONE
            NetworkUtils.loading = false

            if (responseCode == 404) {
                dialog = DialogPrompt(requireActivity())
                dialog!!.setCancellable(false)
                dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                    .addMessage(getErrorMessage(responseCode), 18f, "message")
                    .addButtons(
                        dialog!!.Button(errorMessages.OK, 18f, {
                            dialog!!.dismiss()
                        }, "retry"), dialog!!.Button(
                            errorMessages.BACK, 18f,

                            {
                                dialog!!.dismiss()
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
                            startDownloads()
                            EventBus.getDefault().post(RetryEvent(true))
                            binding.loadingCircle.visibility = View.VISIBLE
                            NetworkUtils.loading = true
                        }, "retry"), dialog!!.Button(
                            errorMessages.BACK, 18f,

                            {
                                dialog!!.dismiss()
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
                NavigationActivity.hideFavoriteButton()
                if (!NetworkUtils.loading) {
                    when {
                        activity.supportFragmentManager.findFragmentByTag("wowfragment") != null -> {
                            AccountFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        activity.supportFragmentManager.findFragmentByTag("wowfavorites") != null -> {
                            WoWFavoritesFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        activity.supportFragmentManager.findFragmentByTag("guild_nav_fragment") != null -> {
                            ActivityFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        else -> {
                            NewsListFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        }
                    }
                }
            }
        }
    }
}