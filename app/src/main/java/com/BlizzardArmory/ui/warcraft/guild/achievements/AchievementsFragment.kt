package com.BlizzardArmory.ui.warcraft.guild.achievements


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.BlizzardArmory.databinding.WowGuildActivityBinding
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper


class AchievementsFragment : Fragment() {

    private var _binding: WowGuildActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AchievementViewModel by viewModels()

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

        setObservers()
        viewModel.getBnetParams().value =
            activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
        })
    }
}
