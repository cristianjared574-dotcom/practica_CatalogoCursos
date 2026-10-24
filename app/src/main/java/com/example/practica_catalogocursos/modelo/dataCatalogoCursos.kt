package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCatalogoCursos(
    @SerialName("id_curso") val idcurso:Int = 0,
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("codigo_curso") val codigoCurso: String = "",
    @SerialName("nombre_curso") val nombreCurso: String = "",
    @SerialName("descripcion_curso") val descripcion : String = "",
    @SerialName("ruta_imagen") val rutaImagen: String = "",
    @SerialName("salon") val salonCurso: String = "",
    @SerialName("edificio") val edificioCurso: String = "",
    @SerialName("total_horas") val totaHorasCurso: String = "",
    @SerialName("dias_atencion") val diasAtencion: String = "",
    @SerialName("hora_inicio") val horaInicioCurso: String = "",
    @SerialName("hora_fin") val horaFinCurso: String = "",
    @SerialName("cupo_total") val cupoTotalCurso: String =""
)
