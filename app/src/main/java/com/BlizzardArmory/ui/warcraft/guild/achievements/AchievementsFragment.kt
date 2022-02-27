package com.BlizzardArmory.ui.warcraft.guild.achievements


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.WowAchievementsFragmentBinding
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.ParentCategoryEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.events.SubCategoryEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class AchievementsFragment : Fragment() {

    private var _binding: WowAchievementsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AchievementViewModel by activityViewModels()

    private var realm: String? = null
    private var guildName: String? = null
    private var region: String? = null

    private var faction: String? = null
    private var currentCategory = 0L
    private var subCurrentCategory = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val bundle = requireArguments()
        realm = bundle.getString("realm")
        guildName = bundle.getString("guildName")
        region = bundle.getString("region")

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

        setObservers()

    }

    private fun setObservers() {
        viewModel.getAllAchievements().observe(viewLifecycleOwner, {
            viewModel.downloadGuildAchivements(realm!!, guildName!!, region!!)
        })

        viewModel.getGuildAchievements().observe(viewLifecycleOwner, {
            setCategories()
        })

        viewModel.getCategories().observe(viewLifecycleOwner, {
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
                }, NetworkUtils.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getGuildAchievements().value?.achievements!!)
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
                    viewModel.getGuildAchievements().value?.achievements?.find { ac -> ac.id == it.id }?.completed_timestamp
                }.thenBy {
                    it.display_order
                }), viewModel.getGuildAchievements().value?.achievements!!
            )
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViewToSubCategory(id: Long) {
        binding.categoriesRecycler.apply {
            adapter = CategoriesAdapter(viewModel.getCategories().value!!.filter {
                it.parentCategoryId == id
            }.sortedBy {
                it.displayOrder
            }, NetworkUtils.locale, faction!!, viewModel.getMappedAchievements().value!!, viewModel.getGuildAchievements().value?.achievements!!)
            adapter!!.notifyDataSetChanged()
        }
        if (viewModel.getMappedAchievements().value!![currentCategory]?.size != 0) {
            binding.achievementsRecycler.apply {
                adapter =
                    AchievementsAdapter(viewModel.getMappedAchievements().value!![currentCategory]!!, viewModel.getGuildAchievements().value?.achievements!!)
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun factionEventReceived(factionEvent: FactionEvent) {
        faction = factionEvent.data
        binding.loading.visibility = View.VISIBLE
        setBackground(faction!!)
        setAchievementInformation()

    }

    private fun setBackground(faction: String) {
        if (faction == "ALLIANCE") {
            binding.achievLayout.setBackgroundColor(Color.parseColor("#090B13"))
        } else {
            binding.achievLayout.setBackgroundColor(Color.parseColor("#1A1511"))
        }
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
}
