package com.nihad.notes.ui.notes.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.R
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.StaggeredRecyclerViewAdapter.ViewHolder


class StaggeredRecyclerViewAdapter(
    context: Context,
    noteList: List<String>,
) :
    RecyclerView.Adapter<ViewHolder>() {
    private var noteLists: List<String> = noteList

    private val mContext: Context = context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_column, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.composeView.setContent {
            TipcalcTheme {

                NotesEach(position,noteLists)


            }
        }

    }



    override fun getItemCount(): Int {
        return noteLists.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var composeView: ComposeView = itemView.findViewById(R.id.compose_view)


    }

    companion object {
        private const val TAG = "StaggeredRecyclerViewAd"
    }


}

@Composable
private fun NotesEach(position: Int, noteLists: List<String>) {
    Surface(modifier = Modifier.wrapContentHeight(),color = Color.Transparent) {

        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp,color =TipcalcTheme.colors.textPrimary),
            modifier = Modifier.wrapContentHeight().padding(8.dp).drawShadow(3.dp,shape = RoundedCornerShape(8.dp)).background(
                TipcalcTheme.colors.background)
            ,backgroundColor = TipcalcTheme.colors.background
        ) {
            Column() {

                if(position==1)
                {

                    val image = loadImageResource(R.drawable.landscape)
                    image.resource.resource?.let {

                        Image(asset = it,
                            modifier = Modifier
                                .fillMaxWidth())
                    }
                }
                Text(text = noteLists[position],modifier = Modifier.wrapContentHeight().padding(8.dp),
                color = TipcalcTheme.colors.backgroundreverse)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private  fun PreviewNoteEach()
{
    TipcalcTheme()
    {
        NotesEach(position = 1, noteLists = listOf("String1","String2"))
    }
}
@Preview(showBackground = true)
@Composable
private  fun PreviewNoteEachdark()
{
    TipcalcTheme(darkTheme = true)
    {
        NotesEach(position = 1, noteLists = listOf("String1","String2"))
    }
}
