package com.example.practica_catalogocursos.modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras():List<dataCarreras>{
        val datos=RetrofitCliente.apiService.obtenerCarreras() //pedido a la base de datos

        val listaFinal = mutableListOf(dataCarreras(0,"SELECCIONE LA CARRERA:"))
        listaFinal.addAll(datos)

        return listaFinal
    }
}