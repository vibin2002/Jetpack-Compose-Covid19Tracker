package com.example.covidui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import com.example.covidui.newsapi.ApiHelper
import com.example.covidui.newsapi.Apiservice
import com.example.covidui.newsapi.RetrofitBuilder
import com.example.covidui.newsapi.newsmodel.Newsfeed
import com.example.covidui.ui.CovidUITheme
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CovidUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color(0xFF404369)) {
                    CovidHomeScrn()
                }
            }
        }
        setupViewModel()
    }
//MainViewModelFactory(ApiHelper(RetrofitBuilder.newsapiservice))
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,MainViewModelFactory(Repository()))
            .get(MainViewModel::class.java)
    }
}

@Preview
@Composable
fun CovidHomeScrn(model: MainViewModel = viewModel()) {

    val news by model.getNewsData().observeAsState()
    val corona by model.getCoronaData().observeAsState()

    Log.e("ViewModel" , corona.toString())

    ScrollableColumn(modifier = Modifier.fillMaxHeight()) {
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth())
        {
            Column() {
                Text(
                        text = "Stay Home",
                        style = TextStyle(
                                fontSize = TextUnit.Companion.Sp(28),
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                        ),
                        modifier = Modifier.padding(start = 24.dp)
                )
                Text(text = "Stay Safe",
                        style = TextStyle(
                                fontSize = TextUnit.Companion.Sp(28),
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                        ),
                        modifier = Modifier.padding(start = 24.dp, top = 6.dp)
                )
            }
            Button(onClick = {}, modifier = Modifier.clip(shape = RoundedCornerShape(20)).padding(end = 24.dp).height(60.dp)) {
                Text(text = "WorldWide")
            }
        }
        if (corona == null)
            CircularProgressIndicator()
        else {
            HomeCard(
                title = "Confirmed",
                count = corona!!.cases.toString(),
                cardbg = Color(0xFFffa48e),
                emojidrawable = imageResource(id = R.drawable.confirmed_emoji)
            )
            HomeCard(
                title = "Active",
                count = corona!!.active.toString(),
                cardbg = Color(0xFF4acfac),
                emojidrawable = imageResource(id = R.drawable.active_emoji)
            )
            HomeCard(
                title = "Recovered",
                count = corona!!.recovered.toString(),
                cardbg = Color(0xFF5ddbe8),
                emojidrawable = imageResource(id = R.drawable.happy_emoji)
            )
            HomeCard(
                title = "Deaths",
                count = corona!!.deaths.toString(),
                cardbg = Color(0xFF7e8ce0),
                emojidrawable = imageResource(id = R.drawable.death_emoji)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        Text(
                text = "Latest updates",
                style = TextStyle(
                        fontSize = TextUnit.Companion.Sp(28),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                ),
                modifier = Modifier.padding(start = 24.dp)
        )
        if (news == null) CircularProgressIndicator() else ScrollableRowComponent(news = news)
    }
}

@Composable
fun ScrollableRowComponent(news: Newsfeed?) {


    ScrollableRow(modifier = Modifier.fillMaxWidth(), children = {
        Row() {
            for (article in news?.articles!!) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(16.dp).height(200.dp).width(250.dp),
                )
                {
                    Column()
                    {
                        var image by remember { mutableStateOf<ImageAsset?>(null) }
                        var drawable by remember { mutableStateOf<Drawable?>(null) }

                        onCommit("IMAGE")
                        {
                            val picasso = Picasso.get()

                            val target = object : Target {
                                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                                    image = bitmap?.asImageAsset()
                                }

                                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                                    drawable = errorDrawable
                                }

                                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                                    drawable = placeHolderDrawable
                                }
                            }

                            picasso.load(article.urlToImage).into(target)
                            onDispose {
                                image = null
                                drawable = null
                                picasso.cancelRequest(target)
                            }
                        }
                        if (image == null)
                            CircularProgressIndicator()
                        else
                            Image(
                                modifier = Modifier.weight(8f),
                                asset = image!!,
                                contentScale = ContentScale.Crop
                        )
                        Text(
                            text = article.title, modifier = Modifier.padding(4.dp).weight(2f),
                            fontSize = TextUnit.Companion.Sp(12)
                        )

                    }
                }
            }
        }
    })
}

@Composable
fun HomeCard(
        title: String,
        count: String,
        cardbg: Color,
        emojidrawable: ImageAsset
) {
    Card(
            modifier = Modifier.fillMaxWidth().padding(12.dp), backgroundColor = cardbg,
            elevation = 5.dp,
            shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.height(60.dp).padding(vertical = 5.dp).padding(start = 10.dp, end = 5.dp),
        )
        {
            Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .background(color = cardbg, shape = RoundedCornerShape(12.dp))
                    .weight(2.0f)
                    .align(Alignment.CenterVertically)
            )
            {
                Image(asset = emojidrawable, modifier = Modifier.align(Alignment.Center))
            }
            Text(
                    textAlign = TextAlign.Start,
                    text = title,
                    modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically).weight(6.0f),
                    fontSize = TextUnit.Companion.Sp(20),
                    fontFamily = FontFamily.Serif
            )
            Text(
                    textAlign = TextAlign.End,
                    text = count,
                    modifier = Modifier.padding(end = 16.dp).align(Alignment.CenterVertically).weight(4.0f),
                    fontSize = TextUnit.Companion.Sp(18),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CovidUITheme {
        CovidHomeScrn()
    }
}