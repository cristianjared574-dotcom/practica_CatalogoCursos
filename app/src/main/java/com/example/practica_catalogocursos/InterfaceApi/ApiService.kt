package com.example.practica_catalogocursos.InterfaceApi

import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("id_carrera") carreraId: Int): List<dataCatalogoCursos>
}