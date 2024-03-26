package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyclerview.ui.theme.MyAppTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.recyclerview.compose.TvShowListItem
import com.example.recyclerview.data.TvShowList
import com.example.recyclerview.model.TvShow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Not Recommended approach
            // ScrollableColumnDemo()

            // Recommended approach
            // LazyColumnDemo()

            /*
            // Adding lambda expression as a parameter for click listener
            // Using clickable modifier to pass the selected item
            LazyColumnDemo2 {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
             */
            
            DisplayTvShows{
               // Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
                startActivity(InfoActivity.intent(this, it))
            }
        }
        /* Default Content:->
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        } */
    }
}


// Displaying TvShow list
//@Preview
@Composable
private fun DisplayTvShows(selectedItem: (TvShow) -> Unit) {
    val tvShows = remember { TvShowList.tvShows }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = tvShows,
            itemContent = {
                TvShowListItem(tvShow = it, selectedItem = selectedItem)
            }
        )
    }
}


// LazyColumn : It will create and emits only set of items
// which are required to create current screen. i.e like Recycler view
// It's Efficient and recommended way
@Preview
@Composable
fun LazyColumnDemo() {
    LazyColumn {
        items(100) { index ->
            Text(
                text = "User Name $index",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(10.dp)
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

// Adding lambda expression as a parameter for click listener
// Using clickable modifier to pass the selected item
//@Preview
@Composable
fun LazyColumnDemo2(selectedItem: (String) -> (Unit)) {
    LazyColumn {
        items(100) { index ->
            Text(
                text = "User Name $index",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { selectedItem("$index Selected") }
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

// This is similar to list view wherein all elements are loaded at one time
// It's inefficient way so go with LazyColumn
@Preview
@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..100) {
            Text(
                text = "User Name $i",
                style = MaterialTheme.typography.h5,
                color = Color.Blue,
                modifier = Modifier.padding(10.dp)
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}


/* Default Content:->
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}
*/