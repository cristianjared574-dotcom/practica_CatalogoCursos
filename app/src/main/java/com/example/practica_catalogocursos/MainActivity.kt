package com.example.practica_catalogocursos

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.presentador.clsCarrerasPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presenter:clsCarrerasPresentador
    private lateinit var spinnerCarrera:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCarrera = findViewById(R.id.spnCarreras)
        presenter = clsCarrerasPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }
    private fun configurarListeners(){
        spinnerCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position:Int,id:Long){
                presenter.seleccionarCarrera(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun mostrarCarrera(carreras: List<dataCarreras>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarrera.adapter = adapter
    }

    fun mostrarMensajeExito(id: Int, nombre: String){
        Toast.makeText(this, "ID: " + id.toString()+ "-seleccionaste:"+nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

}