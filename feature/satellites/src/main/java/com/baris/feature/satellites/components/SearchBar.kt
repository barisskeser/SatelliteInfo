package com.baris.feature.satellites.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.baris.feature.satellites.R

/**
 * Created on 31.12.2023.
 * @author Barış Keser
 */

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    onQueryChange: ((String) -> Unit)? = null
) {
    val searchValue = rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        value = searchValue.value,
        onValueChange = {
            searchValue.value = it
            onQueryChange?.invoke(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        placeholder = {
            Text(text = placeHolder)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        shape = CircleShape
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        placeHolder = "Search"
    )
}