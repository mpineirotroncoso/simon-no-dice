import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.contador.Colores
import com.example.contador.ModelView

@Composable
fun IU(model: ModelView) {
    val context = LocalContext.current
    val color = remember {
        mutableStateOf(Color.White)
    }
    var numeroRandom by remember { mutableStateOf(model.getNumero()) }
    var record by remember { mutableStateOf(0) }
    var puntuacion by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.align(Alignment.Center)) {
            Text("Record $record",
                textAlign = TextAlign.Center,
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
            Button(onClick = {
                model.crearRandom()
            },
                colors = ButtonDefaults.buttonColors(color.value),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text(text = numeroRandom.toString())
            }


            Row {
                TextButton(
                    onClick = {
                        color.value = Color.Red
                        if (model.compararNumero(Colores.ROJO.color))
                            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
                        else {
                            Toast.makeText(context, "Lose", Toast.LENGTH_SHORT).show()
                        }
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
                        if (model.compararNumero(Colores.VERDE.color)) {
                            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Lose", Toast.LENGTH_SHORT).show()
                        }
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
                        if (model.compararNumero(Colores.AZUL.color)) {
                            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Lose", Toast.LENGTH_SHORT).show()
                        }
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
                        if (model.compararNumero(Colores.AMARILLO.color))
                            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
                        else {
                            Toast.makeText(context, "Lose", Toast.LENGTH_SHORT).show()
                        }
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