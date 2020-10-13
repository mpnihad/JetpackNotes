package com.nihad.notes.ui.notes.main

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.KeyboardVoice
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.R
import com.nihad.notes.ui.Moon
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.StaggeredRecyclerViewAdapter
import com.nihad.notes.ui.notes.widget.SearchBar
import com.nihad.notes.ui.white
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max


@AndroidEntryPoint
class NotesFragment : Fragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }


    var searchfocused: Boolean = false
    var searchFocusedd by mutableStateOf(searchfocused)

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val windows= activity?.window
        windows?.statusBarColor= android.graphics.Color.parseColor("#FF57314B")

        return ComposeView(context = requireContext()).apply {
            setContent {
                TipcalcTheme {

                    Dashbord()
                }

            }
        }
    }

    @Composable
    fun Dashbord() {

        val noteList: List<String> = listOf(
            "String1angsv",
            "Stringsembcksbvasbvsbv jk  khbakbv2",
            "String2",
            "Strinvsv g2",
            "String2",
            "Stringvvsvsdsvwew 2",
            "vsvs",
            "Strinsdvsg2",
            "String2",
            "Strdsmcvshv hs shcing2",
            "Strinvsv g2",
            "String2",
            "Stringvvsvsdsvwew 2",
            "vsvs",
            "Strinsdvsg2",
            "String2",
            "Strdsmcvshv hs shcing2",
            "vsvs",
            "Strinsdvsg2",
            "String2",
            "Strdsmcvshv hs shcing2",
            "Strinvsv g2",
            "String2",
            "Stringvvsvsdsvwew 2",
            "vsvs",
            "Strinsdvsg2",
            "String2",
            "Strdsmcvshv hs shcing2"
        )
        val bottomnavList: List<VectorAsset> = listOf(
            Icons.Outlined.CheckBox,
            Icons.Outlined.PhotoCamera,
            Icons.Outlined.KeyboardVoice
        )

        Stack(modifier = Modifier.fillMaxSize().background(
            color = TipcalcTheme.colors.bgcolor)
        )
        {

            Column {
                Surface(

                    color = TipcalcTheme.colors.primary,
                    shape = RoundedCornerShape(
                        20.dp
                    ).copy(topLeft = ZeroCornerSize, topRight = ZeroCornerSize),
                    modifier = Modifier.preferredHeight(175.dp).fillMaxWidth()
                ) {
                    val modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)

                    val annotatedString = AnnotatedString.Builder("   Welcome to \n Notes App!")
                        .apply {
                            addStyle(
                                SpanStyle(
                                    fontSize = TextUnit.Companion.Sp(18f),
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.W100
                                ), 0, 13
                            )
                            addStyle(
                                SpanStyle(
                                    fontSize = TextUnit.Companion.Sp(24f),
                                    fontFamily = Moon,
                                    fontWeight = FontWeight.Bold
                                ), 14, 26
                            )

                        }


                    Column {
                        Text(
                            modifier = modifier,
                            text = annotatedString.toAnnotatedString(),
                            color = white
                        )


                        val context = ContextAmbient.current
                        SearchBar(
                            query = TextFieldValue("hello"),
                            onQueryChange = { },
                            searchFocused = searchFocusedd,
                            onSearchFocusChange = { },

                            onClearQuery = { searchFocusedd = false },
                            onSearchClicked = {
                                searchFocusedd = this@NotesFragment.searchFocusedd == false
                            },
                            searching = false
                        )

                    }

                }



                ScrollableColumn(
                    modifier = Modifier.fillMaxSize().padding(bottom = 70.dp).background(
                        color = TipcalcTheme.colors.bgcolor
                    )
                ) {


                    embeddedAndroidViewDemo(noteList)
                }
            }
            Surface(modifier = Modifier.align(alignment = Alignment.BottomStart)) {

                BottomNavigationOnlySelectedLabelComponent(bottomnavList)
            }

        }


    }


    @Composable
    fun BottomNavigationOnlySelectedLabelComponent(notelist: List<VectorAsset>) {

        var selectedIndex by remember { mutableStateOf(0) }
        // BottomNavigation is a component placed at the bottom of the screen that represents primary
        // destinations in your application.

        Surface(
            color = TipcalcTheme.colors.primary, modifier = Modifier.fillMaxWidth().preferredHeight(
                55.dp
            )
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    5.dp
                ),
                modifier = Modifier.fillMaxWidth()
            )
            {
                Button(
                    onClick = {
//                    Navigation.findNavController.navigate(R.id.action_notesFragment_to_addNotes)
                        findNavController().navigate(
                            R.id.addNotes,
                        )
                    },
                    modifier = Modifier.wrapContentWidth().padding(8.dp),
                    backgroundColor = TipcalcTheme.colors.secondary,
                    contentPadding = PaddingValues(
                        5.dp
                    )
                )
                {
                    Icon(asset = Icons.Filled.Add, modifier = Modifier.size(20.dp))


                    Text(
                        text = "Take A Note...", style = MaterialTheme.typography.subtitle1.copy(
                            color = Color.Black, fontSize = TextUnit.Companion.Sp(
                                14f
                            )
                        )
                    )
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 5.dp, bottom = 5.dp)
                            .fillMaxHeight()

                            .preferredWidth(2.dp)
                            .background(color = Color.Black)

                    )
                    Icon(asset = Icons.Filled.ExpandLess, modifier = Modifier.size(20.dp))



                }
                notelist.forEachIndexed { index, icon ->

                    IconButton(onClick = { selectedIndex = index }) {
                        Icon(asset = icon, modifier = Modifier.size(20.dp))

                    }

                }

