package com.BlizzardArmory.ui.ui_warcraft


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.IOnBackPressed


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"


class AchievementsFragment : Fragment(), IOnBackPressed {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getString(CHARACTER)
            realm = it.getString(REALM)
            media = it.getString(MEDIA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.wow_achiev_fragment, container, false)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String) =
                WoWNavFragment().apply {
                    arguments = Bundle().apply {
                        putString(CHARACTER, character)
                        putString(REALM, realm)
                        putString(MEDIA, media)
                    }
                }
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}
