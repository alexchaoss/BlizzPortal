package com.BlizzardArmory.ui.ui_warcraft.covenant

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.WowCovenantFragmentBinding
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.SoulbindInformation
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Soulbinds
import com.BlizzardArmory.model.warcraft.covenant.covenant.custom.CovenantSpells
import com.BlizzardArmory.model.warcraft.covenant.techtalenttree.TechTalentTree
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.MetricConversion
import com.BlizzardArmory.util.TempJSON
import com.BlizzardArmory.util.events.ClassEvent
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CovenantFragment : Fragment() {
    private var soulbindInformation: SoulbindInformation? = null
    private var techTalentTree: TechTalentTree? = null

    private var covenantClassSpells: List<CovenantSpells>? = null

    private val gson = GsonBuilder().create()

    private var _binding: WowCovenantFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowCovenantFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val battlenetOAuth2Params: BattlenetOAuth2Params? = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
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

    private fun setAvatars() {
        setAvatar(binding.avatar1, binding.border1, soulbindInformation!!.soulbinds[0])
        setAvatar(binding.avatar2, binding.border2, soulbindInformation!!.soulbinds[1])
        setAvatar(binding.avatar3, binding.border3, soulbindInformation!!.soulbinds[2])

        binding.renowmLevel.text = soulbindInformation!!.renownLevel.toString()

        binding.avatar1.setOnClickListener {
            setGreyShade(binding.avatar1, 1f)
            setGreyShade(binding.avatar2, 0f)
            setGreyShade(binding.avatar3, 0f)
        }

        binding.avatar2.setOnClickListener {
            setGreyShade(binding.avatar2, 1f)
            setGreyShade(binding.avatar1, 0f)
            setGreyShade(binding.avatar3, 0f)
        }

        binding.avatar3.setOnClickListener {
            setGreyShade(binding.avatar3, 1f)
            setGreyShade(binding.avatar1, 0f)
            setGreyShade(binding.avatar2, 0f)
        }
    }

    private fun setAvatar(avatar: ImageView, border: ImageView, soulbinds: Soulbinds){
        when(soulbinds.soulbind.id){
            1 -> avatar.setImageResource(R.drawable.niya)
            2 -> avatar.setImageResource(R.drawable.dreamweaver)
            3 -> avatar.setImageResource(R.drawable.general_draven)
            4 -> avatar.setImageResource(R.drawable.plague_deviser_marileth)
            5 -> avatar.setImageResource(R.drawable.emeni)
            6 -> avatar.setImageResource(R.drawable.korayn)
            7 -> avatar.setImageResource(R.drawable.pelagos)
            8 -> avatar.setImageResource(R.drawable.nadjia_the_mistblade)
            9 -> avatar.setImageResource(R.drawable.theotar_the_mad_duke)
            10 -> avatar.setImageResource(R.drawable.bonesmith_heirmir)
            13 -> avatar.setImageResource(R.drawable.kleia)
            18 -> avatar.setImageResource(R.drawable.forgelite_prime_mikanikos)
        }
        if(soulbinds.active){
            border.setImageResource(R.drawable.soulbinds_portrait_selected)
            val params = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(120, requireContext()), MetricConversion.getDPMetric(120, requireContext()))
            border.layoutParams = params
            binding.conduitRecycler.apply {
                adapter = SoulbindAdapter(soulbinds.traits, techTalentTree?.talents?.sortedBy { it.tier }!!, requireContext())
            }
        }else{
            setGreyShade(avatar, 0f)
        }
    }

    private fun setGreyShade(avatar: ImageView , saturation: Float) {
        val matrix = ColorMatrix()
        matrix.setSaturation(saturation)
        avatar.colorFilter = ColorMatrixColorFilter(matrix)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        Log.i("class", "received")
        downloadCovenantClassSpell(classEvent)
        EventBus.getDefault().unregister(this)
    }

    private fun downloadCovenantClassSpell(classEvent: ClassEvent) {
        val call: Call<List<CovenantSpells>> = GamesActivity.client!!.getCovenantSpells(URLConstants.getCovenantClassSpells(classEvent.data, MainActivity.locale))
        call.enqueue(object : Callback<List<CovenantSpells>> {
            override fun onResponse(call: Call<List<CovenantSpells>>, response: Response<List<CovenantSpells>>) {
                if (response.isSuccessful) {
                    covenantClassSpells = response.body()
                    soulbindInformation = gson.fromJson(TempJSON.string, SoulbindInformation::class.java)
                    techTalentTree = gson.fromJson(TempJSON.soulbind, TechTalentTree::class.java)

                    setAvatars()
                    Glide.with(requireContext()).load(covenantClassSpells?.find { it.covenant_id == soulbindInformation!!.chosenCovenant.id }?.icon).into(binding.covenantClassSpell)
                    setOnSpellTouched(binding.covenantClassSpell, covenantClassSpells?.find { it.covenant_id == soulbindInformation!!.chosenCovenant.id }!!)
                    downloadCovenantSpell(soulbindInformation!!.chosenCovenant.id)
                }
            }

            override fun onFailure(call: Call<List<CovenantSpells>>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    private fun downloadCovenantSpell(covenantId: Int) {
        val call: Call<List<CovenantSpells>> = GamesActivity.client!!.getCovenantSpells(URLConstants.getCovenantSpells(covenantId, MainActivity.locale))
        call.enqueue(object : Callback<List<CovenantSpells>> {
            override fun onResponse(call: Call<List<CovenantSpells>>, response: Response<List<CovenantSpells>>) {
                if (response.isSuccessful) {
                    val covenantSpells = response.body()

                    Glide.with(requireContext()).load(covenantSpells?.get(0)?.icon).into(binding.covenantSpell)
                    setOnSpellTouched(binding.covenantSpell, covenantSpells?.get(0)!!)
                }
            }

            override fun onFailure(call: Call<List<CovenantSpells>>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnSpellTouched(spell: ImageView, covenantSpells: CovenantSpells) {
        spell.setOnTouchListener { view, motionEvent ->
            view.performClick()
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.spellTooltip.visibility = View.VISIBLE
                    if (covenantSpells.powerCost == null || covenantSpells.powerCost == "") {
                        binding.spellCost.visibility = View.GONE
                    } else {
                        binding.spellCost.visibility = View.VISIBLE
                    }
                    binding.spellName.text = covenantSpells.name
                    binding.spellCost.text = covenantSpells.powerCost
                    binding.spellCast.text = covenantSpells.castTime
                    binding.spellCd.text = covenantSpells.cooldown
                    binding.spellDescription.text = covenantSpells.description
                }
                MotionEvent.ACTION_UP -> {
                    binding.spellTooltip.visibility = View.GONE
                }
            }
            return@setOnTouchListener true
        }
    }
}