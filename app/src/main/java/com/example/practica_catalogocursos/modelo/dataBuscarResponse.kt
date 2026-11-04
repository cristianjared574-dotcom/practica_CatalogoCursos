package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataBuscarResponse(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("matricula") val matricula: String = "",
    @SerialName("nombre_alumno") val nombrealumno: String = "",
    @SerialName("correo_institucional") val correoInstitucional: String = ""
)
