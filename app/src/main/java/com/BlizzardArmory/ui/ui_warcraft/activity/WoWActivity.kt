package com.BlizzardArmory.ui.ui_warcraft.activity

import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.account.Account
import com.BlizzardArmory.model.warcraft.account.Character
import com.BlizzardArmory.ui.MainActivity
import kotlinx.android.synthetic.main.wow_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class WoWActivity : Fragment() {

    private var charaters: Account? = null
    private val characterList = ArrayList<Character>()
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private val charactersByRealm = arrayListOf<List<Character>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingCircle.visibility = View.VISIBLE
        URLConstants.loading = true

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)
        downloadWoWCharacters()

        /*search.setOnClickListener {
            val fragment = parentFragmentManager.findFragmentById(R.id.fragment)
            WoWCharacterSearchDialog.characterSearchPrompt(requireActivity(), fragment)
        }*/
    }

    private fun downloadWoWCharacters() {
        val call: Call<Account> = RetroClient.getClient.getAccount(MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                when {
                    response.isSuccessful -> {
                        Log.i("TEST", response.raw().request.url.toString())
                        charaters = response.body()
                        populateRecyclerView()
                    }
                    response.code() >= 400 -> {
                        Log.e("Error", "Response code: " + response.code())
                        callErrorAlertDialog(response.code())
                    }
                }

            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun populateRecyclerView() {
        if (charaters?.wowAccounts != null) {
            for (wowAccount in charaters!!.wowAccounts) {
                characterList.addAll(wowAccount.characters)
            }
        }
        Log.i("TEST", characterList.get(0).realm.name)
        for (characters in characterList.groupBy { it.realm.name }) {
            charactersByRealm.add(characters.value.sortedBy { it.level.toInt() }.reversed())
        }
        charactersByRealm.sortBy { it[0].realm.name }

        for (realm in charactersByRealm) run {
            val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val button = TextView(requireActivity())
            button.setBackgroundResource(R.drawable.progress_collapse_header)
            button.setTextColor(Color.WHITE)
            var realmNamePlus = "+ " + realm[0].realm.name
            var realmNameMinus = "- " + realm[0].realm.name
            button.text = realmNamePlus
            button.textSize = 18F
            button.layoutParams = paramsButton
            linear_wow_characters.addView(button)

            var expand = false
            val paramsRecyclerView: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val recyclerView = RecyclerView(requireActivity())
            recyclerView.layoutParams = paramsRecyclerView
            linear_wow_characters.addView(recyclerView)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = bnOAuth2Params?.let { ActivityAdapter(realm, parentFragmentManager, requireActivity(), it) }
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
        dialog.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        requireActivity().window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        requireActivity().window.setGravity(Gravity.CENTER)
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
        }
    }
}