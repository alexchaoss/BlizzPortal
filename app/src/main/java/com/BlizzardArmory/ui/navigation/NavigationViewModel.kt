package com.BlizzardArmory.ui.navigation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.UserInformation
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.realm.connected.ConnectedRealms
import com.BlizzardArmory.model.warcraft.realm.connected.Query
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.discord.panels.PanelState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NavigationViewModel(application: Application) : BaseViewModel(application) {

    private var wowConnectedRealms: MutableLiveData<MutableMap<String, ConnectedRealms>> = MutableLiveData()
    private var userInformation: MutableLiveData<UserInformation> = MutableLiveData()
    private var wowMediaCharacter: MutableLiveData<Media> = MutableLiveData()

    data class ViewState(
        val startPanelState: PanelState,
        val endPanelState: PanelState
    )

    private val viewStateSubject: BehaviorSubject<ViewState> =
        BehaviorSubject.createDefault(
            ViewState(
                startPanelState = PanelState.Closed,
                endPanelState = PanelState.Closed
            )
        )

    init {
        wowConnectedRealms.value = mutableMapOf()
    }

    fun observeViewState(): Observable<ViewState> = viewStateSubject

    fun onStartPanelStateChange(panelState: PanelState) {
        val viewState = viewStateSubject.value
        viewStateSubject.onNext(viewState.copy(startPanelState = panelState))
    }

    fun onEndPanelStateChange(panelState: PanelState) {
        val viewState = viewStateSubject.value
        viewStateSubject.onNext(viewState.copy(endPanelState = panelState))
    }

    fun getUserInformation(): LiveData<UserInformation> {
        return userInformation
    }

    fun getMedia(): LiveData<Media> {
        return wowMediaCharacter
    }


    fun getWowConnectedRealms(): LiveData<MutableMap<String, ConnectedRealms>> {
        return wowConnectedRealms
    }

    fun downloadUserInfo() {
        var downloadCount = 0
        val job = coroutineScope.launch {
            val response = RetroClient.getGeneralClient(getApplication()).getUserInfo(
                battlenetOAuth2Helper?.accessToken,
                NetworkUtils.region
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userInformation.value = response.body()
                } else {
                    downloadCount++
                    if (downloadCount <= 5) {
                        downloadUserInfo()
                    }
                }
            }
        }
        jobs.add(job)
    }

    fun initWoWServer() {
        val job = coroutineScope.launch {
            val response = RetroClient.getGeneralClient(getApplication())
                .initWoWServer(NetworkUtils.WOW_SERVER)
            withContext(Dispatchers.Main) {
                Log.i("init wow server", response.message())
            }
        }
        jobs.add(job)
    }

    fun getConnectedRealms() {
        val query = Query()
        val jobUS = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getConnectedRealms(
                "dynamic-us",
                query,
                "us"
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    wowConnectedRealms.value?.set("US", response.body()!!)
                } else {
                    Log.e("Error", response.message())
                }
            }
        }
        jobs.add(jobUS)
        val jobEU = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getConnectedRealms(
                "dynamic-eu",
                query,
                "eu",
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    wowConnectedRealms.value?.set("EU", response.body()!!)
                } else {
                    Log.e("Error", response.message())
                }
            }
        }
        jobs.add(jobEU)
        val jobKR = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getConnectedRealms(
                "dynamic-kr",
                query,
                "kr"
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    wowConnectedRealms.value?.set("KR", response.body()!!)
                } else {
                    Log.e("Error", response.message())
                }
            }
        }
        jobs.add(jobKR)
        val jobTW = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getConnectedRealms(
                "dynamic-tw",
                query,
                "tw"

            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    wowConnectedRealms.value?.set("TW", response.body()!!)
                } else {
                    Log.e("Error", response.message())
                }
            }
        }
        jobs.add(jobTW)
    }

    fun downloadMedia(characterClicked: String, characterRealm: String, selectedRegion: String) {
        val job = coroutineScope.launch {
            val response = RetroClient.getWoWClient(getApplication()).getMedia(
                characterClicked.lowercase(Locale.getDefault()), characterRealm.lowercase(Locale.getDefault()),
                battlenetOAuth2Helper!!.accessToken, selectedRegion.lowercase(Locale.getDefault()),
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    wowMediaCharacter.value = response.body()
                } else {
                    Log.e("Error", response.message())
                    wowMediaCharacter.value = null
                }
            }
        }
        jobs.add(job)
    }
}