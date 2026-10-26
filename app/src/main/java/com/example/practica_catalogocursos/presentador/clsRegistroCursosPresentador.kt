package com.example.practica_catalogocursos.presentador

import com.example.practica_catalogocursos.vista.RegistroAlCursoActivity
import com.example.practica_catalogocursos.modelo.clsRegistroCursosModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistroCursosPresentador(private val vista: RegistroAlCursoActivity) {

    private val modelo= clsRegistroCursosModelo()

    //funcion para cuando se presione el boton registrarse en la pantalla
    fun registrarCurso(idCurso: Int, matricula: String, fechaRegistro: String, correo: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta= modelo.registrarCurso(idCurso, matricula, fechaRegistro, correo)

                if (!respuesta.mensaje.isNullOrEmpty()){
                    vista.mostrarResultado(respuesta.mensaje)
                }else if(!respuesta.error.isNullOrEmpty()){
                    vista.mostrarError(respuesta.error)
                }else{
                    vista.mostrarResultado("Registro realizado con éxito")
                }

            }catch (e: Exception){
                vista.mostrarError("Error al conectar con el servidor: ${e.localizedMessage}")
            }
        }
    }
}