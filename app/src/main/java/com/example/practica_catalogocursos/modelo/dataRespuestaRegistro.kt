package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRespuestaRegistro(
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("error") val error: String = ""
)
