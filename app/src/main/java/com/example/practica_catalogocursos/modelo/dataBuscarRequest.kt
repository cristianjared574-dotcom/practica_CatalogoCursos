package com.example.practica_catalogocursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataBuscarRequest(
    @SerialName("matricula") val matricula: String = ""
)
