package com.example.practica_catalogocursos.InterfaceApi

import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import com.example.practica_catalogocursos.modelo.dataEnvioRegistro
import com.example.practica_catalogocursos.modelo.dataRespuestaRegistro
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("id_carrera") carreraId: Int): List<dataCatalogoCursos>


    @POST("registro_al_curso.php")
    suspend fun registrarCurso(
        @Body datosRegistro: dataEnvioRegistro
    ): dataRespuestaRegistro

    @GET("detalle_registro_cursos.php")
    suspend fun obtenerRegistros(@Query("carrera_id") carreraId: Int): List<dataCatalogoCursos>
}
