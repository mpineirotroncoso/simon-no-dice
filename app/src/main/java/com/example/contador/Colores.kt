package com.example.contador

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Clase enum que representa diferentes colores con propiedades asociadas.
 *
 * @property color El valor entero que representa el color.
 * @property colorname El objeto Color que representa el color.
 * @property cornertopleft El radio de la esquina superior izquierda.
 * @property cornertopright El radio de la esquina superior derecha.
 * @property cornerbottomleft El radio de la esquina inferior izquierda.
 * @property cornerbottomright El radio de la esquina inferior derecha.
 * @property color_suave El color suave asociado con el valor enum, por defecto es Color.Transparent.
 */
enum class Colores(
    val color: Int,
    val colorname: Color,
    val cornertopleft: Dp,
    val cornertopright: Dp,
    val cornerbottomleft: Dp,
    val cornerbottomright: Dp,
    val color_suave: Color = Color.Transparent
) {
    ROJO(1, Color.Red, 5.dp, 5.dp, 5.dp, 50.dp),
    VERDE(2, Color.Green, 5.dp, 5.dp, 50.dp, 5.dp),
    AZUL(3, Color.Blue, 5.dp, 50.dp, 5.dp, 5.dp),
    AMARILLO(4, Color.Yellow, 50.dp, 5.dp, 5.dp, 5.dp),
    START(5, Color.Gray, 5.dp, 5.dp, 5.dp, 5.dp, Color.White)
}