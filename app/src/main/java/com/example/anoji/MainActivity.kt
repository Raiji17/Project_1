package com.example.anoji

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.anoji.ui.theme.AnojiTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class ItemData(val name: String, val imageResId: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnojiTheme {
                Box(modifier = Modifier.fillMaxSize()){
                    Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = { Header("anoji") },
                            bottomBar = { FooterBar() }) { innerPadding ->
                        LazyColumn(modifier = Modifier.padding(innerPadding)){
                                itemDataList().let {
                                    createList(it)
                                }
                        }
                        }
                }
            }
        }
    }

    fun LazyListScope.createList(items :List<ItemData>){
        items(items) { item ->
            MyListItem(item)
        }
    }

    private fun itemDataList(): List<ItemData> {
        return List(50) {
            ItemData(name = "Item $it", imageResId = R.drawable.ic_launcher_foreground)
        }
    }
}

@Composable
fun Header(title: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 30.dp),
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    AnojiTheme {
        Header(title = "anoji")
    }
}

@Composable
fun MyButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
        .size(80.dp), onClick = { /* Aksi saat tombol ditekan */ }) {
        ImageProfile(imageResId = R.drawable.ic_launcher_foreground)
        Text(text = "Klik Saya")
    }
}

@Composable
fun MyListItem(item: ItemData) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            ListItem(
                leadingContent = {
                    ImageProfile(imageResId = item.imageResId)
                },
                headlineContent = {
                    Text(text = item.name)
                }
            )
            Column(modifier = Modifier
                .padding(start = 16.dp, bottom = 30.dp)
                .fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),

                ){
                    ImageProfile(imageResId = item.imageResId)

                    Text(text = "lokasi")

                }
                Text(text = "waktu")
            }
        }

    }
}

@Preview(name = "My List Item",
    showSystemUi = true
)
@Preview(name = "My List Item",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun MyListPreview(){
    AnojiTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { Header("anoji") },
                bottomBar = { FooterBar() }
            ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(items = List(50) { "Item $it" }) {item ->
                        MyListItem(ItemData(name = item, imageResId = R.drawable.ic_launcher_foreground))
                    }
                    item { MyButton() }
                    item { MyButton() }
                }

            }
            Column(modifier = Modifier.align(Alignment.BottomEnd)
                .padding(end = 25.dp)
                .padding(bottom = 70.dp)){
                MyButton()
            }
        }
    }
}

@Composable
fun FooterBar() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* Aksi pertama */ }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = { /* Aksi kedua */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /* Aksi ketiga */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFooterBar() {
    FooterBar()
}


@Composable
fun ImageProfile(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Image Profile",
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