//                TextButton(onClick = {},modifier = Modifier.wrapContentWidth()) {
//
//                }

            }
        }
//        BottomNavigation(modifier = Modifier.padding(0.dp).align(Alignment.Bottom)) {
//            notelist.forEachIndexed { index, label ->
//                BottomNavigationItem(
//                    icon = {
//                        // Simple composable that allows you to draw an icon on the screen. It
//                        // accepts a vector asset as the icon.
//                        Icon(asset = label)
//                    },
//                    label = {
//                        // Text is a predefined composable that does exactly what you'd expect it to -
//                        // display text on the screen. It allows you to customize its appearance using the
//                        // style property.
//                        Text(text = "CheckBox")
//                    },
//                    selected = selectedIndex == index,
//                    // Update the selected index when the BottomNavigationItem is clicked
//                    onClick = { selectedIndex = index },
//                    // Setting this to false causes the label to be show only for the navigation item
//                    // that is currently selected, like in the BottomNavigationAlwaysShowLabelComponent
//                    // component.
//                    alwaysShowLabels = false
//                )
//            }
//        }
    }

    @Composable
    fun GridLayoutComponent(noteList: List<String>) {


        StaggeredGrid(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp), rows = noteList.size / 2.toInt()
        ) {
            noteList.forEach { note ->

                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.wrapContentHeight()
                ) {

                    Text(
                        note, style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

    }

    @Composable
    private fun StaggeredGrid(
        modifier: Modifier = Modifier,
        rows: Int,
        children: @Composable () -> Unit
    ) {
        Layout(
            children = children,
            modifier = modifier
        ) { measurables, constraints ->
            val rowWidths = IntArray(rows) { 0 } // Keep track of the width of each row
            val rowHeights = IntArray(rows) { 0 } // Keep track of the height of each row

            // Don't constrain child views further, measure them with given constraints
            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints)

                // Track the width and max height of each row
                val row = index % rows
//                rowWidths[row] += placeable.width
                rowWidths[row] += constraints.maxWidth / 2
                rowHeights[row] = max(rowHeights[row], placeable.height)

                placeable
            }

            // Grid's width is the widest row
//            val width = rowWidths.maxOrNull()?.coerceIn(constraints.minWidth, constraints.maxWidth)
//                ?: constraints.minWidth
            val width = constraints.maxWidth / 2
            // Grid's height is the sum of each row
            val height = rowHeights.sum().coerceIn(constraints.minHeight, constraints.maxHeight)

            // y co-ord of each row
            val rowY = IntArray(rows) { 0 }
            for (i in 1 until rows) {
                rowY[i] = rowY[i - 1] + rowHeights[i - 1]
            }
            layout(width, height) {
                // x co-ord we have placed up to, per row
                val rowX = IntArray(rows) { 0 }
                placeables.forEachIndexed { index, placeable ->
                    val row = index % rows
                    placeable.place(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.width
                }
            }
        }
    }

    @Composable
    private fun StaggeredGridColumn(
        modifier: Modifier = Modifier,
        columns: Int,
        children: @Composable () -> Unit
    ) {

        Layout(
            children = children,
            modifier = modifier
        ) { measurables, constraints ->
            val columnWidths = IntArray(columns) { 0 } // Keep track of the width of each row
            val columnHeights = IntArray(columns) { 0 } // Keep track of the height of each row

            // Don't constrain child views further, measure them with given constraints
            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints)

                // Track the width and max height of each row
                val row = index % columns
                columnWidths[row] = constraints.maxWidth / 2
                columnHeights[row] += placeable.height

                placeable
            }


            // Grid's width is the widest row
            val height = columnWidths.maxOrNull()?.coerceIn(
                constraints.minWidth,
                constraints.maxWidth
            )
                ?: constraints.minWidth
            // Grid's height is the sum of each row
            val width = columnHeights.sum().coerceIn(constraints.minHeight, constraints.maxHeight)


            // y co-ord of each row
            val rowY = IntArray(columns) { 0 }
            for (i in 1 until columns) {
                rowY[i] = rowY[i - 1] + columnHeights[i - 1]
            }
            layout(width, height) {
                // x co-ord we have placed up to, per row
                val rowX = IntArray(columns) { 0 }
                placeables.forEachIndexed { index, placeable ->
                    val row = index % columns
                    placeable.place(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.height
                }
            }
        }
    }

    @Composable
    fun embeddedAndroidViewDemo(noteList: List<String>) {


        val staggeredRecyclerViewAdapter =
            StaggeredRecyclerViewAdapter(ContextAmbient.current, noteList)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        val state = remember { mutableStateOf(0) }


        val color=TipcalcTheme.colors.bgcolor.toArgb()
        //widget.Button

        AndroidView(
            modifier = Modifier.padding(8.dp).fillMaxHeight().background(TipcalcTheme.colors.primary),
            viewBlock = { ctx ->
                //Here you can construct your View
                RecyclerView(ctx).apply {
                    id = R.id.staggered
                    setBackgroundColor(color)
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    layoutManager = staggeredGridLayoutManager
                    adapter = staggeredRecyclerViewAdapter


                }
            },
        )

        staggeredRecyclerViewAdapter.notifyDataSetChanged()

//            //widget.TextView
//            AndroidView(viewBlock = { ctx ->
//                //Here you can construct your View
//                TextView(ctx).apply {
//                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
//                }
//            }, update = {
//                it.text = "You have clicked the buttons: " + state.value.toString() + " times"
//            })

    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewDashbord() {
        TipcalcTheme {

            Dashbord()
        }

    }
    @Preview("Home • Dark Theme")
    @Composable
    fun HomeDarkPreview() {

        TipcalcTheme(darkTheme = true) {

            Dashbord()
        }

    }


    @Preview("Search Bar")
    @Composable
    private fun SearchBarPreview() {
        TipcalcTheme {

            SearchBar(
                query = TextFieldValue(""),
                onQueryChange = { },
                searchFocused = false,
                onSearchFocusChange = { },
                onSearchClicked = { },
                onClearQuery = { },
                searching = false,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

