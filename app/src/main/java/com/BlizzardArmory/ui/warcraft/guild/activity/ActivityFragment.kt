package com.BlizzardArmory.ui.warcraft.guild.activity


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowGuildActivityBinding
import com.BlizzardArmory.model.warcraft.guild.Guild
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.bumptech.glide.Glide


class ActivityFragment : Fragment() {

    private var _binding: WowGuildActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivityViewModel by viewModels()

    private var realm: String? = null
    private var guildName: String? = null
    private var region: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowGuildActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()
        realm = bundle.getString("realm")
        guildName = bundle.getString("guildName")
        region = bundle.getString("region")

        binding.loadingCircle.visibility = View.VISIBLE

        setObservers()
        viewModel.getBnetParams().value =
            activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            viewModel.downloadGuildSummary(realm!!, guildName!!, region!!)
            viewModel.downloadGuildActivity(realm!!, guildName!!, region!!)
        })

        viewModel.getGuildSummary().observe(viewLifecycleOwner, {
            binding.name.text = it.name
            binding.realm.text = it.realm.name
            setBackground(it)
            setCrest(it)
        })

        viewModel.getGuildActivity().observe(viewLifecycleOwner, {
            binding.activityRecycler.apply {
                adapter = ActivitiesAdapter(it.activities)
            }
            binding.loadingCircle.visibility = View.GONE
        })

        viewModel.getGuildCrestBorder().observe(viewLifecycleOwner, {
            Glide.with(this).load(it.assets[0].value).into(binding.crestBorder)
        })

        viewModel.getGuildCrestEmblem().observe(viewLifecycleOwner, {
            Glide.with(this).load(it.assets[0].value).into(binding.crestIcon)

        })
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
                intArrayOf(Color.rgb(bgColor.r, bgColor.g, bgColor.b), Color.rgb(bgColor.r, bgColor.g, bgColor.b))
            bg.setStroke(5, Color.parseColor("#BBBBBB"))
            binding.crest.background = bg

            binding.crestBorder.setColorFilter(Color.rgb(it.crest.border.color.rgba.r, it.crest.border.color.rgba.g, it.crest.border.color.rgba.b))
            binding.crestIcon.setColorFilter(Color.rgb(it.crest.emblem.color.rgba.r, it.crest.emblem.color.rgba.g, it.crest.emblem.color.rgba.b))
        } catch (e: Exception) {
            if (it.faction.type == "ALLIANCE") {
                binding.crestBorder.setImageResource(R.drawable.alliance_logo)
            } else {
                binding.crestBorder.setImageResource(R.drawable.horde_logo)
            }
        }
    }
}
