package com.example.practica_catalogocursos.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_catalogocursos.Adaptador.catalogoCursosAdaptador
import com.example.practica_catalogocursos.R
import com.example.practica_catalogocursos.modelo.dataCarreras
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos
import com.example.practica_catalogocursos.presentador.clsCarrerasPresentador


class MainActivity : AppCompatActivity() {
    private lateinit var presenter:clsCarrerasPresentador
    private lateinit var spinnerCarrera:Spinner
    private lateinit var recycleCursos: RecyclerView
    private lateinit var adapterCatalogoCurso:catalogoCursosAdaptador
    private lateinit var btnVerRegistros:Button

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
        recycleCursos = findViewById(R.id.rvCatalogos)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)

        recycleCursos.layoutManager = GridLayoutManager(this,2)
        adapterCatalogoCurso = catalogoCursosAdaptador(this, emptyList())
        recycleCursos.adapter = adapterCatalogoCurso
        presenter=clsCarrerasPresentador(this)
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

        btnVerRegistros.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?){
                val intent = Intent(this@MainActivity,VerRegistroActivity::class.java)
                startActivity(intent)
            }
        })
    }

    fun mostrarCarrera(carreras: List<dataCarreras>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarrera.adapter = adapter
    }

    fun mostrarCursos(cursos: List<dataCatalogoCursos>){
        adapterCatalogoCurso.actualizarLista(cursos)
    }

    fun seleccionarCurso(curso: dataCatalogoCursos){
        presenter.seleccionarCurso(curso)
    }
    fun registrarCurso(curso: dataCatalogoCursos) {
        presenter.registrarCurso(curso)
    }

    fun abrirRegistro(curso:dataCatalogoCursos){
        val intent=Intent(this, RegistroAlCursoActivity::class.java)
        intent.putExtra("id_curso", curso.idcurso)
        intent.putExtra("nombre_curso",curso.nombreCurso)
        intent.putExtra("ruta_imagen", curso.rutaImagen)
        startActivity(intent)
    }

    fun mostrarMensajeExito(id: Int, nombre: String){
        Toast.makeText(this, "ID: " + id.toString()+ "-seleccionaste:"+nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

}