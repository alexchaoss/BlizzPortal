package com.BlizzardArmory.util.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val blue_gray = Color(0xFF21242F)
private val dark_gray = Color(0xFF21242B)
private val blue_marine = Color(0xFF1B324D)

@Composable
fun DefaultButton(text: String, modifier: Modifier, onClick: (() -> Unit) = { }) {
    val gradient = Brush.horizontalGradient(listOf(blue_gray, dark_gray, blue_gray))
    Button(modifier = modifier.clickable(
        remember { MutableInteractionSource() },
        rememberRipple(bounded = false, color = blue_marine),
        onClick = { onClick() }
    ),
        onClick = { onClick() },
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(3.dp),
        border = BorderStroke(1.dp, blue_marine),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(modifier = Modifier
            .background(gradient)
            .then(modifier), contentAlignment = Alignment.Center) {
            Text(text = text, color = Color.White, fontSize = 23.sp)
        }
        rememberRipple(bounded = true, color = blue_marine)
    }
}
