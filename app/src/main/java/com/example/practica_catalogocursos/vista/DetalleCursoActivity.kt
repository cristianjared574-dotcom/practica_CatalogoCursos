package com.example.practica_catalogocursos.vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.practica_catalogocursos.R

class DetalleCursoActivity : AppCompatActivity() {

    private lateinit var imagenView: ImageView
    private lateinit var txtDetalleNomCurso: TextView
    private lateinit var txtDetalleCodigo: TextView
    private lateinit var txtDetalleDescripcionmas: TextView
    private lateinit var txtDetalleEspaciomas: TextView
    private lateinit var txtDetalleTotalhMas: TextView
    private lateinit var txtDetalleDiasmas: TextView
    private lateinit var txtDetalleHorario: TextView
    private lateinit var txtDetalleHorarioini: TextView
    private lateinit var txtDetalleHorariofin: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_curso)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imagenView = findViewById(R.id.imageView)
        txtDetalleNomCurso = findViewById(R.id.txtDetalleNomCurso)
        txtDetalleCodigo = findViewById(R.id.txtDetalleCodigo)
        txtDetalleDescripcionmas = findViewById(R.id.txtDetalleDescripcionmas)
        txtDetalleEspaciomas = findViewById(R.id.txtDetalleEspaciomas)
        txtDetalleTotalhMas = findViewById(R.id.txtDetalleTotalhMas)
        txtDetalleDiasmas = findViewById(R.id.txtDetalleDiasmas)
        txtDetalleHorario = findViewById(R.id.txtDetalleHorario)
        txtDetalleHorarioini = findViewById(R.id.txtDetalleHorarioini)
        txtDetalleHorariofin = findViewById(R.id.txtDetalleHorariofin)
        btnBack = findViewById(R.id.btnBack)

        val nomCurso = intent.getStringExtra("nombrecurso") ?: ""
        val codigocurso = intent.getStringExtra("codigo_curso") ?: ""
        val descripcion = intent.getStringExtra("descripcion_curso") ?: ""
        val salonCurso = intent.getStringExtra("salon_curso") ?: ""
        val edificio = intent.getStringExtra("espacio_curso") ?: ""
        val horastotales = intent.getStringExtra("totalh_curso") ?: ""
        val dias = intent.getStringExtra("dias_curso") ?: ""
        val rutaImagen = intent.getStringExtra("IMAGEN_CURSO")

        val horaInicio = intent.getStringExtra("HORA_INICIO") ?: ""
        val horaFin = intent.getStringExtra("HORA_FIN") ?: ""


        txtDetalleNomCurso.text = nomCurso
        txtDetalleCodigo.text = "Código: " + codigocurso
        txtDetalleDescripcionmas.text = descripcion
        txtDetalleEspaciomas.text = "Salón "+"$salonCurso" +", Edificio "+"$edificio"
        txtDetalleTotalhMas.text = horastotales + " Horas"
        txtDetalleDiasmas.text = dias
        txtDetalleHorarioini.text=horaInicio
        txtDetalleHorariofin.text=horaFin

        val baseUrl = "http://172.31.99.212/catalogocursos/"
        if (!rutaImagen.isNullOrEmpty()) {
            Glide.with(this)
                .load(baseUrl + rutaImagen)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(imagenView)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}