package com.BlizzardArmory.ui.warcraft.character.covenant

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.covenant.ConduitSocketType
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Traits
import com.BlizzardArmory.model.warcraft.covenant.conduit.Conduit
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.util.events.TechTalentClickedEvent
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus

class SoulbindViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_conduit_row, parent, false)) {

    private var node1: ConstraintLayout? = null
    private var node2: ConstraintLayout? = null
    private var node3: ConstraintLayout? = null

    private var ring1: ImageView? = null
    private var ring2: ImageView? = null
    private var ring3: ImageView? = null

    private var icon1: ImageView? = null
    private var icon2: ImageView? = null
    private var icon3: ImageView? = null

    private var bg1: ImageView? = null
    private var bg2: ImageView? = null
    private var bg3: ImageView? = null

    init {
        node1 = itemView.findViewById(R.id.node1)
        node2 = itemView.findViewById(R.id.node2)
        node3 = itemView.findViewById(R.id.node3)

        ring1 = node1!!.findViewById(R.id.ring)
        ring2 = node2!!.findViewById(R.id.ring)
        ring3 = node3!!.findViewById(R.id.ring)

        icon1 = node1!!.findViewById(R.id.bottom_icon)
        icon2 = node2!!.findViewById(R.id.bottom_icon)
        icon3 = node3!!.findViewById(R.id.bottom_icon)

        bg1 = node1!!.findViewById(R.id.bg)
        bg2 = node2!!.findViewById(R.id.bg)
        bg3 = node3!!.findViewById(R.id.bg)
    }

    fun bind(talents: List<TechTalent>, trait: Traits?) {
        setConduits(talents)
        if (talents[0].socketType != null && trait != null && trait.conduitSocket.socket != null) {
            downloadConduit(trait.conduitSocket.socket.conduit.id, trait.displayOrder, trait.conduitSocket.socket.rank)
        }
        setIcons(talents, trait)
        for (talent in talents) {
            when (talent.display_order) {
                0 -> setOnTalenttouchAction(talent, node1!!)
                1 -> setOnTalenttouchAction(talent, node2!!)
                2 -> setOnTalenttouchAction(talent, node3!!)
            }
        }
    }

