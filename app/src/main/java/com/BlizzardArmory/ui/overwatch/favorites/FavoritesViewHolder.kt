package com.BlizzardArmory.ui.overwatch.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfile
import com.BlizzardArmory.ui.overwatch.OWFragment
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.FragmentTag
import com.BlizzardArmory.util.events.NetworkEvent
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class FavoritesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.ow_favorite_profile, parent, false)) {

    private var avatar: ImageView? = null
    private var username: TextView? = null
    private var platform: TextView? = null
    private var level: TextView? = null
    private var layout: ConstraintLayout? = null
    private var fragmentManager: FragmentManager? = null
    private var profile: FavoriteProfile? = null
    private var downloaded = false
    private var job: Job? = null

    init {
        avatar = itemView.findViewById(R.id.avatar)
        username = itemView.findViewById(R.id.username)
        platform = itemView.findViewById(R.id.platform)
        level = itemView.findViewById(R.id.level)
        layout = itemView.findViewById(R.id.profile_layout)
        EventBus.getDefault().register(this)
        job = GlobalScope.launch {
            do {
                delay(3000)
                if (!downloaded) {
                    EventBus.getDefault().post(NetworkEvent(ConnectionStatus.hasNetwork()))
                }
            } while (!ConnectionStatus.hasNetwork())
        }
    }

    fun bind(profile: FavoriteProfile, fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
        this.profile = profile
        username?.text = profile.username
        platform?.text = profile.platform?.toUpperCase(Locale.ROOT)
        val levelNumber = profile.profile?.level?.plus(100.times(profile.profile?.prestige!!))
        level?.text = levelNumber.toString()
        downloadAvatar()

        layout?.setOnClickListener {
            val fragment: Fragment = OWFragment()
            val bundle = Bundle()
            bundle.putString("username", profile.username)
            bundle.putString("platform", profile.platform)
            fragment.arguments = bundle
            fragmentManager.beginTransaction()
                .add(R.id.fragment, fragment, FragmentTag.OVERWATCHFRAGMENT.name)
                .addToBackStack("ow_account").commit()
            fragmentManager.executePendingTransactions()
        }
    }

    private fun downloadAvatar() {
        Glide.with(context.applicationContext).load(profile?.profile?.icon).into(avatar!!)
        downloaded = true
        job?.cancel()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            downloadAvatar()
        }
    }

}
