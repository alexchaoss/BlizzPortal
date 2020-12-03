package com.BlizzardArmory.ui.ui_warcraft.achievements


import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.WowAchievementsFragmentBinding
import com.BlizzardArmory.model.warcraft.achievements.categories.Categories
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievements
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievements
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class CategoriesFragment : Fragment() {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var prefs: SharedPreferences? = null
    private var gson: Gson? = null
    private var faction: String? = null
    private var categories: Categories? = null
    private var characterAchievements: Achievements? = null
    private var allAchievements: DetailedAchievements? = null
    private var mappedAchievements = mutableMapOf<Long, List<DetailedAchievement>?>()
    private var currentCategory = 0L
    private var subCurrentCategory = -1L
    private var needToUpdate: Boolean = false

    private var _binding: WowAchievementsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getString(CHARACTER)
            realm = it.getString(REALM)
            media = it.getString(MEDIA)
            region = it.getString(REGION)
        }
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowAchievementsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gson = GsonBuilder().create()
        prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)


        binding.backArrow.setOnClickListener {
            backArrow()
        }

        binding.subCategoryTab.setOnClickListener {
            binding.categoriesRecycler.visibility = View.VISIBLE
            binding.achievementsRecycler.visibility = View.GONE
        }

        binding.achievementTab.setOnClickListener {
            setAchievementRecycler(currentCategory)
            binding.categoriesRecycler.visibility = View.GONE
            binding.achievementsRecycler.visibility = View.VISIBLE
        }

        binding.downloadRequest.setOnClickListener {
            setAchievementInformation()
        }
    }

    private fun backArrow() {
        if (subCurrentCategory != -1L) {
            binding.achievementTab.visibility = View.VISIBLE
            binding.subCategoryTab.visibility = View.VISIBLE
            binding.separator.visibility = View.VISIBLE
            if (subCurrentCategory == 92L) {
                binding.subCategoryLayout.visibility = View.GONE
            }
            subCurrentCategory = -1L
        } else {
            binding.subCategoryLayout.visibility = View.GONE
            setRecyclerViewToParentCategories()
        }

        binding.categoriesRecycler.visibility = View.VISIBLE
        binding.achievementsRecycler.visibility = View.GONE
    }

    private fun setAchievementInformation() {
        /*if (!prefs?.contains("detailed_achievements_${MainActivity.locale}")!! || needToUpdate) {*/
        downloadAchievementInformation()
        /*} else {
            val saveAchievs =
                    gson!!.fromJson<Pair<Long, DetailedAchievements>>(
                            prefs!!.getString("detailed_achievements_${MainActivity.locale}", "DEFAULT"),
                            object : TypeToken<Pair<Long, DetailedAchievements>>() {}.type)

            if ((System.currentTimeMillis() - saveAchievs.first) > 435000000L) {
                needToUpdate = true
                downloadAchievementInformation()
            } else {
                allAchievements = saveAchievs.second
                downloadCharacterAchievements()
            }
        }*/
    }

    private fun downloadAchievementInformation() {
        val call1: Call<DetailedAchievements> = RetroClient.getClient().getAllAchievements(URLConstants.selectAchievementsURLFromLocale(MainActivity.locale))
        call1.enqueue(object : Callback<DetailedAchievements> {
            override fun onResponse(call: Call<DetailedAchievements>, response: Response<DetailedAchievements>) {
                if (response.isSuccessful) {
                    allAchievements = response.body()
                    val savedAchievs = Pair(System.currentTimeMillis(), allAchievements)
                    prefs!!.edit().putString("detailed_achievements_${MainActivity.locale}", gson?.toJson(savedAchievs)).apply()
                    needToUpdate = false
                    downloadCharacterAchievements()
                }
            }

            override fun onFailure(call: Call<DetailedAchievements>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    private fun downloadCharacterAchievements() {
        val call: Call<Achievements> = RetroClient.getClient().getCharacterAchievements(character, realm, MainActivity.locale, region, battlenetOAuth2Helper?.accessToken)
        call.enqueue(object : Callback<Achievements> {
            override fun onResponse(call: Call<Achievements>, response: Response<Achievements>) {
                characterAchievements = response.body()
                setCategories()
            }

            override fun onFailure(call: Call<Achievements>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    private fun setCategories() {
        /*if (!prefs?.contains("achievement_categories_${MainActivity.locale}")!! || needToUpdate) {*/
        downloadCategories()
        /*} else {
            val savedCategories =
                    gson!!.fromJson<Pair<Long, Categories>>(prefs!!.getString("achievement_categories_${MainActivity.locale}", "DEFAULT"), object : TypeToken<Pair<Long, Categories>>() {}.type)
            if ((System.currentTimeMillis() - savedCategories.first) > 435000000L) {
                needToUpdate = true
                downloadCategories()
            } else {
                categories = savedCategories.second
                createAchievementsMap()
            }
        }*/
    }

    private fun downloadCategories() {
        val call: Call<Categories> = RetroClient.getClient().getAchievementCategories(URLConstants.selectAchievementCategoriesURLFromLocale(MainActivity.locale))
        call.enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful) {
                    categories = response.body()
                    val savedCategories = Pair(System.currentTimeMillis(), categories)
                    prefs!!.edit().putString("achievement_categories_${MainActivity.locale}", gson!!.toJson(savedCategories)).apply()
                    needToUpdate = false
                    createAchievementsMap()
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    private fun createAchievementsMap() {
        GlobalScope.launch(Dispatchers.Main) {
            GlobalScope.launch(Dispatchers.Default) {
                mappedAchievements = categories?.groupBy { it.id }
                        ?.mapValues { map ->
                            allAchievements?.filter { a ->
                                a.category_id == map.key && characterAchievements?.achievements?.any { b -> a.id == b.id }!!
                            }
                        }?.toMutableMap()!!
            }.join()
            setRecyclerViewToParentCategories()
        }
    }

    private fun setRecyclerViewToParentCategories() {
        binding.categoriesRecycler.apply {
            adapter = CategoriesAdapter(categories!!.filter {
                it.parentCategoryId == null
            }.sortedBy {
                it.displayOrder
            }, MainActivity.locale, faction!!, mappedAchievements, characterAchievements?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
        binding.loading.visibility = View.GONE
    }

    private fun setAchievementRecycler(id: Long) {
        Log.i("achiev size", id.toString())
        binding.achievementsRecycler.apply {
            adapter = AchievementsAdapter(
                    mappedAchievements[id]!!.sortedWith(compareByDescending<DetailedAchievement> {
                        characterAchievements?.achievements?.find {
                            ac -> ac.id == it.id }?.completed_timestamp
                    }.thenBy {
                        it.display_order
                    }), characterAchievements?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViewToSubCategory(id: Long) {
        binding.categoriesRecycler.apply {
            adapter = CategoriesAdapter(categories!!.filter {
                it.parentCategoryId == id
            }.sortedBy {
                it.displayOrder
            }, MainActivity.locale, faction!!, mappedAchievements, characterAchievements?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
        if (mappedAchievements[currentCategory]?.size != 0) {
            binding.achievementsRecycler.apply {
                adapter = AchievementsAdapter(mappedAchievements[currentCategory]!!, characterAchievements?.achievements!!)
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun factionEventReceived(factionEvent: FactionEvent) {
        faction = factionEvent.data
        /*if (prefs?.contains("achievement_categories_${MainActivity.locale}")!! && prefs?.contains("detailed_achievements_${MainActivity.locale}")!!) {*/
        binding.loading.visibility = View.VISIBLE
        setAchievementInformation()
        /*} else {
            download_request.visibility = View.VISIBLE
        }*/
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun parentCategoryEventReceived(parentCategoryEvent: ParentCategoryEvent) {
        currentCategory = parentCategoryEvent.data
        setRecyclerViewToSubCategory(currentCategory)
        binding.subCategoryLayout.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun subCategoryEventReceived(subCategoryEvent: SubCategoryEvent) {
        subCurrentCategory = subCategoryEvent.data
        binding.categoriesRecycler.visibility = View.GONE
        binding.achievementsRecycler.visibility = View.VISIBLE
        if (subCategoryEvent.data == 92L) {
            binding.subCategoryLayout.visibility = View.VISIBLE
        }
        binding.achievementTab.visibility = View.GONE
        binding.subCategoryTab.visibility = View.GONE
        binding.separator.visibility = View.GONE
        setAchievementRecycler(subCurrentCategory)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            setAchievementInformation()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                binding.achievLayout.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
        }
        Glide.with(this).load(URLConstants.getWoWAsset(bgName)).into(binding.backgroundAchieves)
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String, region: String) =
                WoWNavFragment().apply {
                    arguments = Bundle().apply {
                        putString(CHARACTER, character)
                        putString(REALM, realm)
                        putString(MEDIA, media)
                        putString(REGION, region)
                    }
                }
    }
}
