package com.example.practica_catalogocursos.modelo

class clsVerRegistrosModelo {
    suspend fun obtenerRegistros(carreraId: Int): List<dataCatalogoCursos> {
        return RetrofitCliente.apiService.obtenerRegistros(carreraId)
    }
    fun calcularTotalInscritos(lista: List<dataCatalogoCursos>): Int{
        var sumaTotal = 0
        for (curso in lista) {
            sumaTotal += curso.totalRegistros
        }
        return sumaTotal
    }
}