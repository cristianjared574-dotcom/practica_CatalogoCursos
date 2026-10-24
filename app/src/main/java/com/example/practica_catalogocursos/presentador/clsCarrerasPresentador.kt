package com.example.practica_catalogocursos.presentador

import com.example.practica_catalogocursos.MainActivity
import com.example.practica_catalogocursos.modelo.clsCarrerasModelo
import com.example.practica_catalogocursos.modelo.dataCarreras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarrerasPresentador(private val vista: MainActivity) {
    private val modelo = clsCarrerasModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {

        CoroutineScope(Dispatchers.Main).launch {
            try{
                listaCarreras = modelo.obtenerCarreras()
                vista.mostrarCarrera(listaCarreras)
            }catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int){
        if(posicion >= 0 && posicion < listaCarreras.size){
            val carreras = listaCarreras[posicion]
            vista.mostrarMensajeExito(carreras.idCarrera, carreras.carrera)
        }
    }
}