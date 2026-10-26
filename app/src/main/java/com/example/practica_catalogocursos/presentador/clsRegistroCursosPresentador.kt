package com.example.practica_catalogocursos.presentador

import com.example.practica_catalogocursos.Vista.RegistroAlCursoActivity
import com.example.practica_catalogocursos.modelo.clsRegistroCursosModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistroCursosPresentador(private val vista: RegistroAlCursoActivity) {

    private val modelo= clsRegistroCursosModelo()

    fun RealizarRegistro(matricula: String, fecha: String, correo: String){

        if(matricula.isEmpty()||fecha.isEmpty()||correo.isEmpty()){
            vista.mostrarError("Por favor, llena todos los campos obligatorios")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try{
                val exito=modelo.registrarInscripcion(matricula, fecha, correo)

                if(exito){
                    vista.mostrarMensajeExito("Inscripción registrada correctamente")
                    vista.limpiarCampos()
                }else{
                    vista.mostrarError("No se pudo completar el registro")
                }
            }catch(e:Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
}