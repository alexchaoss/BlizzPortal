package com.BlizzardArmory.ui.ui_warcraft.character

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.WowCharacterFragmentBinding
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.equipment.Equipment
import com.BlizzardArmory.model.warcraft.equipment.EquippedItem
import com.BlizzardArmory.model.warcraft.equipment.Set
import com.BlizzardArmory.model.warcraft.equipment.Socket
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacter
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacters
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.model.warcraft.talents.Talents
import com.BlizzardArmory.model.warcraft.talents.TalentsIcons
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.ui_warcraft.account.AccountFragment
import com.BlizzardArmory.ui.ui_warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.*
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class WoWCharacterFragment : Fragment() {
    private var characterRealm: String? = null
    private var characterClicked: String? = null
    private var region: String? = null
    private var dialog: DialogPrompt? = null
    lateinit var errorMessages: ErrorMessages

    //Character information
    private lateinit var characterSummary: CharacterSummary
    private var media: Media? = null
    private lateinit var statistic: Statistic
    private lateinit var talentsInfo: Talents
    private var talentsIcons = listOf<TalentsIcons>()
    private lateinit var equipment: Equipment
    private var nameView: TextView? = null
    private var statsView: TextView? = null
    private val imageURLs = HashMap<String, String>()

    private val gearImageView = HashMap<String, ImageView>()
    private val stats = HashMap<String, String>()
    private val nameList = HashMap<String, String>()
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null

    private var _binding: WowCharacterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = WowCharacterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        URLConstants.loading = true
        errorMessages = ErrorMessages(this.resources)
        val bundle = requireArguments()
        characterRealm = bundle.getString("realm")
        characterClicked = bundle.getString("character")
        if (Gson().fromJson(bundle.getString("media"), Media::class.java) != null) {
            media = Gson().fromJson(bundle.getString("media"), Media::class.java)
        }
        region = bundle.getString("region")

        val linearLayoutItemStats = LinearLayout(view.context)
        linearLayoutItemStats.orientation = LinearLayout.VERTICAL
        linearLayoutItemStats.gravity = Gravity.CENTER
        binding.itemStats.addView(linearLayoutItemStats)
        statsView = TextView(view.context)
        nameView = TextView(view.context)
        linearLayoutItemStats.addView(nameView)
        linearLayoutItemStats.addView(statsView)

        GamesActivity.favorite!!.visibility = View.VISIBLE
        GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
        GamesActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp

        activateCloseButton()

        val layoutParamsName = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsStats = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
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

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val battlenetOAuth2Params: BattlenetOAuth2Params = requireActivity().intent.extras!!.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)!!
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params)

        downloadInfo()
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

    private fun downloadInfo() {
        dialog = null
        binding.loadingCircle.visibility = View.VISIBLE
        downloadBackground()
        downloadAndSetCharacterInformation()
        downloadStats()
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

    private fun downloadTalents() {
        val call: Call<Talents> = RetroClient.getClient().getSpecs(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Talents> {
            override fun onResponse(call: Call<Talents>, response: Response<Talents>) {
                if (response.isSuccessful) {
                    talentsInfo = response.body()!!
                    downloadTalentIconsInfo()
                }
            }

            override fun onFailure(call: Call<Talents>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    private fun downloadTalentIconsInfo() {
        val call2: Call<List<TalentsIcons>> = RetroClient.getClient().getTalentsWithIcon(URLConstants.getTalentsIcons(characterSummary.characterClass.id, MainActivity.locale))
        call2.enqueue(object : Callback<List<TalentsIcons>> {
            override fun onResponse(call: Call<List<TalentsIcons>>, response: Response<List<TalentsIcons>>) {
                if (response.isSuccessful) {
                    talentsIcons = response.body()!!
                    setTalentInformation()
                }
            }

            override fun onFailure(call: Call<List<TalentsIcons>>, t: Throwable) {
                downloadTalentIconsInfo()
            }
        })
    }

    private fun downloadStats() {
        val call: Call<Statistic> = RetroClient.getClient().getStats(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Statistic> {
            override fun onResponse(call: Call<Statistic>, response: Response<Statistic>) {
                when {
                    response.code() in 201..Int.MAX_VALUE -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        statistic = response.body()!!
                        setCharacterInformationTextviews()
                    }
                }
            }

            override fun onFailure(call: Call<Statistic>, t: Throwable) {
                Log.e("Error", t.message, t)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun downloadAndSetCharacterInformation() {
        val call: Call<CharacterSummary> = RetroClient.getClient().getCharacter(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<CharacterSummary> {
            override fun onResponse(call: Call<CharacterSummary>, response: Response<CharacterSummary>) {
                when {
                    response.code() in 201..Int.MAX_VALUE -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        characterSummary = response.body()!!
                        downloadTalents()
                        setBackgroundColor()
                        binding.characterName.text = characterSummary.name
                        manageFavorite(characterSummary)
                        EventBus.getDefault().post(FactionEvent(characterSummary.faction.type.toLowerCase(Locale.ROOT)))
                        EventBus.getDefault().post(ClassEvent(characterSummary.characterClass.id))
                        binding.itemLvl.text = String.format("Item Level : %s", characterSummary.equippedItemLevel)
                        val levelRaceClassString = characterSummary.level.toString() + " " + characterSummary.race.name + " " + characterSummary.characterClass.name
                        binding.levelRaceClass.text = levelRaceClassString
                        downloadEquipment()

                    }
                }
            }

            override fun onFailure(call: Call<CharacterSummary>, t: Throwable) {
                Log.e("Error", t.message, t)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun manageFavorite(characterSummary: CharacterSummary) {
        var favoriteCharacters = FavoriteCharacters()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("wow-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "{\"characters\":[]}" && favoriteCharactersString != "DEFAULT") {
            favoriteCharacters = gson.fromJson(favoriteCharactersString, FavoriteCharacters::class.java)
            var indexOfCharacter = -1
            var indexTemp = 0
            for (favoriteCharacter in favoriteCharacters.characters) {
                if (hasCharacter(favoriteCharacter, characterSummary)) {
                    indexOfCharacter = indexTemp
                    GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                    GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                    favoriteCharacters.characters[indexOfCharacter] = FavoriteCharacter(characterSummary, region!!)
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
                && region.equals(favoriteCharacter.region, ignoreCase = true))
    }

    private fun deleteFavorite(favoriteCharacters: FavoriteCharacters, characterSummary: CharacterSummary, indexOfCharacter: Int, gson: Gson, prefs: SharedPreferences) {
        if (GamesActivity.favorite!!.tag == R.drawable.ic_star_black_24dp && indexOfCharacter != -1) {
            GamesActivity.favorite!!.setOnClickListener {
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_border_black_24dp
                favoriteCharacters.characters.removeAt(indexOfCharacter)
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Toast.makeText(requireActivity(), "Character removed from favorites", Toast.LENGTH_SHORT).show()
                addToFavorite(favoriteCharacters, characterSummary, gson, prefs)

            }
        }
    }

    private fun addToFavorite(favoriteCharacters: FavoriteCharacters, characterSummary: CharacterSummary, gson: Gson, prefs: SharedPreferences) {
        GamesActivity.favorite!!.setOnClickListener {
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
                GamesActivity.favorite!!.setImageResource(R.drawable.ic_star_black_24dp)
                GamesActivity.favorite!!.tag = R.drawable.ic_star_black_24dp
                favoriteCharacters.characters.add(FavoriteCharacter(characterSummary, region!!))
                prefs.edit().putString("wow-favorites", gson.toJson(favoriteCharacters)).apply()
                Toast.makeText(requireActivity(), "Character added to favorites", Toast.LENGTH_SHORT).show()
                deleteFavorite(favoriteCharacters, characterSummary, indexOfCharacter, gson, prefs)
            }
        }
    }

    private fun downloadEquipment() {
        val call2: Call<Equipment> = RetroClient.getClient().getEquippedItems(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call2.enqueue(object : Callback<Equipment> {
            override fun onResponse(call: Call<Equipment>, response: Response<Equipment>) {
                when {
                    response.code() in 400..Int.MAX_VALUE -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        equipment = response.body()!!
                        for (i in equipment.equippedItems.indices) {
                            downloadIcons(equipment, i, equipment.equippedItems[i].slot.type)
                            setCharacterItemsInformation(i)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Equipment>, t: Throwable) {
                Log.e("Error", t.message, t)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun downloadBackground() {
        if (media != null) {
            Glide.with(this).load(media?.assets?.first { it.key == "main" }?.value).placeholder(R.color.colorPrimaryDark).override(1080, 1440).centerCrop().into(binding.background)
        } else {
            val call: Call<Media> = RetroClient.getClient().getMedia(characterClicked!!.toLowerCase(Locale.ROOT),
                    characterRealm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
            call.enqueue(object : Callback<Media> {
                override fun onResponse(call: Call<Media>, response: Response<Media>) {
                    if (response.isSuccessful) {
                        media = response.body()!!
                        Glide.with(this@WoWCharacterFragment).load(media?.assets?.first { it.key == "main" }?.value).placeholder(R.drawable.loading_placeholder).into(binding.background)
                    }
                }

                override fun onFailure(call: Call<Media>, t: Throwable) {
                    Log.e("Error", t.message, t)
                    callErrorAlertDialog(0)
                }
            })
        }
    }

    private fun setBackgroundColor() {
        when (characterSummary.characterClass.id) {
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

    private fun downloadIcons(equipment: Equipment?, index: Int, itemSlot: String) {
        var url = equipment?.equippedItems?.get(index)?.media?.key?.href
        if (url!!.contains("static")) {
            url = url.replace(("static-[0-9].[0-9].[0-9]_[0-9]*-" + region?.toLowerCase(Locale.ROOT)?.toRegex()).toRegex(), "static-" + region?.toLowerCase(Locale.ROOT))
        }
        url = url.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
        val call: Call<com.BlizzardArmory.model.warcraft.equipment.media.Media> = RetroClient.getClient().getDynamicEquipmentMedia(url, MainActivity.locale, region?.toLowerCase(Locale.ROOT))
        call.enqueue(object : Callback<com.BlizzardArmory.model.warcraft.equipment.media.Media> {
            override fun onResponse(call: Call<com.BlizzardArmory.model.warcraft.equipment.media.Media>, response: Response<com.BlizzardArmory.model.warcraft.equipment.media.Media>) {
                when {
                    response.code() in 201..Int.MAX_VALUE -> {
                        if (index == equipment?.equippedItems!!.size - 1) {
                            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                            binding.loadingCircle.visibility = View.GONE
                            URLConstants.loading = false
                        }
                        val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                        setIcons(itemSlot, equipment, errorIcon)
                    }
                    response.code() < 200 -> {
                        if (index == equipment?.equippedItems!!.size - 1) {
                            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                            binding.loadingCircle.visibility = View.GONE
                            URLConstants.loading = false
                        }
                        val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                        setIcons(itemSlot, equipment, errorIcon)
                    }
                    else -> {
                        val mediaItem = response.body()!!
                        imageURLs[itemSlot] = mediaItem.assets[0].value
                        Glide.with(this@WoWCharacterFragment).load(imageURLs[itemSlot]).placeholder(R.drawable.loading_placeholder).into(gearImageView[itemSlot]!!)
                        setIcons(itemSlot, equipment, null)
                        if (index == equipment?.equippedItems!!.size - 1) {
                            binding.loadingCircle.visibility = View.GONE
                            URLConstants.loading = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<com.BlizzardArmory.model.warcraft.equipment.media.Media>, t: Throwable) {
                Log.e("Error", t.message, t)
                val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                setIcons(itemSlot, equipment, errorIcon)
                if (index == equipment?.equippedItems!!.size - 1) {
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    binding.loadingCircle.visibility = View.GONE
                    URLConstants.loading = false
                }
            }
        })
    }

    private fun setIcons(itemSlot: String, equipment: Equipment?, item: Drawable?) {
        if (item != null) {
            gearImageView[itemSlot]?.setImageDrawable(item)
        }
        gearImageView[itemSlot]?.clipToOutline = true
        var backgroundStroke: Drawable? = null
        for (i in equipment?.equippedItems?.indices!!) {
            if (equipment.equippedItems[i].slot.type == itemSlot) {
                backgroundStroke = itemColor(equipment.equippedItems[i], GradientDrawable())
                if (equipment.equippedItems[i].azeriteDetails != null && equipment.equippedItems[i].item.id != 158075L) {
                    when (itemSlot) {
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

                    when (itemSlot) {
                        "HEAD" -> binding.headAzerite.visibility = View.INVISIBLE
                        "SHOULDER" -> binding.shoulderAzerite.visibility = View.INVISIBLE
                        "CHEST" -> binding.chestAzerite.visibility = View.INVISIBLE
                    }
                }
            }
        }
        gearImageView[itemSlot]?.background = backgroundStroke
    }

    private fun itemColor(equippedItem: EquippedItem, drawable: GradientDrawable): Drawable {
        drawable.cornerRadius = 3f
        drawable.setColor(Color.parseColor("#000000"))
        drawable.setStroke(3, getItemColor(equippedItem))
        return drawable
    }

    private fun getItemColor(item: EquippedItem): Int {
        try {
            when (item.quality.type) {
                "POOR" -> return Color.parseColor("#9d9d9d")
                "COMMON" -> return Color.parseColor("#ffffff")
                "UNCOMMON" -> return Color.parseColor("#1eff00")
                "RARE" -> return Color.parseColor("#0070dd")
                "EPIC" -> return Color.parseColor("#a335ee")
                "LEGENDARY" -> return Color.parseColor("#ff8000")
                "ARTIFACT" -> return Color.parseColor("#e6ca80")
                "HEIRLOOM" -> return Color.parseColor("#01c5f7")
            }
        } catch (e: Exception) {
            return Color.GRAY
        }
        return 0
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, index: Int) {
        imageView?.setOnTouchListener { _: View?, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                nameView?.text = nameList[equipment.equippedItems[index].slot.type]
                nameView?.setTextColor(getItemColor(equipment.equippedItems[index]))
                nameView?.textSize = 20f
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    statsView?.text = Html.fromHtml(stats[equipment.equippedItems[index].slot.type], Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = ResourcesCompat.getDrawable(resources, resourceId, context?.theme)
                        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                } else {
                    statsView?.text = Html.fromHtml(stats[equipment.equippedItems[index].slot.type], { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = ResourcesCompat.getDrawable(resources, resourceId, context?.theme)
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

        when (talentsInfo.specializations.size) {
            4 -> {
                binding.tabLayout.addTab(binding.tabLayout.newTab())
                binding.tabLayout.getTabAt(3)?.text = talentsInfo.specializations[3].specialization.name
            }
            2 -> {
                binding.tabLayout.getTabAt(2)?.let { binding.tabLayout.removeTab(it) }
            }
            1 -> {
                binding.tabLayout.getTabAt(2)?.let { binding.tabLayout.removeTab(it) }
                binding.tabLayout.getTabAt(1)?.let { binding.tabLayout.removeTab(it) }
            }
        }

        for (i in talentsInfo.specializations.indices) {
            val tab = binding.tabLayout.getTabAt(i)
            tab?.text = talentsInfo.specializations[i].specialization.name
        }

        try {
            binding.talentRecycler.apply {
                adapter = TalentAdapter(talentsInfo.specializations[0].talents, talentsIcons, context)
                adapter!!.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            binding.noTalent.visibility = View.VISIBLE
        }


        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                try {
                    binding.talentRecycler.apply {
                        adapter = TalentAdapter(talentsInfo.specializations[tab.position].talents, talentsIcons, context)
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

    private fun setCharacterItemsInformation(index: Int) {
        val equippedItem = equipment.equippedItems[index]
        nameList[equippedItem.slot.type] = equippedItem.name
        var slot = StringBuilder(equippedItem.inventoryType.name)
        val itemLvl = "<font color=#edc201>" + equippedItem.level.displayString + "</font><br>"
        var nameDescription = ""
        var damageInfo = StringBuilder()
        var durability = ""
        var transmog = ""
        var armor = ""
        var requiredLevel = ""
        var description = ""
        var trigger = ""
        var hoaLevel = ""
        var bind = ""
        val itemSubclass: String
        val enchant = StringBuilder()
        val setInfo = StringBuilder()
        var socketBonus = ""
        val sockets = StringBuilder()
        val statsString = StringBuilder()
        var azeriteSpells = StringBuilder()
        val sellPrice = StringBuilder()
        try {
            setInfo.append("<font color=#edc201>").append(equippedItem.set.displayString).append("</font><br>")
            setInfo.append(formatSetItemText(equippedItem.set))
        } catch (e: Exception) {
            setInfo.replace(0, setInfo.length, "")
        }
        try {
            sellPrice.append(equippedItem.sellPrice.displayStrings.header).append(" ")
            if (equippedItem.sellPrice.displayStrings.gold != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.gold).append(" <img src=\"gold\">")
            }
            if (equippedItem.sellPrice.displayStrings.silver != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.silver).append(" <img src=\"silver\">")
            }
            if (equippedItem.sellPrice.displayStrings.copper != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.copper).append(" <img src=\"copper\">")
            }
        } catch (e: Exception) {
        }
        try {
            durability = equippedItem.durability.displayString
        } catch (e: Exception) {
        }
        try {
            for (enchantment in equippedItem.enchantments) {
                enchant.append("<font color=#00ff00>").append(enchantment.displayString).append("</font><br>")
            }
        } catch (e: Exception) {
        }
        try {
            itemSubclass = equippedItem.itemSubclass.name
            var length = slot.length + itemSubclass.length
            while (length < 43) {
                slot.append("&nbsp;")
                length++
            }
            var lengthSubClass = itemSubclass.length
            while (lengthSubClass < 14) {
                slot.append("&nbsp;")
                lengthSubClass++
            }
            if (lengthSubClass + length == 60 && itemSubclass == "Miscellaneous") {
                slot = StringBuilder(slot.substring(0, slot.length - 12))
            }
            slot.append(itemSubclass).append("<br>")
        } catch (e: Exception) {
        }
        try {
            transmog = "<font color=#f57bf5>" + equippedItem.transmog.displayString.replace("\n", "<br>") + "</font><br>"
        } catch (e: Exception) {
        }
        try {
            armor = if (equippedItem.armor.displayString == null) {
                "<font color=#" + rbgToHexHTML(equippedItem.armor.display.color) + ">" + equippedItem.armor.display.displayString + "</font><br>"
            } else {
                equippedItem.armor.displayString + "<br>"
            }
        } catch (e: Exception) {
        }
        try {
            requiredLevel = equippedItem.requirements.level.displayString
        } catch (e: Exception) {
        }

        if (equippedItem.description != null) {
            description = "<font color=#edc201>" + equippedItem.description + "</font>"
        }

        try {
            nameDescription = "<font color=#" + getNameDescriptionColor(equippedItem) + ">" + equippedItem.nameDescriptionObject.displayString + "</font><br>"
        } catch (e: Exception) {
        }
        try {
            trigger = if (equippedItem.spells[0].color != null) {
                "<font color=#" + rbgToHexHTML(equippedItem.spells[0].color) + ">" + equippedItem.spells[0].description + "</font>"
            } else {
                "<font color=#00ff00>" + equippedItem.spells[0].description + "</font>"
            }
        } catch (e: Exception) {
        }
        try {
            if (equippedItem.item.id == 158075L) {
                azeriteSpells.append("<br>")
                for (selectedEssence in equippedItem.azeriteDetails.selectedEssences) {
                    val essenceImage = "<img src=\"" + getEssenceImage(selectedEssence.slot) + "\"> "
                    val color = "<font color=#" + getEssenceRankColor(selectedEssence.rank) + ">"
                    azeriteSpells.append(essenceImage).append(color).append(selectedEssence.essence.name).append("</font><br>")
                }
            } else {
                azeriteSpells = StringBuilder("<br><font color=#edc201>" + equippedItem.azeriteDetails.selectedPowersString + "</font><br>")
                for (selectedPower in equippedItem.azeriteDetails.selectedPowers) {
                    if (!selectedPower.isIsDisplayHidden) {
                        azeriteSpells.append("- ").append(selectedPower.spellTooltip.spell.name).append("<br>")
                        azeriteSpells.append("<font color=#00ff00>").append(selectedPower.spellTooltip.description).append("</font><br>")
                    }
                }
            }
        } catch (e: Exception) {
        }
        try {
            for (currentSocket in equippedItem.sockets) {
                if (currentSocket.displayString == null) {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ")
                    sockets.append(currentSocket.socketType?.name).append("<br>")
                } else {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ")
                    sockets.append(currentSocket.displayString).append("<br>")
                }
            }
        } catch (e: Exception) {
        }
        try {
            if (equippedItem.socketBonus != null) {
                socketBonus = "<font color=#00ff00>" + equippedItem.socketBonus + "</font><br>"
            }
        } catch (e: Exception) {
        }
        try {
            bind = equippedItem.binding.name + "<br>"
        } catch (e: Exception) {
        }
        if (equippedItem.item.id == 158075L) {
            hoaLevel = "<font color=#edc201>" + equippedItem.azeriteDetails.level.displayString + "</font><br>"
        }
        if (equippedItem.itemClass.id == 2L) {
            damageInfo = StringBuilder(equippedItem.weapon.damage.displayString)
            var length = damageInfo.length + equippedItem.weapon.attackSpeed.displayString.length
            while (length < 43) {
                damageInfo.append("&nbsp;")
                length++
            }
            damageInfo.append(equippedItem.weapon.attackSpeed.displayString).append("<br>").append(equippedItem.weapon.dps.displayString).append("<br>")
        }
        try {
            for (i in equippedItem.stats.indices) {
                statsString.append("<font color=#" + rbgToHexHTML(equippedItem.stats[i].display.color) + ">").append(equippedItem.stats[i].display.displayString).append("</font>").append("<br>")
            }
        } catch (e: Exception) {
            var i = 0
            while (equippedItem.stats != null && i < equippedItem.stats.size) {
                if (equippedItem.stats[i].isIsEquipBonus) {
                    statsString.append("<font color=#00ff00>").append(equippedItem.stats[i].display_string).append("</font>").append("<br>")
                } else {
                    statsString.append(equippedItem.stats[i].display_string).append("<br>")
                }
                i++
            }
        }
        stats[equippedItem.slot.type] = String.format("%s%s%s%s%s%s%s%s%s", nameDescription, itemLvl, transmog, hoaLevel, bind, slot.toString(), damageInfo.toString(), armor, statsString.toString())
        if (enchant.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", enchant)
        }
        stats[equippedItem.slot.type] = stats[equippedItem.slot.type] + azeriteSpells
        if (trigger != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", trigger)
        }
        if (sockets.length > 4) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", sockets)
        }
        if (socketBonus != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s", socketBonus)
        }
        if (setInfo.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", setInfo)
        }
        if (durability != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", durability)
        }
        if (requiredLevel != "") {
            if (durability == "") {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", requiredLevel)
            } else {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", requiredLevel)
            }
        }
        if (sellPrice.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", sellPrice)
        }
        if (equippedItem.description != null) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", description)
        }
        setOnPressItemInformation(gearImageView[equippedItem.slot.type], index)
    }

    private fun formatSetItemText(set: Set): StringBuilder {
        var equippedSetItem = 0
        val itemRequiredForEffect = IntArray(set.effects.size)
        val setInfo = StringBuilder()
        for (i in set.effects.indices) {
            itemRequiredForEffect[i] = set.effects[i].requiredCount
        }
        for (itemEquipped in set.items) {
            if (itemEquipped.isIsEquipped) {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#ffff98>").append(itemEquipped.item.name).append("</font><br>")
                equippedSetItem++
            } else {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#6e6e6e>").append(itemEquipped.item.name).append("</font><br>")
            }
        }
        setInfo.append("<br>")
        for (i in itemRequiredForEffect.indices) {
            if (itemRequiredForEffect[i] <= equippedSetItem) {
                setInfo.append("<font color=#ffff98>").append(set.effects[i].displayString).append("</font><br>")
            } else {
                setInfo.append("<font color=#6e6e6e>").append(set.effects[i].displayString).append("</font><br>")
            }
        }
        return setInfo
    }

    private fun rbgToHexHTML(color: com.BlizzardArmory.model.warcraft.equipment.Color): String {
        return String.format("#%X", Color.rgb(color.r, color.g, color.b)).substring(3)
    }

    private fun getNameDescriptionColor(equippedItem: EquippedItem): String {
        var red = Integer.toHexString(equippedItem.nameDescriptionObject.color.r)
        var green = Integer.toHexString(equippedItem.nameDescriptionObject.color.g)
        var blue = Integer.toHexString(equippedItem.nameDescriptionObject.color.b)
        if (red == "0") {
            red += "0"
        }
        if (green == "0") {
            green += "0"
        }
        if (blue == "0") {
            blue += "0"
        }
        return red + green + blue
    }

    private fun getSocketColor(currentSocket: Socket): String {
        when (currentSocket.socketType?.type) {
            "PRISMATIC" -> return "socket_prismatic"
            "META" -> return "socket_meta"
            "PUNCHCARDRED" -> return "socket_red"
            "PUNCHCARDYELLOW" -> return "socket_yellow"
            "PUNCHCARDBLUE" -> return "socket_blue"
        }
        return "socket_prismatic"
    }

    private fun getEssenceImage(essenceSlot: Int): String {
        when (essenceSlot) {
            0 -> return "essence_border_gold"
            1, 3 -> return "essence_border_silver"
        }
        return "essence_border_gold"
    }

    private fun getEssenceRankColor(rank: Int): String {
        when (rank) {
            1 -> return "00ff00"
            2 -> return "0070ff"
            3 -> return "663288"
            4 -> return "ff8000"
        }
        return "00ff00"
    }

    private fun setCharacterInformationTextviews() {
        binding.health.text = String.format("Health: %s", statistic.health)
        binding.power.text = String.format("%s: %s", statistic.powerType.name, statistic.power)
        binding.strength.text = String.format("Strength: %s", statistic.strength.effective)
        binding.agility.text = String.format("Agility: %s", statistic.agility.effective)
        binding.intellect.text = String.format("Intellect: %s", statistic.intellect.effective)
        binding.stamina.text = String.format("Stamina: %s", statistic.stamina.effective)
        binding.crit.text = String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", statistic.meleeCrit.value)
        binding.haste.text = String.format(Locale.ENGLISH, "Haste: %.2f%%", statistic.meleeHaste.value.toDouble())
        binding.mastery.text = String.format(Locale.ENGLISH, "Mastery: %.2f%%", statistic.mastery.value.toDouble())
        binding.versatility.text = String.format(Locale.ENGLISH, "Versatility: %.2f%%", statistic.versatilityDamageDoneBonus.toDouble())
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
        if (URLConstants.loading) {
            binding.loadingCircle.visibility = View.GONE
            URLConstants.loading = false

            if(responseCode == 404){
                dialog = DialogPrompt(requireActivity())
                dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                        .addMessage(getErrorMessage(responseCode), 18f, "message")
                        .addSideBySideButtons(errorMessages.OK, 18f, errorMessages.BACK, 18f,
                                {
                                    dialog!!.cancel()
                                },
                                {
                                    dialog!!.cancel()
                                    EventBus.getDefault().post(NetworkEvent(true))
                                },
                                "retry", "back").show()
            }else {
                dialog = DialogPrompt(requireActivity())
                dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                        .addMessage(getErrorMessage(responseCode), 18f, "message")
                        .addSideBySideButtons(errorMessages.RETRY, 18f, errorMessages.BACK, 18f,
                                {
                                    dialog!!.cancel()
                                    downloadInfo()
                                    EventBus.getDefault().post(RetryEvent(true))
                                    binding.loadingCircle.visibility = View.VISIBLE
                                    URLConstants.loading = true
                                },
                                {
                                    dialog!!.cancel()
                                    EventBus.getDefault().post(NetworkEvent(true))
                                },
                                "retry", "back").show()
            }
        }

    }

    companion object {
        var faction: String? = null
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                GamesActivity.hideFavoriteButton()
                if(!URLConstants.loading) {
                    when {
                        activity.supportFragmentManager.findFragmentByTag("wowfragment") != null -> {
                            AccountFragment.addOnBackPressCallback(activity)
                            activity.supportFragmentManager.popBackStack()
                        }
                        activity.supportFragmentManager.findFragmentByTag("wowfavorites") != null -> {
                            WoWFavoritesFragment.addOnBackPressCallback(activity)
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