package com.BlizzardArmory.ui.warcraft.guild.activity


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowGuildActivityBinding
import com.BlizzardArmory.model.warcraft.guild.Guild
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboards.MRaidLeaderboardsFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.state.FragmentTag
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus


class ActivityFragment : Fragment() {

    private var _binding: WowGuildActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivityViewModel by activityViewModels()

    lateinit var errorMessages: ErrorMessages

    private var realm: String? = null
    private var guildName: String? = null
    private var region: String? = null

    private var dialog: DialogPrompt? = null

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().viewModelStore.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowGuildActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
        NetworkUtils.loading = true

        val bundle = requireArguments()
        realm = bundle.getString("realm")
        guildName = bundle.getString("guildName")
        region = bundle.getString("region")

        binding.loadingCircle.visibility = View.VISIBLE

        setObservers()
        viewModel.downloadGuildSummary(realm!!, guildName!!, region!!)
        viewModel.downloadGuildActivity(realm!!, guildName!!, region!!)

    }

    private fun setObservers() {
        viewModel.getGuildSummary().observe(viewLifecycleOwner) {
            binding.name.text = it.name
            binding.realm.text = it.realm.name
            EventBus.getDefault().post(FactionEvent(it.faction.type))
            setBackground(it)
            setCrest(it)
        }

        viewModel.getGuildActivity().observe(viewLifecycleOwner) {
            NetworkUtils.loading = false
            if (!it.activities.isNullOrEmpty()) {
                binding.activityRecycler.apply {
                    adapter = ActivitiesAdapter(it.activities, requireContext())
                }
            }
            binding.loadingCircle.visibility = View.GONE
        }

        viewModel.getGuildCrestBorder().observe(viewLifecycleOwner) {
            Glide.with(this).load(it.assets[0].value).into(binding.crestBorder)
        }

        viewModel.getGuildCrestEmblem().observe(viewLifecycleOwner) {
            Glide.with(this).load(it.assets[0].value).into(binding.crestIcon)
        }

        viewModel.getShowErrorDialog().observe(viewLifecycleOwner) {
            if (dialog == null) {
                callErrorAlertDialog(viewModel.errorCode.value!!)
            }
        }
    }

    private fun setBackground(it: Guild) {
        if (it.faction.type == "ALLIANCE") {
            binding.background.setBackgroundColor(Color.parseColor("#090B13"))
        } else {
            binding.background.setBackgroundColor(Color.parseColor("#1A1511"))
        }
    }

    private fun setCrest(it: Guild) {
        try {
            val bgColor = it.crest.background.color.rgba
            val bg = GradientDrawable()
            bg.colors =
                intArrayOf(
                    Color.rgb(bgColor.r, bgColor.g, bgColor.b),
                    Color.rgb(bgColor.r, bgColor.g, bgColor.b)
                )
            bg.setStroke(5, Color.parseColor("#BBBBBB"))
            binding.crest.background = bg

            binding.crestBorder.setColorFilter(
                Color.rgb(
                    it.crest.border.color.rgba.r,
                    it.crest.border.color.rgba.g,
                    it.crest.border.color.rgba.b
                )
            )
            binding.crestIcon.setColorFilter(
                Color.rgb(
                    it.crest.emblem.color.rgba.r,
                    it.crest.emblem.color.rgba.g,
                    it.crest.emblem.color.rgba.b
                )
            )
        } catch (e: Exception) {
            if (it.faction.type == "ALLIANCE") {
                binding.crestBorder.setImageResource(R.drawable.alliance_logo)
            } else {
                binding.crestBorder.setImageResource(R.drawable.horde_logo)
            }
        }
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            in 400..499 -> {
                errorMessages.GUILD_NOT_FOUND_MESSAGE
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
                errorMessages.GUILD_NOT_FOUND
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
        dialog = DialogPrompt(requireActivity())
        NetworkUtils.loading = false
        if (responseCode == 404) {
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
                        }, "back"
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
                        viewModel.downloadGuildSummary(realm!!, guildName!!, region!!)
                        viewModel.downloadGuildActivity(realm!!, guildName!!, region!!)
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

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {

                when {
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.NAVFRAGMENT.name) != null -> {
                        WoWCharacterFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWRAIDLEADERBOARD.name) != null -> {
                        MRaidLeaderboardsFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    }
                    activity.supportFragmentManager.findFragmentByTag(FragmentTag.WOWGUILDNAVFRAGMENT.name) != null -> {
                        addOnBackPressCallback(activity)
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
