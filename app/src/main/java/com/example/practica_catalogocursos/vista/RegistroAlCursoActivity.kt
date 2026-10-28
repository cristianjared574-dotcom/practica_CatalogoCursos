package com.example.practica_catalogocursos.vista

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import android.app.DatePickerDialog
import java.util.Calendar
import com.example.practica_catalogocursos.R
import com.example.practica_catalogocursos.presentador.clsRegistroCursosPresentador

class RegistroAlCursoActivity : AppCompatActivity() {
    private var idCursoActual: Int = 0
    private lateinit var txtEtiMatricula: EditText
    private lateinit var txtEtiFechaRegistro: EditText
    private lateinit var txtEtiCorreo: EditText
    private lateinit var btnConfirmarRegistro: Button
    private lateinit var btnCancelarRegistro: Button
    private lateinit var txtRegistroCurso: TextView
    private lateinit var imgCursoRegistro: ImageView
    private lateinit var presenter: clsRegistroCursosPresentador



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_al_curso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEtiMatricula= findViewById(R.id.txtEtiMatricula)
        txtEtiFechaRegistro= findViewById(R.id.txtEtiFechaRegistro)
        txtEtiCorreo= findViewById(R.id.txtEtiCorreo)
        btnConfirmarRegistro= findViewById(R.id.btnConfirmarRegistro)
        btnCancelarRegistro= findViewById(R.id.btnCancelarRegistro)
        imgCursoRegistro= findViewById(R.id.imgCursoRegistro)
        txtRegistroCurso= findViewById(R.id.txtRegistroCurso)

        //prueba temporal
        idCursoActual=intent.getIntExtra("id_curso",0)
        val nombreCurso=intent.getStringExtra("nombre_curso")
        val rutaImagen=intent.getStringExtra("ruta_imagen")

        txtRegistroCurso.text=nombreCurso ?: "CursoSinNombre"

        val baseUrl = "http://192.168.100.53/catalogocursos/"
        val urlImagen=baseUrl + rutaImagen

        Glide.with(this)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCursoRegistro)

        presenter= clsRegistroCursosPresentador(this)

        btnConfirmarRegistro.setOnClickListener{
            val matricula= txtEtiMatricula.text.toString().trim()
            val fechaRegistro = txtEtiFechaRegistro.text.toString().trim()
            val correo = txtEtiCorreo.text.toString().trim()

            //valida campos vacios
            if(matricula.isNotEmpty() && correo.isNotEmpty() && fechaRegistro.isNotEmpty()){
                presenter.registrarCurso(idCursoActual, matricula, fechaRegistro, correo)
            }else{
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelarRegistro.setOnClickListener{
            finish()
        }
    }

    fun mostrarResultado(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

    }

    fun mostrarError(mensaje:String){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}