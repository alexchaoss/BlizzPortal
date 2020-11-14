package com.BlizzardArmory.ui.ui_warcraft.account

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.model.warcraft.account.Character
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.MetricConversion
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.android.synthetic.main.wow_account_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AccountFragment : Fragment() {

    private var charaters: Account? = null
    private val characterList = ArrayList<Character>()
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private val charactersByRealm = arrayListOf<List<Character>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback {
            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)
        downloadWoWCharacters()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun downloadWoWCharacters() {
        loadingCircle.visibility = View.VISIBLE
        URLConstants.loading = true
        val call: Call<Account> = RetroClient.getClient.getAccount(MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                when {
                    response.isSuccessful -> {
                        Log.i("TEST", response.raw().request.url.toString())
                        charaters = response.body()
                        populateRecyclerView()
                        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        URLConstants.loading = false
                        loadingCircle.visibility = View.GONE
                    }
                    response.code() >= 400 -> {
                        Log.e("Error", "Response code: " + response.code())
                        callErrorAlertDialog(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e("Error", "trace", t)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun populateRecyclerView() {
        characterList.clear()
        charactersByRealm.clear()
        if (charaters?.wowAccounts != null) {
            for (wowAccount in charaters!!.wowAccounts) {
                characterList.addAll(wowAccount.characters)
            }
        }
        Log.i("TEST", characterList[0].realm.name)
        for (characters in characterList.groupBy { it.realm.name }) {
            charactersByRealm.add(characters.value.sortedBy { it.level.toInt() }.reversed())
        }
        charactersByRealm.sortBy { it[0].realm.name }

        for (realm in charactersByRealm) run {
            val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val button = TextView(requireActivity())
            button.setBackgroundResource(R.drawable.progress_collapse_header)
            button.setTextColor(Color.WHITE)
            val realmNamePlus = "+ " + realm[0].realm.name
            val realmNameMinus = "- " + realm[0].realm.name
            button.text = realmNamePlus
            button.textSize = 18F
            button.layoutParams = paramsButton
            linear_wow_characters.addView(button)

            var expand = false
            val recyclerViewSize = if (realm.size * 65 < 300) {
                realm.size * 64
            } else {
                300
            }
            val paramsRecyclerView: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MetricConversion.getDPMetric(recyclerViewSize, requireActivity()))
            val recyclerView = RecyclerView(requireActivity())
            recyclerView.layoutParams = paramsRecyclerView
            linear_wow_characters.addView(recyclerView)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = battlenetOAuth2Params?.let { AccountAdapter(realm, parentFragmentManager, requireActivity(), it) }
                loadingCircle.visibility = View.GONE
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            recyclerView.visibility = View.GONE

            button.setOnClickListener {
                Log.i("CLICKED", "realm")
                if (!expand) {
                    expand = true
                    recyclerView.visibility = View.VISIBLE
                    button.text = realmNameMinus
                } else {
                    expand = false
                    recyclerView.visibility = View.GONE
                    button.text = realmNamePlus
                }
            }
        }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        URLConstants.loading = false
        if (loadingCircle != null) {
            loadingCircle.visibility = View.GONE
        }
        val builder = AlertDialog.Builder(requireActivity(), R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        val titleText = TextView(requireActivity())
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(requireActivity())
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val button = Button(requireActivity())
        button.textSize = 18f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.layoutParams = buttonParams
        button.background = getDrawable(requireActivity(), R.drawable.buttonstyle)
        val button2 = Button(requireActivity())
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.height = 100
        button2.layoutParams = buttonParams
        button2.background = getDrawable(requireActivity(), R.drawable.buttonstyle)
        val buttonLayout = LinearLayout(requireActivity())
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        when (responseCode) {
            in 400..499 -> {
                titleText.text = ErrorMessages.INFORMATION_OUTDATED
                messageText.text = ErrorMessages.LOGIN_TO_UPDATE
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
            }
            500 -> {
                titleText.text = ErrorMessages.SERVERS_ERROR
                messageText.text = ErrorMessages.BLIZZ_SERVERS_DOWN
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
            }
            else -> {
                titleText.text = ErrorMessages.NO_INTERNET
                messageText.text = ErrorMessages.TURN_ON_CONNECTION_MESSAGE
                button.text = ErrorMessages.RETRY
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
            }
        }
        val dialog = builder.show()
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(requireActivity())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        dialog.setOnCancelListener { downloadWoWCharacters() }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener {
            dialog.dismiss()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }
}