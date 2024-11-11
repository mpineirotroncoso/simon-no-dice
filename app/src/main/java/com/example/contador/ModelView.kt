package com.example.contador

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelView : ViewModel() {
    var _numbers = mutableStateOf(0)

    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    fun crearRandom() {
        Log.i("ModelView", "crearRandom")
        estadoLiveData.value = Estados.GENERANDO
        _numbers.value = (1..4).random()
        Log.d("ModelView", "creamos random ${_numbers.value}")
        actualizarNumero(_numbers.value)
    }

    fun actualizarNumero(numero: Int) {
        Log.d("ModelView", "actualizamos numero en Datos")
        Datos.numero = numero
        estadoLiveData.value = Estados.ADIVINANDO
    }

    fun compararNumero(numero: Int): Boolean {
        Log.d("ModelView", "comparamos numero $numero")
        if (numero == Datos.numero) {
            Log.d("ModelView", "numero correcto")
            estadoLiveData.value = Estados.INICIO
            increaseScore()
            return true
        } else {
            estadoLiveData.value = Estados.ADIVINANDO
            Log.d("ModelView", "numero incorrecto")
            return false
        }
    }

    fun getNumero(): Int {
        return Datos.numero
    }

    fun increaseScore() {
        Log.d("ModelView", "increaseScore")
        Datos.score++
    }

    fun resetScore() {
        Log.d("ModelView", "resetScore")
        Datos.score = 0
    }

    fun getScore(): Int {
        return Datos.score
    }
}