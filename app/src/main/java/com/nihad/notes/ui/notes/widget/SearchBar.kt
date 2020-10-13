package com.nihad.notes.ui.notes.widget

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.CoreTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.white


@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    onSearchClicked: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    TipcalcTheme() {
        Stack(modifier = modifier.fillMaxSize()) {
            Card(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp).wrapContentHeight()
                    .preferredHeight(55.dp).padding(bottom = 16.dp).align(Alignment.BottomStart),
                backgroundColor = Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)


                ) {
                Stack(modifier = modifier.wrapContentHeight()) {

                    if (!searchFocused) {

                        SearchHint(
                            onSearchClicked
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {
                        if (searchFocused) {
                            IconButton(onClick = onClearQuery,modifier = Modifier.padding(start = 16.dp)) {
                                Icon(
                                    asset = Icons.Outlined.ArrowBack,
                                    tint = Color.White
                                )
                            }
                            Spacer(Modifier.preferredWidth(8.dp))
                            SearchBarComponent(query, onSearchFocusChange)
                        }

                        if (false) {
                            CircularProgressIndicator(
                                color = TipcalcTheme.colors.primary,
                                modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .preferredSize(36.dp)
                            )
                        } else {
                            Spacer(Modifier.preferredWidth(IconSize)) // balance arrow icon
                        }
                    }

                }

            }
        }
    }
}

@OptIn(ExperimentalFocus::class)
@Composable
fun SearchBarComponent(query: TextFieldValue, searchFocused: (Boolean) -> Unit) {

    val context = ContextAmbient.current


    Surface(
        color = Color.Transparent, modifier = Modifier.wrapContentHeight(),
        shape = RoundedCornerShape(5.dp)
    ) {

        var textValue by remember { mutableStateOf(query) }

//        TextField(
//            value = textValue,
//            onValueChange = { textValue = it },
//            imeAction = ImeAction.Search,
//            onImeActionPerformed= {imei,action->
//                hideKeyboard(context)
//            },
//            modifier = Modifier.padding(16.dp).fillMaxWidth()
//        )
        CoreTextField(value = textValue,
            modifier = Modifier.padding(end = 16.dp,top = 8.dp,bottom = 8.dp).fillMaxWidth().focusObserver {onfocus->
                searchFocused(onfocus.isFocused)
            },
            imeAction = ImeAction.Search,
            textStyle = TextStyle(color = white),





            onValueChange = {
                textValue = it
            }
        )
    }
}

fun onSearchFocusChange(focused: Boolean) {

}


private val IconSize = 48.dp

@Composable
private fun SearchHint(
    onSearchClicked: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize().padding(start = 32.dp),

    ) {
        Icon(

            asset = Icons.Outlined.Search,
            tint = Color.White
        )
        Spacer(Modifier.preferredWidth(8.dp))
        TextButton(onClick = onSearchClicked,modifier = Modifier.fillMaxWidth()) {

            Text(
                textAlign = TextAlign.Start,
                text = "Search",
                color = Color.White
                ,modifier = Modifier.fillMaxWidth()
            )
        }


    }
}

@Preview
@Composable
fun previewSearchHint()
{
    TipcalcTheme {

        SearchHint(onSearchClicked = {})
    }
}

