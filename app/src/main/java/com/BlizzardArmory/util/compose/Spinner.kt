package com.BlizzardArmory.util.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Spinner(requestToOpen: Boolean = false, list: List<String>, selectedString: (String) -> Unit, modifier: Modifier) {
    var selectedIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }


    Button(modifier = modifier
        .background(Color.Black),
        shape = RoundedCornerShape(3.dp),
        border = BorderStroke(width = 1.dp, Color.Gray),
        onClick = { expanded = !expanded },
        colors = ButtonDefaults.buttonColors(Color.Transparent)) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Text(text = list[selectedIndex],
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
                    .clickable(onClick = { Color.Gray }), color = Color.White)
            Icon(imageVector = Icons.Outlined.ArrowDropDown,
                "DropDown",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 5.dp),
                tint = Color.White)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.Transparent)
                .padding(start = 32.dp, end = 32.dp, top = 10.dp)
                .background(Color.Black)
                .fillMaxWidth()
                .height(215.dp)
        ) {
            list.forEachIndexed { index, string ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        if (index != 0) {
                            selectedString(string)
                            selectedIndex = index
                            expanded = false
                        }
                    }) {
                    if (index == 0) {
                        Text(text = string, color = Color.Gray, textAlign = TextAlign.Center, fontSize = 23.sp, modifier = Modifier.fillMaxWidth())
                    } else {
                        Text(text = string, color = Color.White, textAlign = TextAlign.Center, fontSize = 23.sp, modifier = Modifier.fillMaxWidth())
                    }

                }
            }
        }
    }

}