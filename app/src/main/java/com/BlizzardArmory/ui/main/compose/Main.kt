package com.BlizzardArmory.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.BlizzardArmory.ui.main.compose.Login

private val gray = Color(0xFF272931)

@Preview
@Composable
fun Main() {
    ConstraintLayout(modifier = Modifier
        .background(gray)
        .fillMaxSize()) {
        val (logo, loginLayout) = createRefs()

        LogoCompose(Modifier
            .constrainAs(logo) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
            .padding(top = 75.dp)
            .width(269.dp)
            .height(253.dp))

        Login(Modifier
            .constrainAs(loginLayout) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 32.dp, end = 32.dp, top = 180.dp))

    }
}

