package com.example.contador

object Datos {
    var numero = 0
    var score = 0
}

enum class Estados(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    ADIVINANDO(start_activo = false, boton_activo = true),
}

enum class EstadosAuxiliares(val txt: String) {
    AUX1(txt = "aux1"),
    AUX2(txt = "aux2"),
    AUX3(txt = "aux3"),
}