package com.BlizzardArmory.ui.warcraft.account

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowAccountFragmentBinding
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.OauthFlowStarter
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import okhttp3.internal.toImmutableList

class AccountFragment : Fragment() {

    private var prefs: SharedPreferences? = null
    private lateinit var errorMessages: ErrorMessages

    private var _binding: WowAccountFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowAccountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
        addOnBackPressCallback(activity as GamesActivity)
        binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        viewModel.getBnetParams().value =
            activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            viewModel.downloadWoWCharacters()
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            showNoConnectionMessage(it)
        })

        viewModel.getCharacters().observe(viewLifecycleOwner, { account ->
            setAdapter(account.wowAccounts.flatMap { it.characters }.map { it.realm.name }
                .distinct().sorted().toMutableList(), binding.realmSpinner)
            binding.loadingCircle.visibility = View.GONE
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateRecyclerView() {
        binding.characterRecycler.apply {
            adapter = CharacterAdapter(viewModel.getCharacters().value?.wowAccounts!!
                .flatMap { it.characters }
                .filter { it.realm.name == binding.realmSpinner.selectedItem.toString() }
                .sortedByDescending { it.level.toInt() },
                parentFragmentManager, requireActivity(), viewModel.getBnetParams().value!!
            )
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun setAdapter(spinnerList: MutableList<String>, spinner: Spinner) {
        spinnerList.add(0, "Select Realm")
        val arrayAdapter: ArrayAdapter<*> = object :
            ArrayAdapter<String?>(requireActivity(), android.R.layout.simple_dropdown_item_1line, spinnerList.toImmutableList()) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView

                tv.textSize = 18f
                tv.gravity = Gravity.CENTER
                tv.setBackgroundColor(Color.parseColor("#272931"))

                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 18f
                    view.gravity = Gravity.CENTER
                    populateRecyclerView()
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.LOGIN_TO_UPDATE
            }
            503, 403 -> {
                errorMessages.UNEXPECTED
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
            404 -> {
                errorMessages.INFORMATION_OUTDATED
            }
            503, 403 -> {
                errorMessages.UNAVAILABLE
            }
            500 -> {
                errorMessages.SERVERS_ERROR
            }
            else -> {
                errorMessages.NO_INTERNET
            }
        }
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        binding.loadingCircle.visibility = View.GONE
        NetworkUtils.loading = false

        val dialog = DialogPrompt(requireActivity())
        dialog.setCancellable(false)
        if (responseCode == 401) {
            OauthFlowStarter.lastOpenedFragmentNeedingOAuth = this.javaClass.simpleName
            OauthFlowStarter.startOauthFlow(viewModel.getBnetParams().value!!, requireActivity(), View.GONE)
        } else {
            dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addButtons(
                    dialog.Button(errorMessages.RETRY, 18f, {
                        dialog.dismiss()
                        viewModel.downloadWoWCharacters()
                        binding.loadingCircle.visibility = View.VISIBLE
                        NetworkUtils.loading = true
                    }, "retry"), dialog.Button(
                        errorMessages.BACK, 18f,

                        {
                            dialog.dismiss()
                            GamesActivity.hideFavoriteButton()
                            parentFragmentManager.popBackStack()
                            NewsPageFragment.addOnBackPressCallback(activity as GamesActivity)
                        }, "back"
                    )
                ).show()
        }
    }

    companion object {
        fun addOnBackPressCallback(activity: GamesActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (!NetworkUtils.loading) {
                    NewsPageFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack()
                }
            }
        }
    }
}