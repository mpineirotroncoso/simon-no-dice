package com.example.contador

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Clase ViewModel para gestionar el estado y la lógica de la aplicación.
 */
class ModelView : ViewModel() {
    /**
     * Estado mutable que contiene el número actual.
     */
    var _numbers = mutableStateOf(0)

    /**
     * LiveData que contiene el estado actual de la aplicación.
     */
    val estadoLiveData: MutableLiveData<Estados?> = MutableLiveData(Estados.INICIO)

    /**
     * Crea un número aleatorio entre 1 y 4 y actualiza el estado.
     */
    fun crearRandom() {
        Log.i("ModelView", "crearRandom")
        estadoLiveData.value = Estados.GENERANDO
        _numbers.value = (1..4).random()
        Log.d("ModelView", "creamos random ${_numbers.value}")
        actualizarNumero(_numbers.value)
    }

    /**
     * Actualiza el número global en Datos y cambia el estado a ADIVINANDO.
     *
     * @param numero El nuevo número a actualizar.
     */
    fun actualizarNumero(numero: Int) {
        Log.d("ModelView", "actualizamos numero en Datos")
        Datos.numero = numero
        estadoLiveData.value = Estados.ADIVINANDO
    }

    /**
     * Compara el número dado con el número global en Datos.
     *
     * @param numero El número a comparar.
     * @return True si los números coinciden, false en caso contrario.
     */
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

    /**
     * Obtiene el número global actual de Datos.
     *
     * @return El número actual.
     */
    fun getNumero(): Int {
        return Datos.numero
    }

    /**
     * Incrementa la puntuación global en Datos en 1.
     */
    fun increaseScore() {
        Log.d("ModelView", "increaseScore")
        Datos.score++
    }

    /**
     * Restablece la puntuación global en Datos a 0.
     */
    fun resetScore() {
        Log.d("ModelView", "resetScore")
        Datos.score = 0
    }

    /**
     * Obtiene la puntuación global actual de Datos.
     *
     * @return La puntuación actual.
     */
    fun getScore(): Int {
        return Datos.score
    }

    /**
     * Corutina que lanza estados auxiliares.
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