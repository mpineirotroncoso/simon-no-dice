package com.example.contador

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ModelView : ViewModel() {
    var _numbers = mutableStateOf(0)

    fun crearRandom() {
        Log.i("ModelView", "crearRandom")
        _numbers.value = (1..4).random()
        Log.d("ModelView", "creamos random ${_numbers.value}")
        actualizarNumero(_numbers.value)
    }

    fun actualizarNumero(numero: Int) {
        Log.d("ModelView", "actualizamos numero en Datos")
        Datos.numero = numero
    }

    fun compararNumero(numero: Int): Boolean {
        Log.d("ModelView", "comparamos numero $numero")
        return numero == Datos.numero
    }

    fun getNumero(): Int {
        return Datos.numero
    }
}