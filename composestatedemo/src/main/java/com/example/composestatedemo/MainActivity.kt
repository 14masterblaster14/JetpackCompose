package com.example.composestatedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.ui.theme.MyAppTheme

/**
 * Re-composition can be achieved with the help of :
 *          - Mutable state
 *          - Live data
 *          - Flow
 *          - RxJava
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Column(
                   modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    MyButton()
                }
            }
        }
    }
}

/** It will update the counter in toast but not on button */
//var count = 0
/** It will update the counter in toast and button text as well but not the best way to handle the composition */
var count = mutableStateOf(0)

//@Preview(name = "MyButton")
@Composable
fun MyButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            //count++
            count.value = count.value + 1
            Toast.makeText(context, "Count is : ${count.value}", Toast.LENGTH_SHORT).show()
        },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Count is : ${count.value}",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
}