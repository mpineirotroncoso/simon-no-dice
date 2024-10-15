package com.example.contador

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contador.ui.theme.ContadorTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Red,
                                        Color.Yellow,
                                        Color.Yellow,
                                        Color.Red
                                    )
                                )
                            )
                            .padding(innerPadding)
                    ) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.redsuninthesky) }
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val color = remember {
        mutableStateOf(Color.White)
    }
    val secuencia by remember { mutableStateOf(mutableListOf<Int>()) }
    var record by remember { mutableStateOf(0) }
    var puntuacion by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            TextButton(
                onClick = {
                    counter++
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0)
                    if (mediaPlayer.isPlaying) {
                        //mediaPlayer.seekTo(0)
                        mediaPlayer.start()
                    } else {
                        mediaPlayer.start()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .padding(top = 16.dp)
            ) {
                Text("Clics: $counter")
            }
        }
        Column (modifier = Modifier.align(Alignment.Center)) {
            Text("Record $record",
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            )
            Text("Puntuacion $puntuacion",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            )
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(color.value),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text(secuencia.toString())
            }


            Row {
                TextButton(
                    onClick = {
                        color.value = Color.Red
                        secuencia.add(Colores.ROJO.color)
                        puntuacion = secuencia.count()
                              },
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp,
                        bottomEnd = 50.dp,
                        bottomStart = 5.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    Text("ROJO")
                }
                TextButton(
                    onClick = {
                        color.value = Color.Green
                        secuencia.add(Colores.VERDE.color)
                        puntuacion = secuencia.count()
                              },
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 50.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    Text("VERDE")
                }
            }
            Row {
                TextButton(
                    onClick = {
                        color.value = Color.Blue
                        secuencia.add(Colores.AZUL.color)
                        puntuacion = secuencia.count()
                              },
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 50.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 5.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    Text("AZUL")
                }
                TextButton(
                    onClick = {
                        color.value = Color.Yellow
                        secuencia.add(Colores.AMARILLO.color)
                        puntuacion = secuencia.count()
                              },
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 5.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 5.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color.Yellow),
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    Text("AMARILLO")
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContadorTheme {
        Greeting("Android")
    }
}