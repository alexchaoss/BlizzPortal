package com.BlizzardArmory.ui.navigation.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class Home {
    @Preview
    @Composable
    fun RootView ( darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
        val colors = if (darkTheme) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
        MaterialTheme(colors = colors, content = content)
    }
}