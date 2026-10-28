package com.example.practica_catalogocursos.modelo

class clsRegistroCursosModelo {
    suspend fun registrarCurso(
        idCurso: Int, matricula: String, fechaRegistro: String, correo: String
    ): dataRespuestaRegistro{
        //creamos el objeto json que se enviara en el body
        val datos= dataEnvioRegistro(
            id_curso=idCurso, matricula=matricula, fecha_registro=fechaRegistro, correo_institucional=correo
        )
        return RetrofitCliente.apiService.registrarCurso(datos)

    }
}