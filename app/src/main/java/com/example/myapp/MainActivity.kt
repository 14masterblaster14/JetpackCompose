package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.MyAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
Column : Vertical Arrangement (Horizontal Alignment)
Row : Horizontal Arrangement (Vertical Alignment)

Space Between :  |A__B__C|
Space Evenly  :  |__A__B__C__|
Space Around  :  |_A__B__C_|

SnackBar :
1) A Scaffold State : It's a layout which implements the basic Material Design
layout structure. It's a another layer on activity. We used it to place
Floating Action Button, Navigation Drawer, Top Bars, Bottom Bars, SnackBars.
2) A Coroutine Scope

 **/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Column & Rows starts -->
        /*
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*
                    // Columns
                    Column(
                        modifier = Modifier
                            .background(color = Color.LightGray)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Android")
                        Greeting("Jetpack")
                        Greeting("Compose")
                    }
                    */
                    // Rows
                    Row(
                        modifier = Modifier
                            .background(color = Color.LightGray)
                            //.fillMaxWidth()
                            //.height(20.dp),
                      // horizontalArrangement = Arrangement.SpaceEvenly
                            .fillMaxHeight()
                            .width(60.dp),
                        verticalAlignment = Alignment.Bottom

                    ) {
                        Greeting("Ab")
                        Greeting("Cd")
                        Greeting("Ef")
                    }

                }
            }
        }
        // Column & Rows ends -->
*/
        // Box Layout starts -->
        /*
        setContent {
            //BoxExample1()
            //BoxExample2()
            BoxExample3()
        }
         */
        // Box Layout ends -->

        // Buttons starts -->
        /*
        setContent {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                ButtonDemo()
            }
        }
         */
        // Buttons ends -->

        // SnackBar starts -->
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplaySnackBar()
                }
            }
        }
        // SnackBar ends -->

    }
}

// Column & Rows starts -->

@Composable
fun Greeting(name: String) {
    Text(
        text = "$name",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Yellow)
            .padding(10.dp) //before border(from out to in)
            .border(2.dp, color = Color.Green)
            .padding(20.dp) //after border(from out to in)
        //.fillMaxWidth(0.5f)
        //.fillMaxHeight(0.3f)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}
// Column & Rows ends -->

// Box Layout starts -->
@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .size(180.dp, 300.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(125.dp, 100.dp)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "Hi",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .background(color = Color.White)
                    .size(90.dp, 50.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun BoxExample2() {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
    ) {
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.TopStart),
            text = "TopStart"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.TopCenter),
            text = "TopCenter"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.TopEnd),
            text = "TopEnd"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.CenterStart),
            text = "CenterStart"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.Center),
            text = "Center"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.CenterEnd),
            text = "CenterEnd"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.BottomStart),
            text = "BottomStart"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            text = "BottomCenter"
        )
        Text(
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .align(Alignment.BottomEnd),
            text = "BottomEnd"
        )
    }
}

@Composable
fun BoxExample3() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.water_wave),
            contentDescription = "Water wave"
        )
        Text(
            text = "Water Wave",
            style = MaterialTheme.typography.h4,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp)
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.DarkGray
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .border(5.dp, Color.DarkGray, RectangleShape)
        ) {
            Text(text = "Add To Cart")
        }
    }
}
// Box Layout ends -->

// Buttons starts -->
@Composable
fun ButtonDemo() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
        }) {
        Text(text = "Add To Cart")
    }

    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
        },
        enabled = false
    ) {
        Text(text = "Add To Cart")
    }

    TextButton(onClick = {
        Toast.makeText(context, "Clicked on TextButton", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Add To Cart")
    }

    OutlinedButton(onClick = {
        Toast.makeText(context, "Clicked on OutlinedButton", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Add To Cart")
    }

    IconButton(onClick = {
        Toast.makeText(context, "Clicked on ImageButton", Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            Icons.Filled.Refresh,
            contentDescription = "Refresh Button",
            tint = Color.DarkGray,
            modifier = Modifier.size(80.dp)
        )
    }

    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Custom Button", Toast.LENGTH_SHORT).show()
        },
        contentPadding = PaddingValues(8.dp),
        border = BorderStroke(8.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Add To Cart",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(8.dp)
        )
    }

    Button(
        onClick = {
            Toast.makeText(
                context,
                "Clicked on Custom CutCornerShape Button",
                Toast.LENGTH_SHORT
            ).show()
        },
        shape = CutCornerShape(8.dp),
        contentPadding = PaddingValues(8.dp),
        border = BorderStroke(8.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Add To Cart",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(8.dp)
        )
    }

    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Custom CircleShape Button", Toast.LENGTH_SHORT)
                .show()
        },
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        border = BorderStroke(8.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Add To Cart",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(8.dp)
        )
    }
}
// Buttons ends -->

// SnackBar starts -->
@Composable
fun DisplaySnackBar() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Button(
            onClick = {
                coroutineScope.launch {
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = "This is Snackbar message",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                    when (snackbarResult) {
                        SnackbarResult.ActionPerformed -> Log.i("MYTAG", "Action label clicked")
                        SnackbarResult.Dismissed -> Log.i("MYTAG", "Dismissed!")
                    }
                }
            }
        ) {
            Text(
                text = "Display Snackbar",
                modifier = Modifier.padding(it)
            )
        }
    }
}
// SnackBar ends -->

