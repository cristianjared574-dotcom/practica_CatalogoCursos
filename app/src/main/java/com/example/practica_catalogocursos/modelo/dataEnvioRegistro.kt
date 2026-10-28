package com.example.practica_catalogocursos.modelo

data class dataEnvioRegistro(
    val id_curso: Int,
    val matricula: String,
    val fecha_registro: String,
    val correo_institucional: String
)
