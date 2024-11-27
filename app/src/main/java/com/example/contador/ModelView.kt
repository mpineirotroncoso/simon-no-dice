package com.example.contador

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ModelView : ViewModel() {
    var _numbers = mutableStateOf(0)

    val estadoLiveData: MutableLiveData<Estados?> = MutableLiveData(Estados.INICIO)

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
        estadosAuxiliares()
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

    /**
     * Corutina que lanza estados auxiliares
     */
    fun estadosAuxiliares() {
        viewModelScope.launch {
            // guardamos el estado auxiliar
            var estadoAux = EstadosAuxiliares.AUX1
            // hacemos un cambio a tres estados auxiliares
            Log.d("estadosAuxiliares", "estado (corutina): ${estadoAux}")
            delay(1500)
            estadoAux = EstadosAuxiliares.AUX2
            Log.d("estadosAuxiliares", "estado (corutina): ${estadoAux}")
            delay(1500)
            estadoAux = EstadosAuxiliares.AUX3
            Log.d("estadosAuxiliares", "estado (corutina): ${estadoAux}")
            delay(1500)
        }
    }
}