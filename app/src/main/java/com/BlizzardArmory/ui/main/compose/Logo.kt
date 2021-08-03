package com.BlizzardArmory.ui.main

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.BlizzardArmory.R

@Composable
fun LogoCompose(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.bnet_logo),
        contentDescription = "Battle.net Logo",
        modifier = modifier
    )
}