import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.contador.Colores
import com.example.contador.ModelView
import kotlinx.coroutines.delay

/**
 * Función composable que define la interfaz de usuario de la aplicación.
 *
 * @param model La instancia de ViewModel que gestiona el estado y la lógica de la aplicación.
 */
@Composable
fun IU(model: ModelView) {
    val context = LocalContext.current

    var record by remember { mutableStateOf(0) }
    var puntuacion by remember { mutableIntStateOf(model.getScore()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
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

            Boton_start(model = model, color = Colores.START, context = context)

            Row {
                Boton(model = model, color = Colores.ROJO, context = context)
                Boton(model = model, color = Colores.VERDE, context = context)
            }
            Row {
                Boton(model = model, color = Colores.AZUL, context = context)
                Boton(model = model, color = Colores.AMARILLO, context = context)
            }
        }
    }
}

/**
 * Función composable que define un botón con color y comportamiento específicos.
 *
 * @param model La instancia de ViewModel que gestiona el estado y la lógica de la aplicación.
 * @param color Las propiedades de color para el botón.
 * @param context El contexto en el que se usa el botón.
 * @param position La posición del botón (por defecto es 0).
 */
@Composable
fun Boton(model: ModelView, color: Colores, context: Context, position: Int = 0) {

    var _activo by remember { mutableStateOf(model.estadoLiveData.value!!.boton_activo) }

    model.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = model.estadoLiveData.value!!.boton_activo
    }

    TextButton(
        enabled = _activo,
        onClick = {
            if (model.compararNumero(color.color)) {
                Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Lose", Toast.LENGTH_SHORT).show()
            }
        },
        shape = RoundedCornerShape(
            topStart = color.cornertopleft,
            topEnd = color.cornertopright,
            bottomEnd = color.cornerbottomright,
            bottomStart = color.cornerbottomleft
        ),
        colors = ButtonDefaults.buttonColors(color.colorname),
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(5.dp)
    ) {
        Text("$color")
    }
}

/**
 * Función composable que define el botón de inicio con color y comportamiento específicos.
 *
 * @param model La instancia de ViewModel que gestiona el estado y la lógica de la aplicación.
 * @param color Las propiedades de color para el botón.
 * @param context El contexto en el que se usa el botón.
 */
@Composable
fun Boton_start(model: ModelView, color: Colores, context: Context) {
    var _activo by remember { mutableStateOf(model.estadoLiveData.value!!.start_activo) }

    model.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = model.estadoLiveData.value!!.start_activo
    }
    // variable para el color del botón usado en el LaunchedEffect
    var _color by remember { mutableStateOf(color.color_suave) }

    LaunchedEffect(_activo) {
        Log.d("Start_btn", "LaunchedEffect - Estado: ${_activo}")
        // solo si el botón está activo parpadea
        while (_activo) {
            _color = color.color_suave
            delay(500)
            _color = color.colorname
            delay(100)
        }
    }

    Button(onClick = {
        model.crearRandom()
    },
        enabled = _activo,
        colors = ButtonDefaults.buttonColors(_color),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .padding(bottom = 16.dp)
    ) {
        Text("Start")
    }
}