package com.example.practica_catalogocursos.modelo

class clsCatalogoCursosModelo {

    suspend fun obtenerCursos(carreraId: Int): List<dataCatalogoCursos>{
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
}