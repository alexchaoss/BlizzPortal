package com.BlizzardArmory.ui.warcraft.achievements


import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.databinding.WowAchievementsFragmentBinding
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class AchievementsFragment : Fragment() {

    private var media: String? = null

    private var prefs: SharedPreferences? = null
    private var gson: Gson? = null
    private var faction: String? = null
    private var currentCategory = 0L
    private var subCurrentCategory = -1L
    private var needToUpdate: Boolean = false

    private var _binding: WowAchievementsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AchievementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.character = it.getString(CHARACTER)!!
            viewModel.realm = it.getString(REALM)!!
            media = it.getString(MEDIA)
            viewModel.region = it.getString(REGION)!!
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
        setObservers()
        viewModel.getBnetParams().value = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
        })

        viewModel.getAllAchievements().observe(viewLifecycleOwner, {
            val savedAchievs = Pair(System.currentTimeMillis(), it)
            prefs!!.edit().putString("detailed_achievements_${MainActivity.locale}", gson?.toJson(savedAchievs)).apply()
            needToUpdate = false
            viewModel.downloadCharacterAchievements()
        })

        viewModel.getCharacterAchievements().observe(viewLifecycleOwner, {
            setCategories()
        })

        viewModel.getCategories().observe(viewLifecycleOwner, {
            val savedCategories = Pair(System.currentTimeMillis(), it)
            prefs!!.edit().putString("achievement_categories_${MainActivity.locale}", gson!!.toJson(savedCategories)).apply()
            needToUpdate = false
        })

        viewModel.getMappedAchievements().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                setRecyclerViewToParentCategories()
            }
        })
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
        viewModel.downloadAchievementInformation()
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

    private fun setCategories() {
        /*if (!prefs?.contains("achievement_categories_${MainActivity.locale}")!! || needToUpdate) {*/
        viewModel.downloadCategories()
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

    private fun setRecyclerViewToParentCategories() {
        try {
            binding.categoriesRecycler.apply {
                adapter = CategoriesAdapter(viewModel.getCategories().value!!.filter {
                    it.parentCategoryId == null
                }.sortedBy {
                    it.displayOrder
                }, MainActivity.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getCharacterAchievements().value?.achievements!!)
                adapter!!.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            setAchievementInformation()
        }

        binding.loading.visibility = View.GONE
    }

    private fun setAchievementRecycler(id: Long) {
        Log.i("achiev size", id.toString())
        binding.achievementsRecycler.apply {
            adapter = AchievementsAdapter(
                    viewModel.getMappedAchievements().value!![id]!!.sortedWith(compareByDescending<DetailedAchievement> {
                        viewModel.getCharacterAchievements().value?.achievements?.find { ac -> ac.id == it.id }?.completed_timestamp
                    }.thenBy {
                        it.display_order
                    }), viewModel.getCharacterAchievements().value?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViewToSubCategory(id: Long) {
        binding.categoriesRecycler.apply {
            adapter = CategoriesAdapter(viewModel.getCategories().value!!.filter {
                it.parentCategoryId == id
            }.sortedBy {
                it.displayOrder
            }, MainActivity.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getCharacterAchievements().value?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
        if (viewModel.getMappedAchievements().value!![currentCategory]?.size != 0) {
            binding.achievementsRecycler.apply {
                adapter = AchievementsAdapter(viewModel.getMappedAchievements().value!![currentCategory]!!, viewModel.getCharacterAchievements().value?.achievements!!)
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
