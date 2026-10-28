package com.example.practica_catalogocursos.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_catalogocursos.Adaptador.verRegistroAdaptador
import com.example.practica_catalogocursos.R
import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import com.example.practica_catalogocursos.presentador.clsVerRegistrosPresentador

class VerRegistroActivity : AppCompatActivity() {

    private lateinit var presenter: clsVerRegistrosPresentador
    private lateinit var spinnerCarrera: Spinner
    private lateinit var recycleCursos: RecyclerView
    private lateinit var adapterVerRegistro: verRegistroAdaptador
    private lateinit var btnVolverCatalogo: Button
    private lateinit var txtTotalInscritos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.ver_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCarrera = findViewById(R.id.spnCarrera)
        recycleCursos = findViewById(R.id.rvCursos)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)
        txtTotalInscritos = findViewById(R.id.txttotalinscritos)

        recycleCursos.layoutManager = LinearLayoutManager(this)
        adapterVerRegistro = verRegistroAdaptador(emptyList())
        recycleCursos.adapter = adapterVerRegistro

        presenter = clsVerRegistrosPresentador(this)
        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {
        spinnerCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.seleccionarFiltro(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnVolverCatalogo.setOnClickListener {
            finish()
        }
    }

    fun mostrarCarrera(carreras: List<dataCarreras>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarrera.adapter = adapter

        spinnerCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.seleccionarFiltro(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun mostrarRegistros(registros: List<dataCatalogoCursos>, totalInscritos: Int) {
        adapterVerRegistro.actualizarLista(registros)
        val labelTotal = getString(R.string.txttotalregistros)
        txtTotalInscritos.text = "$labelTotal $totalInscritos"
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}