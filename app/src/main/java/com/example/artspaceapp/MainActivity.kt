package com.example.artspaceapp

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(value = 1) }
    val imageResource = when(result) {
        1 -> R.drawable.argentina
        2 -> R.drawable.greece
        3 -> R.drawable.spain
        4 -> R.drawable.australia
        else -> R.drawable.island
    }
    val contentDesc = when(result) {
        1 -> R.string.argetina_fact
        2 -> R.string.greece_fact
        3 -> R.string.spain_fact
        4 -> R.string.australia_fact
        else -> R.string.island_fact
    }
    val countryName = when(result) {
        1 -> R.string.argetina_name
        2 -> R.string.greece_name
        3 -> R.string.spain_name
        4 -> R.string.australia_name
        else -> R.string.island_name
    }
    Column(modifier = Modifier.padding(16.dp)) {
        ArtSpaceImages(imageResource, contentDesc.toString())
        ArtSpaceText(countryName, contentDesc)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier
            .fillMaxSize()
        ){
            Box(modifier = Modifier.wrapContentWidth(Alignment.Start)){
                Button(onClick = {result = (1..5).random()}) {
                    Text(text= stringResource(R.string.btn_previous))
                }
            }
            Box(modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End)) {
                Button(onClick = {result = (1..5).random()}) {
                    Text(text = stringResource(R.string.btn_next))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceImages(
    imageResource: Int,
    contentDesc: String,
    modifier: Modifier = Modifier){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top=72.dp)){
            Image(
                painter = painterResource(imageResource),
                contentDescription = contentDesc,
                alignment = Alignment.Center,
                modifier = Modifier.padding(all = 28.dp)
            )
    }
}

@Composable
fun ArtSpaceText(
    countryName: Int,
    contentDesc: Int,
    modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(countryName),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text = stringResource(contentDesc)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}