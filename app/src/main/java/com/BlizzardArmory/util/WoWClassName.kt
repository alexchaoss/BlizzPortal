package com.BlizzardArmory.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget

object WoWClassName {
    @JvmStatic
    fun get(id: Int): String {
        return when (id) {
            1 -> "WARRIOR"
            2 -> "PALADIN"
            3 -> "HUNTER"
            4 -> "ROGUE"
            5 -> "PRIEST"
            6 -> "DEATH_KNIGHT"
            7 -> "SHAMAN"
            8 -> "MAGE"
            9 -> "WARLOCK"
            10 -> "MONK"
            11 -> "DRUID"
            12 -> "DEMON_HUNTER"
            13 -> "EVOKER"
            else -> ""
        }
    }

    fun setBackground(bg: View, imgBg: ImageView, context: Context, charClass: Int?): ViewTarget<ImageView, Drawable> {
        var bgName = ""
        when (charClass) {
            6 -> {
                bg.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }

            12 -> {
                bg.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }

            11 -> {
                bg.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                bg.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                bg.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                bg.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                bg.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                bg.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                bg.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                bg.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                bg.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                bg.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
            13 -> {
                bg.setBackgroundColor(Color.parseColor("#07060C"))
                bgName = "evoker_bg"
            }
        }
        return Glide.with(context).load(NetworkUtils.getWoWAsset("class/$bgName")).into(imgBg)
    }
}