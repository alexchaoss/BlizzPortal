package com.BlizzardArmory.ui.navigation

import android.app.Application
import android.util.Log
import androidx.annotation.Keep
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class NavigationViewModel(application: Application) : BaseViewModel(application) {

    private var signedIn: MutableLiveData<Boolean> = MutableLiveData()
    private var signInError: MutableLiveData<Boolean> = MutableLiveData()
    private var wowConnectedRealms: MutableLiveData<MutableMap<String, ConnectedRealms>> =
        MutableLiveData()
    private var userInformation: MutableLiveData<UserInformation?> = MutableLiveData()
    private var wowMediaCharacter: MutableLiveData<Media?> = MutableLiveData()
    private var isReady: MutableLiveData<Boolean> = MutableLiveData()

    @Keep
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
        isReady.value = false
        signedIn.value = false
        wowConnectedRealms.value = mutableMapOf()
        initProxy()
    }

    fun observeViewState(): Observable<ViewState> = viewStateSubject

    fun onStartPanelStateChange(panelState: PanelState) {
        val viewState = viewStateSubject.value
        if (viewState != null) {
            viewStateSubject.onNext(viewState.copy(startPanelState = panelState))
        }
    }

    fun onEndPanelStateChange(panelState: PanelState) {
        val viewState = viewStateSubject.value
        if (viewState != null) {
            viewStateSubject.onNext(viewState.copy(endPanelState = panelState))
        }
    }

    fun getIsReady(): LiveData<Boolean?> {
        return isReady
    }

    fun getUserInformation(): LiveData<UserInformation?> {
        return userInformation
    }

    fun setUserInfirmation(userInformation: UserInformation?) {
        this.userInformation.value = userInformation
    }

    fun getMedia(): LiveData<Media?> {
        return wowMediaCharacter
    }

    fun getSignedInStatus(): LiveData<Boolean> {
        return signedIn
    }

    fun getSignInError(): LiveData<Boolean> {
        return signInError
    }

    fun setSignInError(value: Boolean) {
        signInError.value = value
    }

    fun isSignedIn(): Boolean? {
        return signedIn.value
    }

    fun setSignedInStatus(value: Boolean) {
        signedIn.value = value
    }

    fun getWowConnectedRealms(): LiveData<MutableMap<String, ConnectedRealms>> {
        return wowConnectedRealms
    }

    private fun initProxy() {
        var count = 0
        val job = coroutineScope.launch {
            while (isReady.value == false) {
                executeAPICall({ RetroClient.getGeneralClient(getApplication(), true, 0).getRoot() },
                    {
                        val status = it.body()
                        if (status?.status == "Running" && isReady.value == false) {
                            isReady.value = true
                            jobs.filter { job -> job.key.contains("initProxy") }.forEach { job -> job.value.cancel() }
                        } else {
                            count++
                        }
                    })
                delay(1000)
            }
        }
        jobs["initProxy-$count"] = job
    }

    fun downloadUserInfo() {
        executeAPICall({ RetroClient.getGeneralClient(getApplication()).getUserInfo(battlenetOAuth2Helper?.accessToken, NetworkUtils.region) },
            {
                Log.d("USER INFO", it.body().toString())
                userInformation.value = it.body()
            },
            {
                showErrorDialog.value = true
            })
    }

    fun initWoWServer() {
        executeAPICall({ RetroClient.getGeneralClient(getApplication()).initWoWServer(NetworkUtils.API_BASE_URL) }, { Log.d("init wow server", it.message()) })
    }

    fun getConnectedRealms() {
        val query = Query()
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "us") }, { wowConnectedRealms.value?.set("US", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "eu") }, { wowConnectedRealms.value?.set("EU", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "kr") }, { wowConnectedRealms.value?.set("KR", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "tw") }, { wowConnectedRealms.value?.set("TW", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "us", classic = true) }, { wowConnectedRealms.value?.set("US-classic", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "eu", classic = true) }, { wowConnectedRealms.value?.set("EU-classic", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "kr", classic = true) }, { wowConnectedRealms.value?.set("KR-classic", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "tw", classic = true) }, { wowConnectedRealms.value?.set("TW-classic", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "us", classic1x = true) }, { wowConnectedRealms.value?.set("US-classic1x", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "eu", classic1x = true) }, { wowConnectedRealms.value?.set("EU-classic1x", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "kr", classic1x = true) }, { wowConnectedRealms.value?.set("KR-classic1x", it.body()!!) })
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 0).getConnectedRealms(query, "tw", classic1x = true) }, { wowConnectedRealms.value?.set("TW-classic1x", it.body()!!) })
    }

    fun downloadMedia(characterClicked: String, characterRealm: String, selectedRegion: String) {
        executeAPICall({ RetroClient.getWoWClient(getApplication(), cacheTime = 365L).getMedia(
            characterClicked.lowercase(Locale.getDefault()),
            characterRealm.lowercase(Locale.getDefault()),
            selectedRegion.lowercase(Locale.getDefault()),
        ) }, { wowMediaCharacter.value = it.body() }, { wowMediaCharacter.value = null })
    }
}