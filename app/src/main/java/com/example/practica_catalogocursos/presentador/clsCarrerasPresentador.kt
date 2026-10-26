package com.example.practica_catalogocursos.presentador

import com.example.practica_catalogocursos.MainActivity
import com.example.practica_catalogocursos.modelo.clsCarrerasModelo
import com.example.practica_catalogocursos.modelo.clsCatalogoCursosModelo
import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarrerasPresentador(private val vista: MainActivity) {
    private val modeloCatalogoCursos=clsCatalogoCursosModelo()
    private val modelo = clsCarrerasModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {

        CoroutineScope(Dispatchers.Main).launch {
            try{
                listaCarreras = modelo.obtenerCarreras()
                vista.mostrarCarrera(listaCarreras)

                cargarCursos(0)

            }catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int){
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarCursos(carrera.idCarrera)
        }
    }

    private fun cargarCursos(idcarrera: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val cursos = modeloCatalogoCursos.obtenerCursos(idcarrera)
                vista.mostrarCursos(cursos)
            }catch (e: Exception){
                vista.mostrarError("Error al cargar los cursos" + e.localizedMessage)
            }
        }
    }

    fun seleccionarCurso(curso: dataCatalogoCursos) {
        //vista.abrirDetalle(curso)
    }

    fun registrarCurso(curso: dataCatalogoCursos) {
        //vista.abrirRegistro(curso)
    }

    /*fun verRegistroDetalle(curso:data){

    }*/


}