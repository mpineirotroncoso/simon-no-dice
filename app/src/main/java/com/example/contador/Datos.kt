package com.example.contador

/**
 * Objeto para mantener datos globales de la aplicación.
 */
object Datos {
    /**
     * El número actual que se está utilizando en la aplicación.
     */
    var numero = 0

    /**
     * La puntuación actual del usuario.
     */
    var score = 0
}

/**
 * Clase enum que representa los diferentes estados de la aplicación.
 *
 * @property start_activo Indica si el botón de inicio está activo en este estado.
 * @property boton_activo Indica si los botones están activos en este estado.
 */
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    ADIVINANDO(start_activo = false, boton_activo = true),
}

/**
 * Clase enum que representa estados auxiliares con texto asociado.
 *
 * @property txt El texto asociado con el estado auxiliar.
 */
enum class EstadosAuxiliares(val txt: String) {
    AUX1(txt = "aux1"),
    AUX2(txt = "aux2"),
    AUX3(txt = "aux3"),
}