package com.example.practica_catalogocursos.modelo

class clsBuscarModelo {
    suspend fun BuscarAlumno(matricula: String):dataBuscarResponse{
        val request = dataBuscarRequest(matricula)
        return RetrofitCliente.apiService.Buscar(request)
    }
}