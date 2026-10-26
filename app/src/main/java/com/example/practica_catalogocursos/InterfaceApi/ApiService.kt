package com.example.practica_catalogocursos.InterfaceApi

import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import kotlinx.serialization.json.JsonObject
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

    @FormUrlEncoded//simula un formulario web
    @POST("registro_al_curso.php")
    suspend fun registrarCurso(
        @Field("id_curso") idCurso: Int,
        @Field("matricula") matricula: String,
        @Field("fecha_registro") fechaRegistro: String,
        @Field("correo_institucional") correo: String
    ): dataRespuestaRegistro//porque no devuelve una lista
}
