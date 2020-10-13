package com.nihad.notes.ui.notes.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.Transition
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Position
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.model.MySpinnerItem
import com.nihad.notes.ui.roomdb.model.NotesCategory
import com.nihad.notes.ui.roomdb.model.NotesDB
import com.nihad.notes.ui.splashscreen.offsetGradientBackground
import dagger.hilt.android.AndroidEntryPoint
import java.time.OffsetDateTime

@AndroidEntryPoint
class AddNotesFragment : Fragment() {

    companion object {
        fun newInstance() = AddNotesFragment()
    }

    enum class ROTATIONSTATE {
        IDLE, PRESSED
    }

    enum class ColorAnimation {
        INITIAL, FINAL
    }

    val rotation = FloatPropKey()
    val color = FloatPropKey()
    val opacity = FloatPropKey()
    val width = DpPropKey()
    val cancelColor = ColorPropKey()
    val colorProp1 = ColorPropKey()
    val colorProp2 = ColorPropKey()
    private lateinit var viewModel: AddNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val windows = activity?.window
        windows?.statusBarColor = android.graphics.Color.parseColor("#FFEEEEF2")



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View? = activity?.getWindow()?.getDecorView()

            decor?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        }

        return ComposeView(context = requireContext()).apply {
            setContent {
                TipcalcTheme {

                    AddNotes()
                }

            }
        }
    }

    @Composable
    private fun AddNotes() {


        Stack(modifier = Modifier.fillMaxSize()) {


            var titlemutable by remember { mutableStateOf(TextFieldValue("")) }
            val hintStatus= remember { mutableStateOf(true) }

            var notemutable by remember { mutableStateOf(TextFieldValue("")) }
            val notehintStatus= remember { mutableStateOf(true) }

            TitleCode(
                title = titlemutable,
                onQueryChange = {
                    titlemutable = it
                    hintStatus.value = titlemutable.text.equals("")
                },
                hintStatus,
                notemutable,
                onNoteQueryChange = {
                    notemutable = it
                    notehintStatus.value = notemutable.text.equals("")
                },
                notehintStatus
            )

        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun TitleCode(
        title: TextFieldValue,
        onQueryChange: (TextFieldValue) -> Unit,
        hintStatus: MutableState<Boolean>,
        note: TextFieldValue,
        onNoteQueryChange: (TextFieldValue) -> Unit,
        notehintStatus: MutableState<Boolean>
    ) {

        val gradientcolors = listOf<List<Color>>(
            TipcalcTheme.colors.gradient_yellow,
            TipcalcTheme.colors.gradient_pink,
            TipcalcTheme.colors.gradient_orange,
            TipcalcTheme.colors.gradient_green,
            TipcalcTheme.colors.gradient_blue,
            TipcalcTheme.colors.gradient_brown,
            TipcalcTheme.colors.gradient_cream,
            TipcalcTheme.colors.gradient_bluegreen,
            TipcalcTheme.colors.gradient_grey,
            TipcalcTheme.colors.gradient_blue,
            TipcalcTheme.colors.gradient_brown,
            TipcalcTheme.colors.gradient_cream,
            TipcalcTheme.colors.gradient_bluegreen,
            TipcalcTheme.colors.gradient_grey,

            )
        val labels = listOf<NotesCategory>(
            NotesCategory(1, 1, "Work"),
            NotesCategory(2, 1, "Important"),
            NotesCategory(3, 1, "Todo"),
            NotesCategory(4, 1, "Overview"),
            NotesCategory(5, 1, "Project"),
            NotesCategory(6, 1, "WorkFlow"),
            NotesCategory(7, 1, "Company"),
            NotesCategory(8, 1, "Coding"),
            NotesCategory(9, 1, "Session"),
            NotesCategory(10, 1, "Design"),
            NotesCategory(11, 1, "Blog"),


            )

        val spinnerselected = remember { mutableStateOf(2) }
        val spinnerselectedColor = remember { mutableStateOf(gradientcolors[0]) }
        val initialspinnerselectedColor = remember { mutableStateOf(gradientcolors[0]) }




        Stack(
            modifier = Modifier.fillMaxSize().background(TipcalcTheme.colors.bgcolor)
        )
        {


            Column(modifier = Modifier.padding(top = 8.dp)) {

                Stack(modifier = Modifier.fillMaxWidth()) {

                    IconButton(
                        onClick = {
                            findNavController().popBackStack()

                        }, modifier = Modifier.wrapContentSize().padding(16.dp)

                            .align(Alignment.CenterStart)
                    ) {

                        Icon(
                            asset = Icons.Outlined.ArrowBack,

                            )

                    }

                    Row(
                        modifier = Modifier.wrapContentSize().align(Alignment.CenterEnd)
                            .padding(
                                end = 16.dp
                            )
                    )
                    {


                        IconButton(onClick = {
                            findNavController().popBackStack()

                        }
                        ) {

                            Icon(
                                asset = Icons.Outlined.PushPin,
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        }



                        IconButton(
                            onClick = {
                                findNavController().popBackStack()

                            },
                        ) {

                            Icon(
                                asset = Icons.Filled.AlarmAdd,
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        }

                    }
                }

                TitleContent(title, onQueryChange, hintStatus)


                val (colorState, colorTransitionDefinition) = AfterTitle(
                    initialspinnerselectedColor,
                    spinnerselectedColor,
                    spinnerselected,
                    gradientcolors,
                    CircleShape,
                    labels
                )
                val toStateColor by remember { mutableStateOf(ColorAnimation.FINAL) }

                val ColorStatesTransisiton = transition(
                    definition = colorTransitionDefinition,
                    initState = colorState.value,
                    toState = toStateColor
                )

                ScrollableColumn(

                )
                {

                        Box(
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.padding(16.dp).wrapContentHeight()
                                .preferredHeightIn(480.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .offsetGradientBackground(
                                    listOf(
                                        ColorStatesTransisiton[colorProp1],
                                        ColorStatesTransisiton[colorProp2]
                                    ),
                                    480f,
                                    5f
                                )


                        )
                        {


                            NoteContent(note, onNoteQueryChange, notehintStatus)
                        }


                    AddItems()
                }


            }
        }


    }

    @Composable
    private fun AddNotesFragment.AddItems() {


        val clickedState = remember { mutableStateOf(ROTATIONSTATE.IDLE) }

        var primarycolor=TipcalcTheme.colors.primary
        val addLists: List<VectorAsset> = listOf(
            Icons.Outlined.CheckBox,
            Icons.Outlined.PhotoCamera,
            Icons.Outlined.KeyboardVoice
        )


        val rotationTransitionDefinition = transitionDefinition<ROTATIONSTATE> {
            state(ROTATIONSTATE.IDLE) {
                this[rotation] = 0f
                this[opacity] = 0f
                this[width] = 75.dp
                this[cancelColor] = Color.Transparent
            }
            state(ROTATIONSTATE.PRESSED) {
                this[rotation] = 45f
                this[opacity] = 1f
                this[width] = 200.dp
                this[cancelColor] = primarycolor
            }

            transition(ROTATIONSTATE.IDLE to ROTATIONSTATE.PRESSED) {

    //                            rotation using repeatable(
    //                                animation = tween<Float>(
    //                                    durationMillis = 2000,
    //                                    easing = FastOutLinearInEasing
    //                                ),
    //                                iterations = 1
    //                            )

                rotation using tween(
                    durationMillis = 2000, easing = FastOutSlowInEasing
                )

                width using tween(durationMillis = 2000)
                cancelColor using tween(durationMillis = 2000)
                opacity using tween(durationMillis = 2000)
            }
            transition(ROTATIONSTATE.PRESSED to ROTATIONSTATE.IDLE) {
                rotation using tween(
                    durationMillis = 2000, easing = FastOutSlowInEasing
                )

                width using tween(durationMillis = 2000)
                opacity using tween(durationMillis = 2000)
                cancelColor using tween(durationMillis = 2000)
    //                            rotation using repeatable(
    //                                animation = tween<Float>(
    //                                    durationMillis = 2000,
    //                                    easing = FastOutLinearInEasing
    //                                ),
    //                                iterations = 1
    //                            )
            }
        }
        val toState =
            if (clickedState.value == ROTATIONSTATE.IDLE) {
                ROTATIONSTATE.PRESSED
            } else {
                ROTATIONSTATE.IDLE
            }

        Transition(
            definition = rotationTransitionDefinition,
            initState = clickedState.value,
            toState = toState
        )
        {
            AddItemContent(it, addLists, clickedState)

        }
    }

    @Composable
    private fun AddNotesFragment.AddItemContent(
        it: TransitionState,
        addLists: List<VectorAsset>,
        clickedState: MutableState<ROTATIONSTATE>
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),

            modifier = Modifier.wrapContentHeight().padding(8.dp)
                .width(it[width]).padding(8.dp)
                .background(
                    it[cancelColor],
                    shape = RoundedCornerShape(8.dp)
                ),
            backgroundColor =  it[cancelColor]

        )
        {

            Row(
                modifier = Modifier.wrapContentSize()
            )
            {
                Row(
                    modifier = Modifier.width((it[width]) - 75.dp)
                        .drawOpacity(it[opacity])
                )
                {
                    addLists.forEachIndexed { index, icon ->

                        IconButton(onClick = { }) {
                            Icon(asset = icon, modifier = Modifier.size(20.dp))

                        }

                    }
                }


                IconButton(

                    onClick = {
                        clickedState.value =
                            if (clickedState.value == ROTATIONSTATE.IDLE) {
                                ROTATIONSTATE.PRESSED
                            } else {
                                ROTATIONSTATE.IDLE
                            }

                    },
                    modifier = Modifier.wrapContentSize()
                        .background(Color.Transparent)
                        .drawLayer(
                            rotationZ = it[rotation]
                        )

                )
                {


                    Icon(asset = Icons.Outlined.AddBox)

                }
            }

        }
    }

    @Composable
    private fun AddNotesFragment.AfterTitle(
        initialspinnerselectedColor: MutableState<List<Color>>,
        spinnerselectedColor: MutableState<List<Color>>,
        spinnerselected: MutableState<Int>,
        gradientcolors: List<List<Color>>,
        CircleShape: RoundedCornerShape,
        labels: List<NotesCategory>
    ): Pair<MutableState<ColorAnimation>, TransitionDefinition<ColorAnimation>> {
        val colorState = remember { mutableStateOf(ColorAnimation.INITIAL) }

        val colorTransitionDefinition = transitionDefinition<ColorAnimation> {
            state(ColorAnimation.INITIAL) {

                this[colorProp1] = initialspinnerselectedColor.value[0]
                this[colorProp2] = initialspinnerselectedColor.value[1]
            }
            state(ColorAnimation.FINAL) {
                this[colorProp1] = spinnerselectedColor.value[0]
                this[colorProp2] = spinnerselectedColor.value[1]
            }

            transition(ColorAnimation.INITIAL to ColorAnimation.FINAL) {

    //                            rotation using repeatable(
    //                                animation = tween<Float>(
    //                                    durationMillis = 2000,
    //                                    easing = FastOutLinearInEasing
    //                                ),
    //                                iterations = 1
    //                            )

                colorProp1 using tween(
                    durationMillis = 2000
                )
                colorProp2 using tween(
                    durationMillis = 2000
                )


            }
            transition(ColorAnimation.FINAL to ColorAnimation.INITIAL) {
                colorProp1 using tween(
                    durationMillis = 2000
                )
                colorProp2 using tween(
                    durationMillis = 2000
                )

    //                            rotation using repeatable(
    //                                animation = tween<Float>(
    //                                    durationMillis = 2000,
    //                                    easing = FastOutLinearInEasing
    //                                ),
    //                                iterations = 1
    //                            )
            }
        }




        SpinnerContent(spinnerselected, gradientcolors, CircleShape, colorState, labels) {
            initialspinnerselectedColor.value = spinnerselectedColor.value
            spinnerselectedColor.value = it

            colorState.value = ColorAnimation.INITIAL


        }
        return Pair(colorState, colorTransitionDefinition)
    }


    @Composable
    private fun AddNotesFragment.SpinnerContent(
        spinnerselected: MutableState<Int>,
        gradientcolors: List<List<Color>>,
        CircleShape: RoundedCornerShape,

        colorState: MutableState<ColorAnimation>,
        labels: List<NotesCategory>,
        colorSelected: (List<Color>) -> Unit
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            val paddingend = if (spinnerselected.value != 1) 60.dp else 0.dp
            SpinnerCompose(spinnerselected)
            androidx.compose.foundation.layout.Stack() {
                ScrollableRow(
                    contentPadding = PaddingValues(5.dp),
                    modifier = Modifier.padding(start = 8.dp).padding(end = paddingend)
                ) {
                    if (spinnerselected.value == 1) {
                        gradientcolors.forEach { colors ->


                            Box(
                                shape = RoundedCornerShape(12.dp),

                                modifier = Modifier.padding(start = 8.dp, end = 8.dp).height(50.dp)
                                    .width(30.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .offsetGradientBackground(
                                        colors,
                                        60f,
                                        5f
                                    )
                                    .clickable(onClick = {
                                        colorSelected(colors)
                                    }


                                    )
                            )
                        }
                    } else {
                        labels.forEach { lable ->


                            Card(
                                shape = RoundedCornerShape(15.dp),
                                border = BorderStroke(
                                    2.dp,
                                    color = TipcalcTheme.colors.textPrimary
                                ),
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                lable.category_name?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }

                    }
                }

                if (spinnerselected.value != 1) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.width(60.dp).align(Alignment.CenterEnd)
                    ) {
                        Icon(asset = Icons.Filled.Add, modifier = Modifier.width(60.dp))

                    }
                }
            }

        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun TitleContent(
        title: TextFieldValue,
        onQueryChange: (TextFieldValue) -> Unit,
        hintStatus: MutableState<Boolean>
    ) {

        Stack() {

            BaseTextField(
                value = title,
                textStyle = MaterialTheme.typography.h5.copy(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,

                    ),


                textColor = TipcalcTheme.colors.backgroundreverse,


                onValueChange = onQueryChange,

                onTextInputStarted = {

                    hintStatus.value = false


                },
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp
                ).fillMaxWidth(),
            )
            if (hintStatus.value) {
                Text(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    ).wrapContentSize().align(Alignment.BottomStart),
                    text = "Title",
                    color = TipcalcTheme.colors.backgroundreverse,
                    style = MaterialTheme.typography.h5.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                    )
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun NoteContent(
        note: TextFieldValue,
        onQueryChange: (TextFieldValue) -> Unit,
        hintStatus: MutableState<Boolean>
    ) {

        Stack() {

            BaseTextField(
                value = note,
                textStyle = MaterialTheme.typography.h6.copy(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,

                    ),


                textColor = TipcalcTheme.colors.backgroundreverse,


                onValueChange = onQueryChange,

                onTextInputStarted = {

                    hintStatus.value = false


                },
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp
                ).wrapContentHeight().fillMaxWidth(),
            )
            if (hintStatus.value) {
                Text(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    ).wrapContentSize().align(Alignment.BottomStart),
                    text = "Note",
                    color = Color.DarkGray,
                    style =  MaterialTheme.typography.h6.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,

                        ),
                )
            }
        }
    }

    @Composable
    fun SpinnerCompose(spinnerselected: MutableState<Int>) {


        val spinnerItems: MutableList<MySpinnerItem> = ArrayList()
        spinnerItems.add(MySpinnerItem(1, "Color"))
        spinnerItems.add(MySpinnerItem(2, "Lable"))


        val enabled = false




        DropDown(productList = spinnerItems, enabled, spinnerselected)


//            AndroidView(
//                modifier = Modifier.padding(8.dp).wrapContentSize()
//                    .background(Color.Transparent),
//                viewBlock = { ctx ->
//
//
//                    Spinner(ctx).apply {
//                        id = R.id.spinner
//                        layoutParams = LinearLayout.LayoutParams(
//                            ViewGroup.LayoutParams.WRAP_CONTENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT
//                        )
//                        adapter = adapter
//
//                    }
//                })
//            adapter?.notifyDataSetChanged()
//        }
//
////                RecyclerView(ctx).apply {
////                    id = R.id.staggered
////                    setBackgroundColor(color)
////                    layoutParams = LinearLayout.LayoutParams(
////                        ViewGroup.LayoutParams.MATCH_PARENT,
////                        ViewGroup.LayoutParams.WRAP_CONTENT
////                    )
////                    layoutManager = staggeredGridLayoutManager
////                    adapter = staggeredRecyclerViewAdapter
////
////
////                }
//            },
//        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewDashbord() {
        TipcalcTheme {

            AddNotes()
        }

    }

    @Composable
    fun DropDown(
        productList: List<MySpinnerItem>,
        enabled: Boolean,
        spinnerselected: MutableState<Int>
    ) {

        var mutableenable by remember { mutableStateOf(enabled) }

        var title by remember { mutableStateOf(productList.get(0)) }
        DropdownMenu(
            expanded = mutableenable,
            onDismissRequest = { mutableenable = false },
            toggle = iconButtons(mutableenable, title) {
                mutableenable = !mutableenable
            },
            dropdownOffset = Position((-120).dp, 1.dp),
            dropdownModifier = Modifier.background(Color.Transparent),
            toggleModifier = Modifier.background(Color.Transparent),


            ) {
            productList.forEach { product ->
                DropdownMenuItem(onClick = {
                    title = product
                    mutableenable = false
                    spinnerselected.value = product.id
                }, modifier = Modifier.width(120.dp).background(Color.Transparent)) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        if (product.id == 1) {
                            Box(
                                shape = CircleShape,
                                border = BorderStroke(2.dp, color = TipcalcTheme.colors.background),
                                modifier = Modifier.padding(end = 8.dp).height(30.dp).width(30.dp)
                                    .clip(CircleShape)
                                    .offsetGradientBackground(
                                        TipcalcTheme.colors.gradient2_1,
                                        60f,
                                        5f
                                    )


                            )
                        } else {

                            Icon(
                                asset = Icons.Filled.Label,
                                modifier = Modifier.padding(end = 8.dp).height(30.dp).width(30.dp)
                            )

                        }
                        Text(
                            text = product.comboOption,
                            color = TipcalcTheme.colors.backgroundreverse
                        )
                    }


                }

            }

//            DropdownMenuItem(onClick = { title="Settings" }) {
//                Text("Settings")
//            }
//
//            DropdownMenuItem(onClick = { title="Feedback" }) {
//                Text("Send Feedback")
//            }
        }
//        LazyColumnFor(items = productList) {
//
//        }
//        LazyColumnFor(items = productList) {productLists ->
//
//
//            Button(onClick = { PopupState.toggleOwner(productLists) }) {
//                Card(...) {...}
//            }
//            if (PopupState.owner == productLists) {
//                DropdownMenu()
//                {
//
//                    Surface(
//                        shape = RoundedCornerShape(4.dp),
//                        elevation = 16.dp,
//                        color = Color.White,
//                        modifier = Modifier.gravity(Alignment.End)+ Modifier.padding(end = 10.dp)
//                    )
//                    {
//                        Column(modifier = Modifier.padding(10.dp)) {
//
//                            MenuItem(text ="Edit", onClick = {})
//                            MenuItem(text = "Delete", onClick = {})
//                            MenuItem(text = "Details", onClick = {})
//
//
//                        }
//                    }
//                }
//            }
//
//
//        }
    }


    private fun iconButtons(
        mutableenable: Boolean,
        items: MySpinnerItem,
        onClick: () -> Unit
    ): @Composable() () -> Unit {


        val iconButton = @Composable {
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, color = TipcalcTheme.colors.textPrimary),
                modifier = Modifier.padding(start = 16.dp)
                    .background(TipcalcTheme.colors.background, shape = RoundedCornerShape(8.dp)),


                )
            {
                TextButton(
                    onClick = onClick,
                    modifier = Modifier.width(120.dp).background(Color.Transparent),
                    backgroundColor = Color.Transparent
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        if (items.id == 1) {
                            Box(
                                shape = CircleShape,
                                border = BorderStroke(2.dp, color = TipcalcTheme.colors.background),
                                modifier = Modifier.padding(end = 8.dp).height(30.dp).width(30.dp)
                                    .clip(CircleShape)
                                    .drawOpacity(.5f)
                                    .offsetGradientBackground(
                                        TipcalcTheme.colors.gradient2_1,
                                        60f,
                                        5f
                                    )


                            )
                        } else {

                            Icon(
                                asset = Icons.Filled.Label,
                                modifier = Modifier.padding(end = 8.dp).height(30.dp)
                                    .width(30.dp)
                            )
                        }

                        Text(
                            text = items.comboOption,
                            color = TipcalcTheme.colors.backgroundreverse
                        )


                    }
                }
            }
        }
        return iconButton
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNotesViewModel::class.java)
        var notesDB= NotesDB(1,"Nihad","This is a sample note",false,"1","0","" +
                "0","false",date = OffsetDateTime.now())

        viewModel.setStateEvent(AddNoteStateEvent.AddNewNotes(),notesDB)

    }
}





