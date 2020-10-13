package com.nihad.notes

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Stack
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.ui.TipcalcTheme

import com.nihad.notes.ui.notes.NotesActivity
import com.nihad.notes.ui.purple200
import com.nihad.notes.ui.splashscreen.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.reflect.Modifier

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val windows=this.window
        windows.statusBarColor=Color.WHITE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View? = getWindow()?.getDecorView()
            if (true) {
                decor?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor?.systemUiVisibility = 0
            }
        }
        setContent {
            TipcalcTheme {
                // A surface container using the 'background' color from the theme

                Surface(color = TipcalcTheme.colors.background) {
                   SplashScreen()
                    GlobalScope.launch(Dispatchers.Main) {
                       delay(2000L)
                       launchActivity()


                    }
                }
            }
        }
    }

    private fun launchActivity() {
        startActivity(Intent(this,NotesActivity::class.java))
    }


}


