package com.example.composestatedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
            val viewModel = viewModel<MyViewModel>()
            MyAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Without Property delegates and with Property delegates
                    // MyButton()
                    // With Unidirectional Data Flow and State Hoisting << Highly Recommended >>
                    //var count by rememberSaveable{mutableStateOf(0)} // remember state but prefer to use ViewModel
                    val count = viewModel.count
                    MyButton1(count) {  // Passing state as an argument to composable
                        //count = it + 1     // Receiving event from composable to caller using function parameter and lambda
                                            // This is called Unidirectional Data Flow
                        viewModel.increaseCount()
                    }
                }
            }
        }
    }
}

/** It will update the counter in toast but not on button */
//var count = 0
/** It will update the counter in toast and button text as well but not the best way to handle the composition
 *  We will have multiple composable functions, so better to to define these values locally */
//val count = mutableStateOf(0)

@Preview(name = "MyButton")
@Composable
fun MyButton() {
    val context = LocalContext.current
    //val count = remember { mutableStateOf(0)}
    // With Property Delegates
    var count by remember { mutableStateOf(0) } // To save the state
    Button(
        onClick = {
            //count++
            //count.value += 1
            count += 1
            //Toast.makeText(context, "Count is : ${count.value}", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Count is : $count", Toast.LENGTH_SHORT).show()
        },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            //text = "Count is : ${count.value}",
            text = "Count is : $count",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
}

/**
Stateful composable is useful where caller doesn't need to control the state and can use it directly
But Jetpack Compose recommends to make the composable as stateless and pass the state by caller i.e. State Hoisting
State Hoisting has (current value to display , An event that request current value to change).
It allow us to store the state in DB, remote server etc and update the composable when required.
It provides Single Datasource i.e. Single Source of Truth and secured.
It makes States shareble between different composable and eliminates all risk with state sharing.
It allows us to Unit test the composable
 **/


//@Preview(name = "MyButton1") // Parameterised composable can't have preview
@Composable
fun MyButton1(currentCount: Int, updateCount: (Int) -> Unit) {

    Button(
        onClick = {
            updateCount(currentCount)
        },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            //text = "Count is : ${count.value}",
            text = "Count is : $currentCount",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
}