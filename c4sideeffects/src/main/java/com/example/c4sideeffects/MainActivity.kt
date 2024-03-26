package com.example.c4sideeffects

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.example.c4sideeffects.ui.theme.MyAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *  In lifecycle of composable there is only one Composition and
 *  multiple recomposition.
 *
 *  The change in state of app is outside the scope of composible function.
 *  So composible should be side effect free which is not practical.
 *
 *  There are total 8 Side effects handling mechanism
 *
 *  1) LaunchedEffect : run suspend functions in the scope of a composable
 *  2) rememberCoroutineScope: obtain a composition-aware scope to launch a coroutine outside a composable
 *  3) rememberUpdatedState: reference a value in an effect that shouldn't restart if the value changes
 *  4) DisposableEffect: effects that require cleanup
 *  5) SideEffect: publish Compose state to non-Compose code
 *  6) produceState: convert non-Compose state into Compose state
 *  7) derivedStateOf: convert one or multiple state objects into another state
 *  8) snapshotFlow: convert Compose's State into Flows
 *
 *
 *  Restarting effects
 *  Constants as keys
 *
 *  1) LaunchedEffect :
 *  Here, every time we are clicking on Count button, toast/snackbar message is shown
 *  again and again. It an example of side effects.
 *  We used LaunchedEffect to launch the co-routine inside the composition. So we can run
 *  our side effect causing code part inside the separate co-routine w/o getting affected by re-composiotions.
 *
 *  2) rememberCoroutineScope:
 *  We cannot put toast in button composible with LaunchedEffect , so we need to use rememberCoroutineScope.
 *  When we need to use coroutines in compasible
 *
 *  Use rememberCoroutineScope when we want to use coroutines  in a composible and need to cancel and relaunched the coroutine everytime parameter changes.
 *  If it's event use rememberCoroutineScope.
 *  need to cancel the coroutine after an event then use LaunchedEffect.
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    var round by remember { mutableStateOf(1) }
    var total by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }
    val coroutineScope : CoroutineScope = rememberCoroutineScope()

    //val scaffoldState: ScaffoldState = rememberScaffoldState()

    // Scaffold(scaffoldState = scaffoldState ) {

    /**
     *  LaunchedEffect
     */

    //LaunchedEffect(key1 = true, ) {
    LaunchedEffect(key1 = round,) {
        Toast.makeText(context, "Please, start counting round $round...", Toast.LENGTH_LONG).show()
        //  scaffoldState.snackbarHostState.showSnackbar(
        //      message = "Please, start counting round $round...",
        //      duration = SnackbarDuration.Short
        //      )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Total is ${total.toString()}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.DarkGray
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text("Enter value here") },
            value = input,
            onValueChange = {
                input = it
            },
            textStyle = TextStyle(
                color = Color.LightGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            label = { Text(text = "New count") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // This is for LaunchedEffect
        /*
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                total += input.toDouble()
                if (total > 300) {
                    total = 0.0
                    input = ""
                    round++
                }
            }
        ) {
            Text(
                text = "Count",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        */

        // This is for rememberCoroutineScope
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                total += input.toDouble()
                coroutineScope.launch {
                        Toast.makeText(context, "Countiong Updated..", Toast.LENGTH_LONG).show()
                        //  scaffoldState.snackbarHostState.showSnackbar(
                        //      message = "Please, start counting round $round...",
                        //      duration = SnackbarDuration.Short
                        //      )

                }
                if (total > 300) {
                    total = 0.0
                    input = ""
                    round++
                }
            }
        ) {
            Text(
                text = "Count",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
    //}
}
