package com.BlizzardArmory.ui.warcraft.character.reputations


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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowRepFragmentBinding
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.RepByExpansion
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import okhttp3.internal.toImmutableList
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class ReputationsFragment : Fragment() {

    private var media: String? = null

    private val expansionsId = listOf(1118L, 980L, 1097L, 1162L, 1245L, 1444L, 1834L, 2104L, 2414L)

    private var _binding: WowRepFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReputationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.character = it.getString(CHARACTER)!!
            viewModel.realm = it.getString(REALM)!!
            media = it.getString(MEDIA)
            viewModel.region = it.getString(REGION)!!
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = WowRepFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.downloadReputationsPlusParentInfo()
    }

    private fun setObservers() {
        viewModel.getReputations().observe(viewLifecycleOwner, {
            val xpacs = viewModel.getReputationsWithParentInfo().value!!.filter { expansionsId.contains(it.id) }
                .sortedBy { expansionsId.indexOf(it.id) }.map { it.name }
            setAdapter(xpacs.toMutableList(), binding.repSpinner)
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            showOutdatedTextView()
        })
    }

    private fun setAdapter(spinnerList: MutableList<String>, spinner: Spinner) {
        spinnerList.add(0, "Select Expansion")
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
                tv.setBackgroundColor(Color.parseColor("#000000"))

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

    private fun populateRecyclerView() {
        binding.repRecycler.apply {
            adapter = ReputationsAdapter(viewModel.repsByExpac[binding.repSpinner.selectedItemPosition - 1].sortedBy { RepByExpansion.getFaction(it.faction.name) }, context)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun showOutdatedTextView() {
        binding.outdated.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String, region: String) =
            WoWNavFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER, character)
                    putString(REALM, realm)
                    putString(MEDIA, media)
                    putString(REGION, region)
                }
            }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadReputationsPlusParentInfo()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                binding.reputationLayout.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
        }
        Glide.with(this).load(NetworkUtils.getWoWAsset(bgName)).into(binding.backgroundRep)
        EventBus.getDefault().unregister(this)
    }
}
