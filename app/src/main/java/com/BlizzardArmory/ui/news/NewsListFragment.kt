package com.BlizzardArmory.ui.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.databinding.NewsListFragmentBinding
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.util.events.FilterNewsEvent
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NewsListFragment : Fragment() {


    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding: NewsListFragmentBinding
    private val parentJob = SupervisorJob()
    private val coroutineScrope = CoroutineScope(parentJob + Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        binding = NewsListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        setSwipeGestureToRefreshData()
        viewModel.getDownloaded().value = false
        setBackToTopButton()
    }

    private fun setObservers() {
        viewModel.getDownloaded().observe(viewLifecycleOwner, {
            if (it) {
                viewModel.filterList()
                viewModel.setupRecycler()
            } else {
                coroutineScrope.launch {
                    viewModel.downloadNews()
                    binding.swipe.isRefreshing = false
                }
            }
        })
        viewModel.getNewsList().observe(viewLifecycleOwner, { list ->
            Log.i("setup recycler", "test")
            binding.newsRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = NewsAdapter(list, context)
                adapter!!.notifyDataSetChanged()
            }
        })
    }

    private fun setBackToTopButton() {
        binding.newsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 100) {
                    binding.backToTop.visibility = View.VISIBLE
                } else if (dy == 0) {
                    binding.backToTop.visibility = View.GONE
                }
            }
        })

        binding.backToTop.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                GlobalScope.launch(Dispatchers.Main) {
                    binding.newsRecycler.layoutManager?.smoothScrollToPosition(binding.newsRecycler, RecyclerView.State(), 0)
                    delay((binding.newsRecycler.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() * 100L)
                }.join()
                binding.newsRecycler.scrollToPosition(0)
            }
            binding.backToTop.visibility = View.GONE
        }
    }

    private fun setSwipeGestureToRefreshData() {
        binding.swipe.setOnRefreshListener {
            viewModel.getDownloaded().value = false
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }



    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(filterNewsEvent: FilterNewsEvent) {
        val recyclerViewState = binding.newsRecycler.layoutManager?.onSaveInstanceState()
        viewModel.filterList()
        viewModel.setupRecycler()
        binding.newsRecycler.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun refreshNewsReceived(localeSelectedEvent: LocaleSelectedEvent) {
        Log.i("REFRESH", "The news have been refreshed")
        viewModel.getDownloaded().value = false
    }

    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                val intent = Intent(activity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(intent)
            }
        }
    }
}