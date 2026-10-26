package com.example.practica_catalogocursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practica_catalogocursos.R
import com.example.practica_catalogocursos.Vista.MainActivity
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos

class catalogoCursosAdaptador(private val activity: MainActivity, private var listaCatalogoCursos: List<dataCatalogoCursos>): RecyclerView.Adapter<catalogoCursosAdaptador.CursosViewHolder>() {

    class CursosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombreCurso: TextView = itemView.findViewById(R.id.txtNombreCurso)
        val tvSalon: TextView = itemView.findViewById(R.id.txtSalon)
        val imagenCurso: ImageView = itemView.findViewById(R.id.imgCurso)

        val btnVerDetalle: Button = itemView.findViewById(R.id.btnVerDetalle)
        val btnRegistrarse: Button = itemView.findViewById(R.id.btnRegistrarse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cursos, parent, false)
        return CursosViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursosViewHolder , position: Int){
        val curso = listaCatalogoCursos[position]
        holder.tvNombreCurso.text = curso.nombreCurso
        holder.tvSalon.text = "Salón: " + curso.salonCurso
        val baseUrl = "http://192.168.100.53/catalogocursos/"
        val urlImagen = baseUrl + curso.rutaImagen
        Glide.with(holder.itemView.context)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imagenCurso)

        holder.btnVerDetalle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.seleccionarCurso(curso)
            }
        })

        holder.btnRegistrarse.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.registrarCurso(curso)
            }
        })
    }

    override fun getItemCount():Int{
        return listaCatalogoCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCatalogoCursos>){
        listaCatalogoCursos = nuevaLista
        notifyDataSetChanged()
    }

}