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
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.WowRepFragmentBinding
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.RepByExpansion
import com.BlizzardArmory.model.warcraft.reputations.custom.ReputationPlusParentInfo
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
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

    private val expansionsId =
        listOf(1118L, 980L, 1097L, 1162L, 1245L, 1444L, 1834L, 2104L, 2414L, 2506L, 2569L)

    private var _binding: WowRepFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReputationsViewModel by activityViewModels()

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
        requireActivity().viewModelStore.clear()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WowRepFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charClass = EventBus.getDefault().getStickyEvent(ClassEvent::class.java)?.data
        WoWClassName.setBackground(binding.reputationLayout, binding.backgroundRep, requireContext(), charClass)
        setObservers()
        viewModel.downloadReputationsPlusParentInfo()
    }

    private fun setObservers() {
        viewModel.getReputations().observe(viewLifecycleOwner) {
            val repsWithParentInfo = viewModel.getReputationsWithParentInfo().value?.toMutableList()
            //Add Classic xpac because it was removed from the API data
            val classic = ReputationPlusParentInfo(id = 1118L, name = "Classic", isHeader = true)
            val wotlk = ReputationPlusParentInfo(id = 1097L, name = "Wrath of the Lich King", isHeader = true)
            if (repsWithParentInfo?.contains(classic) == false) {
                repsWithParentInfo.add(classic)
            }
            if (repsWithParentInfo?.contains(wotlk) == false) {
                repsWithParentInfo.add(wotlk)
            }
            val xpacs = repsWithParentInfo!!
                .filter { expansionsId.contains(it.id) }
                .sortedBy { expansionsId.indexOf(it.id) }.map { it.name }
            setAdapter(xpacs.toMutableList(), binding.repSpinner)
            binding.repSpinner.setSelection(xpacs.size)
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            showOutdatedTextView()
        }
    }

    private fun setAdapter(spinnerList: MutableList<String>, spinner: Spinner) {
        spinnerList.add(0, "Select Expansion")
        val arrayAdapter: ArrayAdapter<*> = object :
            ArrayAdapter<String?>(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                spinnerList.toImmutableList()
            ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
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
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
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
            adapter =
                ReputationsAdapter(viewModel.repsByExpac[binding.repSpinner.selectedItemPosition - 1].sortedBy {
                    RepByExpansion.getFaction(it.faction.name)
                }, context)
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
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadReputationsPlusParentInfo()
        }
    }
}
