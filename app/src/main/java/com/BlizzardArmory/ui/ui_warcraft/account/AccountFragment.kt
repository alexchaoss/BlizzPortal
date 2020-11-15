package com.BlizzardArmory.ui.ui_warcraft.account

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.android.synthetic.main.wow_account_fragment.*
import okhttp3.internal.toImmutableList
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AccountFragment : Fragment() {

    private var charaters: Account? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    lateinit var errorMessages: ErrorMessages

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
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
                        setAdapter(charaters?.wowAccounts!!.flatMap { it.characters }.map { it.realm.name }.distinct().sorted().toMutableList(), realmSpinner)
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
        character_recycler.apply {
            adapter = AccountAdapter(charaters?.wowAccounts!!
                    .flatMap { it.characters }
                    .filter { it.realm.name == realmSpinner.selectedItem.toString() }.sortedByDescending { it.level.toInt() },
                    parentFragmentManager, requireActivity(), battlenetOAuth2Params!!)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun setAdapter(spinnerList: MutableList<String>, spinner: Spinner) {
        spinnerList.add(0, "Select Realm")
        val arrayAdapter: ArrayAdapter<*> = object : ArrayAdapter<String?>(requireActivity(), android.R.layout.simple_dropdown_item_1line, spinnerList.toImmutableList()) {
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
                titleText.text = errorMessages.INFORMATION_OUTDATED
                messageText.text = errorMessages.LOGIN_TO_UPDATE
                button2.text = errorMessages.BACK
                buttonLayout.addView(button2)
            }
            500 -> {
                titleText.text = errorMessages.SERVERS_ERROR
                messageText.text = errorMessages.BLIZZ_SERVERS_DOWN
                button2.text = errorMessages.BACK
                buttonLayout.addView(button2)
            }
            else -> {
                titleText.text = errorMessages.NO_INTERNET
                messageText.text = errorMessages.TURN_ON_CONNECTION_MESSAGE
                button.text = errorMessages.RETRY
                button2.text = errorMessages.BACK
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