    private fun setOnTalenttouchAction(talent: TechTalent, node: ConstraintLayout) {
        if (talent.socketType == null) {
            node?.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        EventBus.getDefault()
                            .post(TechTalentClickedEvent(talent.spell_tooltip.description, talent.name, talent.spell_tooltip.cast_time, true))
                    }
                    MotionEvent.ACTION_UP -> {
                        EventBus.getDefault()
                            .post(TechTalentClickedEvent(talent.spell_tooltip.description, talent.name, talent.spell_tooltip.cast_time, false))
                    }
                }
                return@setOnTouchListener true
            }
        }
    }

    private fun setConduits(talents: List<TechTalent>) {
        when (talents.size) {
            1 -> {
                node1?.visibility = View.INVISIBLE
                node3?.visibility = View.INVISIBLE
                if (talents[0].socketType != null) {
                    when (talents[0].socketType.type) {
                        ConduitSocketType.ENDURANCE.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                        ConduitSocketType.FINESSE.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                        ConduitSocketType.POTENCY.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                    }
                }
            }
            2 -> {
                node2?.visibility = View.INVISIBLE
                if (talents[0].socketType != null) {
                    talents.forEachIndexed { index, talent ->
                        if (index == 0) {
                            when (talents[index].socketType.type) {
                                ConduitSocketType.ENDURANCE.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                                ConduitSocketType.FINESSE.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                                ConduitSocketType.POTENCY.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                            }
                        } else {
                            when (talents[index].socketType.type) {
                                ConduitSocketType.ENDURANCE.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                                ConduitSocketType.FINESSE.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                                ConduitSocketType.POTENCY.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                            }
                        }
                    }
                }

            }
            else -> {
                if (talents[0].socketType != null) {
                    talents.forEachIndexed { index, talent ->
                        when (index) {
                            0 -> {
                                when (talents[index].socketType.type) {
                                    ConduitSocketType.ENDURANCE.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                                    ConduitSocketType.FINESSE.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                                    ConduitSocketType.POTENCY.type -> icon1?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                                }
                            }
                            1 -> {
                                when (talents[index].socketType.type) {
                                    ConduitSocketType.ENDURANCE.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                                    ConduitSocketType.FINESSE.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                                    ConduitSocketType.POTENCY.type -> icon2?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                                }
                            }
                            else -> {
                                when (talents[index].socketType.type) {
                                    ConduitSocketType.ENDURANCE.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_protect)
                                    ConduitSocketType.FINESSE.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_utility)
                                    ConduitSocketType.POTENCY.type -> icon3?.setImageResource(R.drawable.soulbinds_tree_conduit_icon_attack)
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private fun downloadConduit(conduitId: Int, displayOrder: Int, rank: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context, true)
                .getConduit(conduitId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val conduit = response.body()!!
                    when (displayOrder) {
                        0 -> setOnConduitTouchAction(conduit, rank, node1!!)
                        1 -> setOnConduitTouchAction(conduit, rank, node2!!)
                        2 -> setOnConduitTouchAction(conduit, rank, node3!!)
                    }
                    downloadConduitMedia(conduit.ranks[rank - 1].spell_tooltip.spell.id, displayOrder)
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
    }

    private fun setOnConduitTouchAction(conduit: Conduit, rank: Int, node: ConstraintLayout) {
        node?.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    EventBus.getDefault()
                        .post(TechTalentClickedEvent(conduit.ranks[rank - 1].spell_tooltip.description, conduit.ranks[rank - 1].spell_tooltip.spell.name, conduit.ranks[rank - 1].spell_tooltip.cast_time, true))
                }
                MotionEvent.ACTION_UP -> {
                    EventBus.getDefault()
                        .post(TechTalentClickedEvent(conduit.ranks[rank - 1].spell_tooltip.description, conduit.ranks[rank - 1].spell_tooltip.spell.name, conduit.ranks[rank - 1].spell_tooltip.cast_time, false))
                }
            }
            return@setOnTouchListener true
        }
    }

    private fun setIcons(talents: List<TechTalent>, trait: Traits?) {
        if (talents.any { it.socketType != null }) {
            ring1?.setImageResource(R.drawable.soulbinds_tree_conduit_ring_disabled)
            ring2?.setImageResource(R.drawable.soulbinds_tree_conduit_ring_disabled)
            ring3?.setImageResource(R.drawable.soulbinds_tree_conduit_ring_disabled)
            when (trait?.displayOrder) {
                0 -> ring1?.setImageResource(R.drawable.soulbinds_tree_conduit_ring)
                1 -> ring2?.setImageResource(R.drawable.soulbinds_tree_conduit_ring)
                2 -> ring3?.setImageResource(R.drawable.soulbinds_tree_conduit_ring)
            }


        } else {
            ring1?.setImageResource(R.drawable.soulbinds_tree_ring_disabled)
            ring2?.setImageResource(R.drawable.soulbinds_tree_ring_disabled)
            ring3?.setImageResource(R.drawable.soulbinds_tree_ring_disabled)
            icon1?.visibility = View.INVISIBLE
            icon2?.visibility = View.INVISIBLE
            icon3?.visibility = View.INVISIBLE
            when (trait?.displayOrder) {
                0 -> {
                    ring1?.setImageResource(R.drawable.soulbinds_tree_ring)
                }
                1 -> {
                    ring2?.setImageResource(R.drawable.soulbinds_tree_ring)
                }
                2 -> {
                    ring3?.setImageResource(R.drawable.soulbinds_tree_ring)
                }
            }

            for (talent in talents) {
                downloadMedia(talent)
            }
        }
    }

    private fun downloadMedia(talent: TechTalent) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context, true)
                .getTechTalentMedia(talent.id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val media = response.body()!!
                    when (talent.display_order) {
                        0 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg1!!)
                        1 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg2!!)
                        2 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg3!!)
                    }

                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
    }

    private fun downloadConduitMedia(spellId: Int, displayOrder: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context, true)
                .getSpellMedia(spellId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val media = response.body()!!
                    when (displayOrder) {
                        0 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg1!!)
                        1 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg2!!)
                        2 -> Glide.with(itemView).load(media.assets?.get(0)?.value).circleCrop()
                            .into(bg3!!)
                    }

                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
    }
}