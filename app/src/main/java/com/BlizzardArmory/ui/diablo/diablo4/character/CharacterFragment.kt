package com.BlizzardArmory.ui.diablo.diablo4.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.D4CharacterFragmentBinding
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CharacterFragment : Fragment() {
    private var dialog: DialogPrompt? = null
    private var battletag = ""
    private var id = ""
    private lateinit var errorMessages: ErrorMessages

    private var _binding: D4CharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: D4CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = D4CharacterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().viewModelStore.clear()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        errorMessages = ErrorMessages(this.resources)
        val bundle = requireArguments()
        id = bundle.getString("id")!!
        battletag = bundle.getString("battletag")!!
        //binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
        viewModel.downloadCharacter(battletag, id)
    }

    private fun setObservers() {
        viewModel.getShowErrorDialog().observe(viewLifecycleOwner) {
            //binding.loadingCircle.visibility = View.GONE
            showNoConnectionMessage(viewModel.errorCode.value!!)
        }

        viewModel.getCharacter().observe(viewLifecycleOwner) {

            //binding.loadingCircle.visibility = View.GONE
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
        //binding.loadingCircle.visibility = View.GONE
        NetworkUtils.loading = false
        if (dialog == null) {
            dialog = DialogPrompt(requireActivity())
            dialog!!.setCancellable(false)
            dialog!!.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addButtons(
                    dialog!!.Button(errorMessages.RETRY, 18f, {
                        dialog!!.dismiss()
                        dialog = null
                        viewModel.downloadCharacter(battletag, id)
                        EventBus.getDefault().post(RetryEvent(true))
                        //binding.loadingCircle.visibility = View.VISIBLE
                    }, "retry"), dialog!!.Button(
                        errorMessages.BACK, 18f,

                        {
                            dialog!!.dismiss()
                            dialog = null
                            EventBus.getDefault().post(NetworkEvent(true))
                            parentFragmentManager.popBackStack(
                                null,
                                FragmentManager.POP_BACK_STACK_INCLUSIVE
                            )
                        }, "back"
                    )
                ).show()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun itemShownReceived(itemShownEvent: D3ItemShownEvent) {
        itemPanelShown = itemShownEvent.data
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun spellShownReceived(spellShownEvent: D3SpellShownEvent) {
        spellPanelShown = spellShownEvent.data
    }

    companion object {
        private var itemPanelShown = false
        private var spellPanelShown = false
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (!itemPanelShown && !spellPanelShown) {
                    activity.supportFragmentManager.popBackStack()
                } else {
                    EventBus.getDefault().post(D3ClosePanelEvent())
                }
            }
        }
    }
}