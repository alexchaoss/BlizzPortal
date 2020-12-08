package com.BlizzardArmory.ui.diablo.leaderboard

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.data.common.Player
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.bumptech.glide.Glide


class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_player_list, parent, false)) {

    var btag : TextView? = null
    var icon : ImageView? = null

    init {
        btag = itemView.findViewById(R.id.btag)
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(player: Player, region: String) {
        val playerName = if (player.data.find { it.id == "HeroBattleTag" }?.string == null) {
            "unavailable"
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml("<u>${player.data.find { it.id == "HeroBattleTag" }?.string!!}</u>", Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml("<u>${player.data.find { it.id == "HeroBattleTag" }?.string!!}</u>")
            }
        }

        btag?.text = playerName
        btag?.setTextColor(getColorByClass(player.data.find { it.id == "HeroClass" }?.string))

        Glide.with(context).load(URLConstants.getD3Asset(getGender(player.data.find { it.id == "HeroClass" }?.string!!, player.data.find { it.id == "HeroGender" }?.string!!))).into(icon!!)

        val battlenetOAuth2Params: BattlenetOAuth2Params? = (context as GamesActivity).intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        if (playerName != "unavailable") {
            onClickCharacter(player, region, battlenetOAuth2Params!!)
        }
    }

    private fun getColorByClass(heroClass: String?): Int {
        return when(heroClass){
            "monk" -> Color.parseColor("#cdd410")
            "barbarian" -> Color.parseColor("#e38f35")
            "witch doctor" -> Color.parseColor("#14d156")
            "wizard" -> Color.parseColor("#ea1fcd")
            "necromancer" -> Color.parseColor("#1bb1ac")
            "crusader" -> Color.parseColor("#ffffff")
            "demon hunter" -> Color.parseColor("#d0392a")
            else -> Color.parseColor("#ffffff")
        }
    }

    private fun onClickCharacter(player: Player, region: String, battlenetOAuth2Params: BattlenetOAuth2Params) {
        itemView.setOnClickListener {
            val fragment: Fragment = D3Fragment()
            val bundle = Bundle()
            bundle.putString("battletag", player.data.find { it.id == "HeroBattleTag" }?.string)
            bundle.putString("region", region)
            bundle.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
            fragment.arguments = bundle
            (context as GamesActivity).supportFragmentManager.beginTransaction().add(R.id.fragment, fragment, "d3fragment").addToBackStack("d3fragment").commit()
            context.supportFragmentManager.executePendingTransactions()
        }
    }

    private fun getGender(heroClass: String, gender: String): String {
        var imageName = ""
        when (heroClass) {
            "barbarian" -> if (gender == "m") {
                imageName = "barb_male"
            } else {
                imageName = "barb_female"
            }
            "wizard" -> if (gender == "m") {
                imageName = "wizard_male"
            } else {
                imageName = "wizard_female"
            }
            "demon hunter" -> if (gender == "m") {
                imageName = "dh_male"
            } else {
                imageName = "dh_female"
            }
            "witch doctor" -> if (gender == "m") {
                imageName = "wd_male"
            } else {
                imageName = "wd_female"
            }
            "necromancer" -> if (gender == "m") {
                imageName = "necro_male"
            } else {
                imageName = "necro_female"
            }
            "monk" -> if (gender == "m") {
                imageName = "monk_male"
            } else {
                imageName = "monk_female"
            }
            "crusader" -> if (gender == "m") {
                imageName = "crusader_male"
            } else {
                imageName = "crusader_female"
            }
        }
        return imageName
    }

}
