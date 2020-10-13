import android.R
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.compose.foundation.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.FragmentActivity
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.model.MySpinnerItem


//import android.app.Activity
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.TextView
//import androidx.compose.foundation.Text
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.ComposeView
//import com.nihad.notes.R
//import com.nihad.notes.ui.TipcalcTheme
//import com.nihad.notes.ui.notes.adapter.model.MySpinnerItem
//
//
//class SpinnerAdapterViews(
//    context: Activity?,
//    resouceId: Int,
//    textviewId: Int,
//    list: List<MySpinnerItem?>?
//) :
//    ArrayAdapter<MySpinnerItem?>(context!!, resouceId, textviewId, list!!) {
//    var flater: LayoutInflater? = null
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return rowview(convertView, position)
//    }
//
//
//
//    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup?): View? {
//        return rowview(convertView, position)
//    }
//
//    private fun rowview(convertView: View?, position: Int): View {
//        val rowItem: MySpinnerItem? = getItem(position)
//        val holder: viewHolder
//        var rowview: View? = convertView
//        if (rowview == null) {
//
//
//            holder = viewHolder()
//            flater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        rowview = flater!!.inflate(R.layout.grid_column, null, false)
//            holder.compose_view = rowview.findViewById(R.id.compose_view)
//            holder.compose_view?.setContent {
//                TipcalcTheme {
//                    rowItem?.comboOption?.let { Text(text = it, color = Color.Blue) }
//
//                }
//            }
//            rowview.setTag(holder)
//        } else {
//            holder = rowview.getTag() as viewHolder
//        }
//
//
////        holder.imageView.setImageResource(rowItem.getImageId())
////        holder.txtTitle.setText(rowItem.getTitle())
//        return rowview!!
//    }
//
//
//    private class viewHolder {
//        var compose_view: ComposeView? = null
//    }
//}


class SpinnerAdapterViews(context: FragmentActivity, resouceId: Int, textviewId: Int, list: List<MySpinnerItem?>?) :
    ArrayAdapter<MySpinnerItem?>(context, resouceId, textviewId,
        list as MutableList<MySpinnerItem?>
    ) {
    var flater: LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowItem: MySpinnerItem? = getItem(position)
        val rowview: View = flater.inflate(com.nihad.notes.R.layout.grid_column, null, true)
        val txtTitle = rowview.findViewById(R.id.title) as TextView
        txtTitle.setText(rowItem?.comboOption)
        val composeView: ComposeView = rowview.findViewById(com.nihad.notes.R.id.compose_view) as ComposeView

        composeView?.setContent {
                TipcalcTheme {
                    rowItem?.comboOption?.let { Text(text = it, color = Color.Blue) }

                }
            }
        return rowview
    }

    init {
        flater = context.layoutInflater
    }
}