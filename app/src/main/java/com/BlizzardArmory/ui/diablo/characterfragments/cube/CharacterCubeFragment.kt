package com.BlizzardArmory.ui.diablo.characterfragments.cube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.D3CubeFragmentBinding
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.util.events.WoWCharacterEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class CharacterCubeFragment : Fragment() {


    private var cubeText = ""
    private val cubeMap = HashMap<String, ImageView?>()

    private var characterInformation: CharacterInformation? = null

    private var _binding: D3CubeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterCubeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3CubeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBnetParams().value = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        binding.cubeArmorItem.setImageResource(0)
        binding.cubeSwordItem.setImageResource(0)
        binding.cubeRingItem.setImageResource(0)
        setObservers()
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
        })

        viewModel.getItem().observe(viewLifecycleOwner, { item ->
            cubeMap[item.icon.lowercase(Locale.getDefault())]?.setOnClickListener {
                for (attributes in item.attributes.secondary) {
                    if (attributes.textHtml.contains("d3-color-ffff8000")) {
                        cubeText = "<big>" + item.name + "</big><br>" + attributes.textHtml
                            .replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#")
                            .replace("<span class=\"d3-color-magic\">".toRegex(), "<font color=\"#7979d4\">")
                            .replace("span".toRegex(), "font")
                    }
                }
                binding.cubeText.visibility = View.VISIBLE
                binding.cubeText.text = HtmlCompat.fromHtml(cubeText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        })
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

    private val cubeIcons: Unit
        get() {
            val cubeURL = HashMap<String, String>()
            val armorArray = arrayOf("shoulder", "spirit", "voodoo", "wizardhat", "gloves", "helm", "chest", "cloak", "belt", "pants", "boots", "bracers")
            val ringArray = arrayOf("ring", "amulet")
            if (!characterInformation!!.legendaryPowers.isNullOrEmpty()) {
                for (power in characterInformation!!.legendaryPowers) {
                    when {
                        armorArray.any { s: String? -> power.icon.contains(s!!) } -> {
                            cubeURL["armor"] =
                                NetworkUtils.D3_ICON_ITEMS.replace("icon.png", power.icon + ".png")
                            cubeMap[power.icon.lowercase(Locale.getDefault())] =
                                binding.cubeArmorItem
                        }
                        ringArray.any { s: String? -> power.icon.contains(s!!) } -> {
                            cubeURL["ring"] =
                                NetworkUtils.D3_ICON_ITEMS.replace("icon.png", power.icon + ".png")
                            cubeMap[power.icon.lowercase(Locale.getDefault())] =
                                binding.cubeRingItem
                        }
                        else -> {
                            cubeURL["sword"] =
                                NetworkUtils.D3_ICON_ITEMS.replace("icon.png", power.icon + ".png")
                            cubeMap[power.icon.lowercase(Locale.getDefault())] =
                                binding.cubeSwordItem
                        }
                    }
                }
            }
            for (key in cubeURL.keys) {
                when (key) {
                    "sword" -> {
                        binding.cubeSwordItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeSwordItem)
                    }
                    "armor" -> {
                        binding.cubeArmorItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeArmorItem)
                    }
                    "ring" -> {
                        binding.cubeRingItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeRingItem)
                    }
                }
            }
        }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(woWCharacterEvent: WoWCharacterEvent) {
        characterInformation = woWCharacterEvent.data
        cubeIcons
        viewModel.downloadCubeItems(characterInformation!!)
    }

}