package com.BlizzardArmory.ui.warcraft.character.achievements


import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.databinding.WowAchievementsFragmentBinding
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.ParentCategoryEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.events.SubCategoryEvent
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
    private val viewModel: AchievementViewModel by activityViewModels()

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
        requireActivity().viewModelStore.clear()
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
        val charClass = EventBus.getDefault().getStickyEvent(ClassEvent::class.java)?.data
        faction = EventBus.getDefault().getStickyEvent(FactionEvent::class.java)?.data
        WoWClassName.setBackground(binding.achievLayout, binding.backgroundAchieves, requireContext(), charClass)
        binding.backArrow.setOnClickListener {
            backArrow()
        }

        binding.subCategoryTab.setOnClickListener {
            binding.achievementTab.setTextColor(Color.parseColor("#555555"))
            binding.achievementTab.setBackgroundColor(Color.parseColor("#10ffffff"))
            binding.subCategoryTab.setTextColor(Color.parseColor("#ffffff"))
            binding.subCategoryTab.setBackgroundColor(Color.parseColor("#000000"))
            binding.categoriesRecycler.visibility = View.VISIBLE
            binding.achievementsRecycler.visibility = View.GONE
        }

        binding.achievementTab.setOnClickListener {
            binding.subCategoryTab.setTextColor(Color.parseColor("#555555"))
            binding.subCategoryTab.setBackgroundColor(Color.parseColor("#10ffffff"))
            binding.achievementTab.setTextColor(Color.parseColor("#ffffff"))
            binding.achievementTab.setBackgroundColor(Color.parseColor("#000000"))
            setAchievementRecycler(currentCategory)
            binding.categoriesRecycler.visibility = View.GONE
            binding.achievementsRecycler.visibility = View.VISIBLE
        }

        binding.downloadRequest.setOnClickListener {
            setAchievementInformation()
        }
        setObservers()
        setAchievementInformation()
    }

    private fun setObservers() {
        viewModel.getAllAchievements().observe(viewLifecycleOwner) {
            val savedAchievs = Pair(System.currentTimeMillis(), it)
            prefs!!.edit()
                .putString(
                    "detailed_achievements_${NetworkUtils.locale}",
                    gson?.toJson(savedAchievs)
                )
                .apply()
            needToUpdate = false
            viewModel.downloadCharacterAchievements()
        }

        viewModel.getCharacterAchievements().observe(viewLifecycleOwner) {
            setCategories()
        }

        viewModel.getCategories().observe(viewLifecycleOwner) {
            val savedCategories = Pair(System.currentTimeMillis(), it)
            prefs!!.edit()
                .putString(
                    "achievement_categories_${NetworkUtils.locale}",
                    gson!!.toJson(savedCategories)
                )
                .apply()
            needToUpdate = false
        }

        viewModel.getMappedAchievements().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                setRecyclerViewToParentCategories()
            }
        }
    }

    private fun backArrow() {
        if (subCurrentCategory != -1L) {
            binding.achievementTab.visibility = View.VISIBLE
            binding.subCategoryTab.visibility = View.VISIBLE
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
        viewModel.downloadAchievementInformation()
    }

    private fun setCategories() {
        viewModel.downloadCategories()
    }

    private fun setRecyclerViewToParentCategories() {
        try {
            binding.categoriesRecycler.apply {
                adapter = CategoriesAdapter(viewModel.getCategories().value!!.filter {
                    it.parentCategoryId == null
                }.sortedBy {
                    it.displayOrder
                }, NetworkUtils.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getCharacterAchievements().value?.achievements!!)
                adapter!!.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            setAchievementInformation()
        }

        binding.loading.visibility = View.GONE
    }

    private fun setAchievementRecycler(id: Long) {
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
            }, NetworkUtils.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getCharacterAchievements().value?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
        if (viewModel.getMappedAchievements().value!![currentCategory]?.size != 0) {
            binding.achievementsRecycler.apply {
                adapter = AchievementsAdapter(viewModel.getMappedAchievements().value!![currentCategory]!!, viewModel.getCharacterAchievements().value?.achievements!!)
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun parentCategoryEventReceived(parentCategoryEvent: ParentCategoryEvent) {
        currentCategory = parentCategoryEvent.data
        setRecyclerViewToSubCategory(currentCategory)
        binding.subCategoryLayout.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun subCategoryEventReceived(subCategoryEvent: SubCategoryEvent) {
        subCurrentCategory = subCategoryEvent.data
        binding.categoriesRecycler.visibility = View.GONE
        binding.achievementsRecycler.visibility = View.VISIBLE
        if (subCategoryEvent.data == 92L) {
            binding.subCategoryLayout.visibility = View.VISIBLE
        }
        binding.achievementTab.visibility = View.INVISIBLE
        binding.subCategoryTab.visibility = View.INVISIBLE
        setAchievementRecycler(subCurrentCategory)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            setAchievementInformation()
        }
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
