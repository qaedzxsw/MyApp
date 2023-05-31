package com.example.myapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthorInfo(this@MainActivity)
                }
            }
        }
    }
}

@Composable
fun AuthorInfo(activity: Activity) {
    var isRectTouched by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "作者：資工四B 賴威銡")

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.map),
                contentDescription = "Map Image",
                modifier = Modifier.fillMaxSize()
            )

            Canvas(
                modifier = Modifier.fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { tap ->
                            val rect1Width = 40f
                            val rect1Height = 40f
                            val rect1Position = Offset(1670f, 880f)

                            if (tap.x >= rect1Position.x && tap.x <= rect1Position.x + rect1Width &&
                                tap.y >= rect1Position.y && tap.y <= rect1Position.y + rect1Height
                            ) {
                                isRectTouched = !isRectTouched
                                if (isRectTouched) {
                                    showToast(activity, "清水南社社區")
                                }
                            }
                        }
                    }
            ) {
                val rect1Width = 40f
                val rect1Height = 40f
                val rect1Position = Offset(1670f, 880f)

                drawRect(
                    color = Color.Blue,
                    topLeft = rect1Position,
                    size = Size(rect1Width, rect1Height)
                )

                val rect2Width = 40f
                val rect2Height = 40f
                val rect2Position = Offset(780f, 115f)
                drawRect(
                    color = Color.Blue,
                    topLeft = rect2Position,
                    size = Size(rect2Width, rect2Height)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        AuthorInfo(Activity())
    }
}

fun showToast(activity: Activity, message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}
