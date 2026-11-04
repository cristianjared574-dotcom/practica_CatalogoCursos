package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.Serializable

@Serializable //convertidor para enviarlos al servidor
data class dataEnvioRegistro(
    val id_curso: Int,
    val matricula: String,
    val fecha_registro: String,
    val correo_institucional: String
)
