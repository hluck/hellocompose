package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun NonFocusedSearchBar(
    modifier: Modifier = Modifier,
    placeholder:String = "input something"
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(start = 16.dp)
                .size(23.dp)
        )
        Text(
            text = placeholder,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }

}


@Composable
fun LeadingIcon() {
    Icon(
        imageVector = Icons.Outlined.Search,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        modifier = Modifier
            .padding(start = 16.dp)
            .size(23.dp)
    )
}

@Preview
@Composable
fun FocusedSearchBar(
    modifier: Modifier = Modifier,
    placeholder:String = "input something",
    leadingIcon:(@Composable () -> Unit)? = { LeadingIcon() }
) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusRequest = remember { FocusRequester() }
    LaunchedEffect(focusRequest) {
        focusRequest.requestFocus()
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .focusRequester(focusRequest),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    modifier = Modifier.weight(1f)
                        .padding(start = 8.dp)
                ) {
                    if (text.isEmpty()){
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            )
                        )
                    }
                    innerTextField()
                }
                if (text.isNotEmpty()){
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(23.dp)
                            .clickable {
                                text = ""
                            }
                    )
                }

            }
        }
    )


}



