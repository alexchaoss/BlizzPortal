package com.BlizzardArmory.ui.news.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.NewsFragmentBinding
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.util.HTMLtoViewsConverter
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class NewsPageFragment : Fragment() {


    private lateinit var binding: NewsFragmentBinding
    private val viewModel: NewsPageViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = requireArguments()
        val url = bundle.getString("url")
        viewModel.downloadNewsPage(url!!)
        viewModel.getNewsPage().observe(viewLifecycleOwner, {
            Glide.with(requireContext()).load(it.imageURL).into(binding.image)
            binding.game.text = it.game
            binding.title.text = it.title
            binding.author.text = it.author
            binding.date.text = it.date
            val htmlConverter = HTMLtoViewsConverter(requireContext())
            htmlConverter.parseHtml(it.body)
            binding.container.addView(htmlConverter.linearLayout)
        })
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        requireActivity().viewModelStore.clear()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.popBackStack()
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                NewsListFragment.addOnBackPressCallback(activity)
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}