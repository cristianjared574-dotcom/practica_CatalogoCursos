package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarreras(
    @SerialName("id_carrera") val idCarrera:Int=0,
    @SerialName("nombre_carrera") val carrera:String=""
)
{
    override fun toString(): String {
        return carrera
    }
}