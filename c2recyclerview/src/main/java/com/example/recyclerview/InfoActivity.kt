package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyclerview.compose.ViewMoreInfo
import com.example.recyclerview.model.TvShow
import com.example.recyclerview.ui.theme.MyAppTheme

class InfoActivity : ComponentActivity() {

    companion object{
        private const val TvShowId = "tvshowid"
        fun intent(context: Context, tvShow: TvShow)=
            Intent(context, InfoActivity::class.java).apply {
                putExtra(TvShowId, tvShow)
            }
    }

    private val  tvShow : TvShow by lazy {
        intent?.getSerializableExtra(TvShowId) as TvShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ViewMoreInfo(tvShow = tvShow)
/* Default Content :->
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
*/
        }
    }
}

/* Default Content :->
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