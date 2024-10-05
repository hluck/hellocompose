package com.hluck.hellocompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.PanoramaFishEye
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.rounded.AccessAlarm
import androidx.compose.material.icons.rounded.Accessibility
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun PasswordInputField() {
    var password by rememberSaveable { mutableStateOf("") }

    var passwordVisibility by rememberSaveable {
        mutableStateOf(true)
    }
    val icon = if(passwordVisibility){
        Icons.Outlined.RemoveRedEye
    } else {
        Icons.Filled.VisibilityOff
    }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        placeholder = { Text("Password")},
        trailingIcon = {
            IconButton({ passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Password"
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Password,
                contentDescription = "leading Icon"
            )
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}