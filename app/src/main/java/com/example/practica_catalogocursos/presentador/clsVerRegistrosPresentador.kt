package com.example.practica_catalogocursos.presentador

import com.example.practica_catalogocursos.vista.VerRegistroActivity
import com.example.practica_catalogocursos.modelo.clsCarrerasModelo
import com.example.practica_catalogocursos.modelo.clsVerRegistrosModelo
import com.example.practica_catalogocursos.modelo.dataCarreras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsVerRegistrosPresentador(private val vista: VerRegistroActivity) {

    private val modeloRegistros = clsVerRegistrosModelo()
    private val modeloCarreras = clsCarrerasModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarrera(listaCarreras)

                if (listaCarreras.isNotEmpty()) {
                    cargarRegistros(listaCarreras[0].idCarrera)
                }
            } catch (e: Exception) {
                vista.mostrarError("Error al iniciar: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarFiltro(posicion: Int) {
        if (listaCarreras.isNotEmpty() && posicion >= 0 && posicion < listaCarreras.size) {
            val carreraSeleccionada = listaCarreras[posicion]
            cargarRegistros(carreraSeleccionada.idCarrera)
        }
    }

    private fun cargarRegistros(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val registros = modeloRegistros.obtenerRegistros(carreraId)
                val totalInscritos = modeloRegistros.calcularTotalInscritos(registros)
                vista.mostrarRegistros(registros, totalInscritos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar los registros: " + e.localizedMessage)
            }
        }
    }

}