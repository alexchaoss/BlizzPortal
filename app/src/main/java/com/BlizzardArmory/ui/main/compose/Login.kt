package com.BlizzardArmory.ui.main.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.BlizzardArmory.R
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.util.compose.DefaultButton
import com.BlizzardArmory.util.compose.Spinner

@Composable
fun Login(modifier: Modifier) {
    Column(modifier = modifier) {
        Spinner(list = stringArrayResource(id = R.array.regions).toList(),
            selectedString = { NetworkUtils.region = it.lowercase() },
            requestToOpen = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(bottom = 20.dp))

        DefaultButton(text = stringResource(R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp))

    }
}